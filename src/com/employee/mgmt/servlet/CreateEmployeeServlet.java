package com.employee.mgmt.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.employee.mgmt.beans.*;
import com.employee.mgmt.utils.*;

 
@WebServlet(urlPatterns = { "/createEmployee" })
public class CreateEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public CreateEmployeeServlet() {
        super();
    }
 
    // Show employee creation page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/createEmployeeView.jsp");
        dispatcher.forward(request, response);
    }
 
    // When the admin enters the employee information, and click Submit.
    // This method will be called.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String id = (String) request.getParameter("id");

        Employee employee = new Employee();//add data members here
        employee.setId(request.getParameter("id"));
        employee.setUserName(request.getParameter("userName"));
        employee.setGender(request.getParameter("gender"));
        employee.setDepartment(request.getParameter("department"));
        employee.setSalary(request.getParameter("salary"));
        employee.setDob(request.getParameter("dob"));
        employee.setRole(request.getParameter("role"));
        
 
        String errorString = null;
 
        // Product ID is the string literal [a-zA-Z_0-9]
        // with at least 1 character
        String regex = "\\w+";
 
        if (id == null || !id.matches(regex)) {
            errorString = "Employee Id invalid!";
        }
 
        if (errorString == null) {
            try {
                DBUtils.insertEmployee(conn, employee);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }
 
        // Store information to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("employee", employee);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createEmployeeView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the employee listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/listAll");
        }
    }
 
}
