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
 
      <h1 class="page-header">Login Page</h1>
      <p style="color: red;">${errorString}</p>
 
 
      <div>
        <!-- INLINE FORM -->
        <form class="form-inline" method="POST" action="${pageContext.request.contextPath}/login">
                <div class="form-group">
                  <label>User Name</label>
                  <input type="text" class="form-control" placeholder="User Name" name="userName" value= "${user.userName}">
                </div>
                <div class="form-group">
                  <label>Password</label>
                  <input type="password" class="form-control" placeholder="Password" name="password" value= "${user.password}>
                </div>
                <div class="checkbox">
                <label>
                  <input type="checkbox" value= "Y" name="rememberMe"> Remember me
                </label>
                <button type="submit" class="btn btn-default">Login</button>
                <a href="${pageContext.request.contextPath}/" class="btn btn-success" role="button">Cancel</a>
              </form>
      </div>

      <form method="POST" action="${pageContext.request.contextPath}/login">
         <table border="0">
            <tr>
               <td>User Name</td>
               <td><input type="text" name="userName" value= "${user.userName}" /> </td>
            </tr>
            <tr>
               <td>Password</td>
               <td><input type="text" name="password" value= "${user.password}" /> </td>
            </tr>
            <tr>
               <td>Remember me</td>
               <td><input type="checkbox" name="rememberMe" value= "Y" /> </td>
            </tr>
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "Submit" />
                  <a href="${pageContext.request.contextPath}/" class="btn btn-success" role="button">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
 
      <p style="color:blue;">Employee Name: tom, password: tom001 or jerry/jerry001</p>
 
      <jsp:include page="_footer.jsp"></jsp:include>
  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="js/bootstrap.min.js"></script>
</body>
</html>