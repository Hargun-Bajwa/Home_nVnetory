<%-- 
    Document   : admin
    Created on : Jul 22, 2021, 7:52:06 PM
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
        <title>Admin Page</title>
    </head>
    <body>
        <h1>Home Inventory</h1>
        <h4>Menu</h4>
        <ul>
            <li>
                <a href="inventory">Inventory</a>
            </li>
            <li>
                <a href="admin">Admin</a>
            </li>
            <li> <a href="categories"> Categories</li>
            <li>
                <a href="login?logout" name="logout">Logout</a>
            </li>
            
        </ul>
        
        <h2>Manage Users</h2>
        <h4>Click on true,false to cahnge the admin status or active status</h4>
         <table border="1" cellpadding="2">
            <tr>
                <th>Email</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Delete</th>
                <th>Edit</th>
                <th>is Admin?</th>
                <th>is Active?</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td><a href="admin?action=edit&amp;username=${user.username}">Edit</a></td>
                    <td><a href="admin?action=delete&amp;username=${user.username}" >Delete</a></td>
                    <td><a href="admin?action=changestatus&amp;username=${user.username}">${user.isAdmin}</td>
                    <td><a href="admin?action=changeactive&amp;username=${user.username}">${user.active}</td>
                </tr>
            </c:forEach>         
        </table>
 
        <c:if test="${selectedUser eq null}">  <br> 
            <form action="admin" method="POST">
                <h1>Add User</h1>
                Username:
                <input type="text" name="username"><br>
                Password:
                <input type="password" name="password"><br>
                Email:
                <input type="text" name="email"><br>
                First Name:
                <input type="text" name="first_name" ><br>
                Last Name:
                <input type="text" name="last_name"><br>
                
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Save">
                <br>
            </form>
        </c:if>

        <c:if test="${selectedUser ne null}">  <br> 
            <form action="admin" method="POST">
                <h2>Edit User</h2>
                Username:  <input type="text" name="username" value="${selectedUser.username}" readonly="readonly"><br>
                Password:  <input type="password" name="password" value="${selectedUser.password}"><br>
                Username:  <input type="text" name="email" value="${selectedUser.email}"><br>
                First Name:<input type="text" name="first_name" value="${selectedUser.firstName}"><br>
                Last Name: <input type="text" name="last_name" value="${selectedUser.lastName}"><br>
                <br>
                <input type="hidden" name="email" value="${selectedUser.email}">
                <input type="hidden" name="action" value="update">
                <input type="submit" value="Save">
                <br>
            </form>
        </c:if>
    <c:if test="${errorMssg}">
                <p>Any input cannot be empty.</p>
            </c:if>
    <c:if test="${errorMssgAdmin}">
                <h4> Admins can neither be changed nor be deleted.</h4>
            </c:if>
    </body>
</html>
