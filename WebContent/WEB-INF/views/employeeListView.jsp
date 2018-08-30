<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
  <body>

 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>Employee Details List</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Username</th>
          <th>Gender</th>
          <th>Department</th>
          <th>Salary</th>
          <th>Date-Of-Birth</th>
          <th>Action</th>
       </tr>
       <c:forEach items="${empList}" var="user">
          <tr>
             <td><c:out value="${user.userName}"/></td>
             <td>${user.gender}</td>
             <td>${user.department}</td>
             <td>${user.salary}</td>
             <td>${user.dob}</td>
             <td>
                <a href="editEmployeeDetails?userName=${user.userName}">Edit</a>
             </td>
          </tr>
          <tr>
          </tr>
       </c:forEach>
    </table>
 
    <a href="createEmployee" >Create Employee</a>
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
 <!-- Include all compiled plugins (below), or include individual files as needed -->
 <script src="js/bootstrap.min.js"></script>
</body>
</html>