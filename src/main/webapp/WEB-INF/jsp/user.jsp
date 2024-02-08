<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <title>User</title>
    <link rel="stylesheet" type="text/css" href="/css/user/user.css">
</head>

<body>
    <h1>User</h1>
    <div class="user-details">
        <p><strong>Id:</strong> <c:out value="${user.id}" /></p>
        <p><strong>First name:</strong> <c:out value="${user.firstName}" /></p>
        <p><strong>Email:</strong> <c:out value="${user.email}" /></p>
        <p><strong>Password:</strong> <c:out value="${user.password}" /></p>
    </div>

    <a href="getAll" class="back-button">Back</a>
</body>

</html>