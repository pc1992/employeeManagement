<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
      <meta charset="UTF-8">
      <title>Edit Employee Details</title>
   </head>
   <body>
 
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
 
      <h3>Edit Employee Details</h3>
 
      <p style="color: red;">${errorString}</p>
 
      <c:if test="${not empty user}">
         <form method="POST" action="${pageContext.request.contextPath}/editEmployee">
            <input type="hidden" name="id" value="${user.id}" />
            <table border="0">
               <tr>
                  <td>Username</td>
                  <td style="color:red;">${user.userName}</td>
               </tr>
                 <tr>
                  <td>ID</td>
                  <td><input type="text" name="id" value="${user.id}" /></td>
               </tr>
              <%--  <tr>
                  <td>Username</td>
                  <td><input type="text" name="userName" value="${user.userName}" /></td>
               </tr> --%>
               <tr>
                  <td>Gender</td>
                  <td><input type="text" name="gender" value="${user.gender}" /></td>
               </tr>
                 <tr>
                  <td>Department</td>
                  <td><input type="text" name="department" value="${user.department}" /></td>
               </tr>
                 <tr>
                  <td>Salary</td>
                  <td><input type="text" name="salary" value="${user.salary}" /></td>
               </tr>
                 <tr>
                  <td>Date-of-Birth</td>
                  <td><input type="text" name="dob" value="${user.dob}" /></td>
               </tr>
               <tr>
                  <td colspan = "2">
                      <input type="submit" value="Submit" />
                      <a href="${pageContext.request.contextPath}/list">Cancel</a>
                  </td>
               </tr>
            </table>
         </form>
      </c:if>
 
      <jsp:include page="_footer.jsp"></jsp:include>
 
   </body></html>