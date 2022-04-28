<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link  rel="stylesheet" href="<c:url value="/main.css" />" type="text/css">
        <title>Forgot Password</title>
    </head>
    <body>
        <h2>Forgot Password</h2>
        <p>Please enter your Username to retrieve your password</p>
        <form method="post" action="forgot">
        Username: <input type="text" name="username"> <br />
        <input type="submit" value="Submit">
        </form>
        <c:if test="${mssgTest}">
            <p>${message}</p>
        </c:if>
        <c:if test="${done}">
            <br> Email sent Successfully
        </c:if>
    </body>
</html>