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
import com.employee.mgmt.utils.*;
 
@WebServlet(urlPatterns = { "/editEmployeeDetails" })
public class SuperUserEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public SuperUserEditServlet() {
        super();
    }
 
    // Show employee edit page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String userName = (String) request.getParameter("userName");
 
        Employee employee = null;
 
        String errorString = null;
 
        try {
        	employee = DBUtils.findEmployeeForEdit(conn, userName);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
        // If no error.
        // The employee does not exist to edit.
        // Redirect to profile page.
        if (errorString != null && employee == null) {
            response.sendRedirect(request.getServletPath() + "/list");
            return;
        }
 
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("user", employee);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/editEmployeeDetailsView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String userName = (String) request.getParameter("userName");
        String id = (String) request.getParameter("id");
        System.out.println(id+"Id is");

        Employee employeeDetails = new Employee();
        employeeDetails.setUserName(userName);
        employeeDetails.setId(id);
        employeeDetails.setDepartment(request.getParameter("department"));
        employeeDetails.setGender(request.getParameter("gender"));
        employeeDetails.setSalary(request.getParameter("salary"));
        employeeDetails.setDob(request.getParameter("dob"));
 
        String errorString = null;
        List<Employee> list = null;
        try {
            DBUtils.updateEmployeeDetails(conn, employeeDetails);
            list = DBUtils.queryEmployee(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("empList", list);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editEmployeeDetailsView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the profile page.
        else {
            response.sendRedirect(request.getContextPath() + "/employeeListView");
        }
    }
 
}
