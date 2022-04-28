<%-- 
    Document   : login
    Created on : Jul 22, 2021, 7:50:12 PM
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
        <title>Login</title>
    </head>
    <h1>Home Inventory</h1>
    <h4>Login</h4>
        <form action="" method="post">
            Username: <input type="text" name="username" placeholder="Username"><br>
            Password: <input type="password" name="password" placeholder="password"><br>
            <input type="submit" value="Log in">
            <c:if test="${ErrorMssg}">
                <p>
                    Invalid Login.
                </p>
            </c:if>
            <c:if test="${ErrorMssgActive}">
                <p>
                    User is not Active, Contact Administrator for re-activation. OR if you are new member you must have verified email to login.
                </p>
            </c:if>
        </form>
    New to Home nVventory, Sign up today by clicking<a href="register"> here</a>
    <br>
    <a href="forgot">Forgot Password?</a>
    <c:if test="${registerDone}">
                <p>
                    User is registered.
                </p>
            </c:if>
    </body>
</html>
