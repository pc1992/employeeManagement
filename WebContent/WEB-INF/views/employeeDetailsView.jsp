<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
    <meta charset="UTF-8">
    <title>Employee Details</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>Employee Details</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Username</th>
          <th>Gender</th>
          <th>Department</th>
          <th>Salary</th>
          <th>Date-Of-Birth</th>
       </tr>
       <c:forEach items="${user}" var="user" >
          <tr>
             <td>${user.userName}</td>
             <td>${user.gender}</td>
             <td>${user.department}</td>
             <td>${user.salary}</td>
             <td>${user.dob}</td>
          </tr>
          <tr>
             <td>
                <a href="editEmployee?userName=${user.userName}">Edit</a>
             </td>
             <td>
                <a href="deleteProduct?code=${product.code}">Delete</a>
             </td>
          </tr>
       </c:forEach>
    </table>
 
    <a href="createProduct" >Create Product</a>
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>