<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>Hello Super EMP: ${user.userName}</h3>
 
    User Name: <b>${user.userName}</b>
<%--     <br />
    Gender: ${user.gender } <br /> --%>
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>