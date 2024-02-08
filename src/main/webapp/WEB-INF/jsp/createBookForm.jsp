<%@ page contentType = "text/html; charset = UTF-8" language = "java" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/forms/universalForm.css">
<title>Register New Book</title>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>Register New Book</h1>

<form method="post" action="/books/create">

    <label for="title-input">Title:</label>
    <input id="title-input" name="title" type="text"/>
    <br/>
    <label for="author-input">Author:</label>
    <input id="author-input" name="author" type="text"/>
    <br/>
    <label for="isbn-input">ISBN:</label>
    <input id="isbn-input" name="isbn" type="text"/>
    <br/>
    <label for="price-input">PRICE: </label>
    <input id="price-input" name="price" type="number" required value="${book.price}"/>
    <br/>
    <label for = "numberOfPages-input">PAGES:</label>
    <input id = "numberOfPages-input" name = "numberOfPages" type = "number" value = "${requestScope.book.numberOfPages}" required/>
    <br/>
    <label for = "yearOfPublishing-input">yearOfPublishing:</label>
    <input id = "yearOfPublishing-input" name = "yearOfPublishing" type = "number" value = "${requestScope.book.yearOfPublishing}" required/>
    <br/>
    <input type="submit" value="Create">
</form>
</body>
</html>