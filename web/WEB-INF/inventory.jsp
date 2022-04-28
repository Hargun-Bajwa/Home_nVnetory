<%-- 
    Document   : inventory
    Created on : Jul 22, 2021, 7:51:54 PM
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
        <title>Inventory Page</title>
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
        <h4> Inventory for ${username}</h4>
         <table border="1" cellpadding="3">
            <tr>
                <th>Category</th>
                <th>Name</th>
                <th>Price</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
            <c:forEach items="${items}" var="item">
            <tr>
                <td>${item.category.categoryName}</td>
                <td>${item.itemName}</td>
                <td>$${item.price}</td>
                <td><form  method="post">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="itemID" value="${item.itemID}">
                <input type="submit" value="Delete">
            </form></td>
            <td><a href="inventory?action=edit&amp;itemID=${item.itemID}">Edit</a></td>
            </tr>
            </c:forEach>
             </table>
         <c:if test="${selecteditem eq null}">
            <h4>Add Item</h4>
            <br>
            <form method="post">
            Category:
            <select name="category">
            <c:forEach items="${categories}" var="category">
                <option value="${category.categoryID}" >${category.categoryName}</option>
            </c:forEach>
            </select>
            <br>
            Name: 
            <input type="text" name="itemName" value="" >
            <br>
            Price: 
            <input type="number" name="itemPrice" value="" min="0.1" placeholder="0.1" step=".01"><br>
            <input type="hidden" name="action" value="add">
            <input type="submit" value="Save">
            </form>
            <c:if test="${error}">
                <p>Any input cannot be empty.</p>
            </c:if>
                <p>${checker}</p>
         </c:if>
                
                
                <c:if test="${selecteditem ne null}">
            <h4>Edit Item</h4>
            <br>
            <form method="post" action="inventory">
            Category:
            <select name="category">
            <c:forEach items="${categories}" var="category">
                <option value="${category.categoryID}"  >${category.categoryName}</option>
            </c:forEach>
            </select>
            <br>
            Name: 
            <input type="text" name="itemName" value="${selecteditem.itemName}" >
            <br>
            Price: 
            <input type="number" name="itemPrice" value="${selecteditem.price}" min="0.1" placeholder="0.1" step=".01"><br>
            <input type="hidden" name="itemID" value="${selecteditem.itemID}">
            <input type="hidden" name="action" value="update">
            <input type="submit" value="Save">
            </form>
            <c:if test="${error}">
                <p>Any input cannot be empty.</p>
            </c:if>
                <p>${checker}</p>
         </c:if>
    </body>
</html>
