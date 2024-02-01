<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/static/css/user/users.css">
    <title>Users</title>
</head>
<body>
<jsp:include page="navbar.jsp" />
<h1>All Users</h1>
<table>
    <tr>
        <th>#</th>
        <th>Id</th>
        <th>Email</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${users}" var="user" varStatus="counter">
        <tr>
            <td>${counter.count}</td>
            <td>${user.id}</td>
            <td><a href="/users/${user.id}"><c:out value="${user.email}" /></a></td>
            <td>
               <form action="/users/edit/${user.id}" method="get">
                   <input type="submit" value="Edit">
               </form>
            </td>
            <td>
                <form action="/users/delete/${user.id}" method="post">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
