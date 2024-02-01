<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/static/css/book/books.css">
    <title>Books</title>
</head>
<body>
<jsp:include page="navbar.jsp" />
<form action="/books/create" method="get">
    <input type="submit" value="Create book">
</form>
<h1>All Books</h1>
<table>
    <tr>
        <th>#</th>
        <th>Id</th>
        <th>Title</th>
        <th>Author</th>
        <th>Price</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${books}" var="book" varStatus="counter">
        <tr>
            <td>${counter.count}</td>
            <td>${book.id}</td>
            <td><a href="/books/${book.id}"><c:out value="${book.title}" /></a></td>
            <td>${book.author}</td>
            <td>${book.price}</td>
            <td>
                <form action="/books/edit/${book.id}" method="get">
                    <input type="submit" value="Edit">
                </form>
            </td>
            <td>
                <form action="/books/delete/${book.id}" method="post">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>