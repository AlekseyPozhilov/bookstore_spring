<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <title>Book</title>
    <link rel="stylesheet" type="text/css" href="/css/book/book.css">
</head>

<body>
    <h1>Book</h1>
    <div class="book-details">
        <p><strong>Id:</strong> <c:out value="${book.id}" /></p>
        <p><strong>Title:</strong> <c:out value="${book.title}" /></p>
        <p><strong>Author:</strong> <c:out value="${book.author}" /></p>
        <p><strong>Price:</strong> <c:out value="${book.price}" /></p>
        <p><strong>Year of Publishing:</strong> <c:out value="${book.yearOfPublishing}" /></p>
    </div>

    <a href="getAll" class="back-button">Back</a>
</body>

</html>