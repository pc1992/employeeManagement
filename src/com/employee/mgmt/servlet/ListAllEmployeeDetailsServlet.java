package com.employee.mgmt.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.mgmt.beans.*;
import com.employee.mgmt.utils.DBUtils;
import com.employee.mgmt.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/listAll" })
public class ListAllEmployeeDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public ListAllEmployeeDetailsServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(request);
 
        String errorString = null;
        List<Employee> list = null;
        try {
            list = DBUtils.findAll(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("empList", list);
        System.out.println(list.get(0).getDepartment());
         
        // Forward to /WEB-INF/views/employeeListView.jsp
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/listAllView.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}