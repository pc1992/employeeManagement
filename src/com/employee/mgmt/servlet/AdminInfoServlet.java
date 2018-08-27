package com.employee.mgmt.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.mgmt.beans.*;
import com.employee.mgmt.utils.MyUtils;

@WebServlet(urlPatterns = { "/adminInfo" })
public class AdminInfoServlet  extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public AdminInfoServlet() {
       super();
   }

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
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

       // If the user has logged in, then forward to the page
       // /WEB-INF/views/userInfoView.jsp
       RequestDispatcher dispatcher //
               = this.getServletContext().getRequestDispatcher("/WEB-INF/views/adminInfoView.jsp");
       dispatcher.forward(request, response);

   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doGet(request, response);
   }

}
