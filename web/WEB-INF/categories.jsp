<%-- 
    Document   : categories
    Created on : Aug 14, 2021, 8:43:42 PM
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
        <title>Categories Page</title>
    </head>
    <body>
        <h1>Manage Categories</h1>
        <h4>Menu</h4>
        <ul>
            <li>
                <a href="inventory">Inventory</a>
            </li>
            <li>
                <a href="admin">Admin</a>
            </li>
            <li>
                <a href="login?logout" name="logout">Logout</a>
            </li>
            
        </ul>
        <table border="1" cellpadding="5">
            <tr>
                <th>Category ID</th>
                <th>Category Name</th>
                <th>Edit</th>
            </tr>
            <c:forEach var="category" items="${categories}">
                <tr>
                    <td>${category.categoryID}</td>
                    <td>${category.categoryName}</td>
                    
                     <td><a href="categories?action=edit&amp;categoryID=${category.categoryID}">Edit</a></td>
             </c:forEach>         
        </table>
                    <c:if test="${selectedcategory eq null}">  <br> 
            <form action="categories" method="POST">
                <h1>Add Category</h1>
                Name:
                <input type="text" name="catname"><br>
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Save">
                <br>
            </form>
        </c:if>

        <c:if test="${selectedcategory ne null}">  <br> 
            <form action="categories" method="POST">
                <h2>Edit Category</h2>
                Name:  
                <input type="text" name="catname" value="${selectedcategory.categoryName}"><br>
                <input type="hidden" name="catID" value="${selectedcategory.categoryID}">
                <input type="hidden" name="action" value="update">
                <input type="submit" value="Save">
                <br>
            </form>
        </c:if>
    <c:if test="${errorMssg}">
                <p>Any input cannot be empty.</p>
            </c:if>
    </body>
</html>
