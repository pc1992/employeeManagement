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
import javax.servlet.http.HttpSession;

import com.employee.mgmt.beans.*;
import com.employee.mgmt.utils.DBUtils;
import com.employee.mgmt.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/profile" })
public class EmployeeDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public EmployeeDetailsServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        
        HttpSession session = request.getSession();

        // Check User has logged on
        EmployeePersonal loginedUser = MyUtils.getLoginedUser(session);

        // Not logged in
        if (loginedUser == null) {
            // Redirect to login page.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        // Store info to the request attribute before forwarding.
        request.setAttribute("user", loginedUser);

 
        String errorString = null;
        Employee employee = new Employee();
        try {
        	employee = DBUtils.queryEmployee(conn, loginedUser.getUserName());
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("user", employee);
         
        // Forward to /WEB-INF/views/productListView.jsp
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/employeeDetailsView.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}