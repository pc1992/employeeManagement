<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
      <meta charset="UTF-8">
      <title>Edit Product</title>
   </head>
   <body>
 
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
 
      <h3>Edit Product</h3>
 
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
                  <td>Username</td>
                  <td><input type="text" name="userName" value="${user.userName}" /></td>
               </tr>
               <tr>
                  <td>Password</td>
                  <td><input type="text" name="password" value="${user.password}" /></td>
               </tr>
               <tr>
                  <td colspan = "2">
                      <input type="submit" value="Submit" />
                      <a href="${pageContext.request.contextPath}/profile">Cancel</a>
                  </td>
               </tr>
            </table>
         </form>
      </c:if>
 
      <jsp:include page="_footer.jsp"></jsp:include>
 
   </body></html>