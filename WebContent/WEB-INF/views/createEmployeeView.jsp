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
       
      <h1 class="page-header">Create Employee</h1>
       
      <p style="color: red;">${errorString}</p>
       
      <form method="POST" action="${pageContext.request.contextPath}/createEmployee">
         <table border="0"class="table table-striped table-bordered table-hover table-condensed">
            <tr>
               <td>Id</td>
               <td><input type="text" name="id" value="${employee.id}" /></td>
            </tr>
            <tr>
               <td>User Name</td>
               <td><input type="text" name="userName" value="${employee.userName}" /></td>
            </tr>
            <tr>
               <td>Gender</td>
               <td><input type="text" name="gender" value="${employee.gender}" /></td>
            </tr>
            <tr>
               <td>Department</td>
               <td><input type="text" name="department" value="${employee.department}" /></td>
            </tr>
            <tr>
               <td>Salary</td>
               <td><input type="text" name="salary" value="${employee.salary}" /></td>
            </tr>
            <tr>
               <td>Date-of-Birth</td>
               <td><input type="text" name="dob" value="${employee.dob}" /></td>
            </tr>
            <tr>
               <td>Role</td>
               <td><input type="text" name="role" value="${employee.role}" /></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" class="btn btn-success" />
                   <a href="listAll" class="btn btn-danger" role="button">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
       
      <jsp:include page="_footer.jsp"></jsp:include>
       
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>