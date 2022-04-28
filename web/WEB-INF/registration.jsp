<%-- 
    Document   : registration
    Created on : Aug 12, 2021, 4:29:05 PM
    Author     : 839217
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link  rel="stylesheet" href="<c:url value="/main.css" />" type="text/css">
        <title>Register</title>
    </head>
    <body>
        <h1>Registration Page</h1>
        <a href="login">Login</a>
        <h2>Register Here!</h2>
        <form method="post" action="register">
            First Name: <input type="text" name="firstname" placeholder="Firstname"><br>
            Last Name: <input type="text" name="lastname" placeholder="Lastname"><br>
            Username: <input type="text" name="username" placeholder="Username"><br>
            Email: <input type="text" name="email" placeholder="Email"><br>
            Password: <input type="password" name="password" placeholder="password"><br>
            <input type="submit" value="Register">
        </form>
        <c:if test="${error}">
            <p> Something Went wrong, please try again with different username</p>
        </c:if>
            <c:if test="${errorEmptyValue}">
            <br>  Any field cannot be empty.
        </c:if>
            <br>Note:  User must verify their email address for activation
    </body>
</html>
