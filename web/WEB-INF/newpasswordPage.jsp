<%-- 
    Document   : newpasswordPage
    Created on : Aug 15, 2021, 6:59:46 PM
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
        <title>New Password</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        
        <a href="login">Login Page</a><br><br>
        <form method="post" action ="">Enter New Password:  <input type="password" name="newPassword">
            <input type="hidden" name="uuid" value="${uuid}"><br>
            <input type="submit" value="Save">
        </form>
            <br>
            ${message}
    </body>
</html>
