<%-- 
    Document   : userAccount
    Created on : Aug 12, 2021, 5:46:10 PM
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
        <title>Profile Page</title>
    </head>
    <body>
<h1>Home Inventory</h1>
        <h4>Menu</h4>
        <ul>
            <li>
                <a href="inventory">Inventory</a>
            </li>
            <li>
                <a href="profile">Edit User Profile</a>
            </li>
            <li>
                <a href="admin">Admin</a>
            </li>
            <li>
                <a href="login?logout" name="logout">Logout</a>
            </li>
        </ul>
        <h2>User Profile</h2>
        <form method="POST" action="">
            First Name: <input type="text" name="firstname" value="${firstName}"><br>
            Last Name: <input type="text" name="lastname" value="${lastName}"><br>
            Username: <input type="text" name="username" value="${userName}" readonly><br>
            Email: <input type="text" name="email" value="${Email}"><br>
            Password: <input type="password" name="password" value="${passWord}"><br>
            Active Status: <input type="checkbox" name="active" value="true" checked="checked"><br>
            <input type="hidden" name="action" value="edit">
            <input type="submit" value="Update">
        </form>
            <c:if test="${error}">
                <br> Any input cannot be empty.
            </c:if>
            <c:if test="${errorAdmin}">
                <br> Error,  Admins cannot be deactivated from this page
            </c:if>
    </body>
</html>
     
