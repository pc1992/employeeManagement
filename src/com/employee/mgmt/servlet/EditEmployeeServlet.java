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
 
@WebServlet(urlPatterns = { "/editEmployee" })
public class EditEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public EditEmployeeServlet() {
        super();
    }
 
    // Show employee edit page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String userName = (String) request.getParameter("userName");
 
        EmployeePersonal employee = null;
 
        String errorString = null;
 
        try {
        	employee = DBUtils.findEmployee(conn, userName);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
        // If no error.
        // The employee does not exist to edit.
        // Redirect to profile page.
        if (errorString != null && employee == null) {
            response.sendRedirect(request.getServletPath() + "/profile");
            return;
        }
 
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("user", employee);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/editEmployeeView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    // After the user modifies the product information, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String userName = (String) request.getParameter("userName");
        String password = (String) request.getParameter("password");
        String id = (String) request.getParameter("id");
        System.out.println(id+"Id is");

        EmployeePersonal employee = new EmployeePersonal();//add data members
        Employee employeeDetails = new Employee();
        String encryptedPassword = CryptoUtil.encrypt(password);
        employee.setUserName(userName);
        employee.setPassword(encryptedPassword);
        employee.setId(id);
        System.out.println(employee.getPassword());
 
        String errorString = null;
 
        try {
            DBUtils.updateProduct(conn, employee);
            employeeDetails = DBUtils.queryEmployee(conn, userName);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("user", employeeDetails);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editEmployeeView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the profile page.
        else {
            response.sendRedirect(request.getContextPath() + "/profile");
        }
    }
 
}