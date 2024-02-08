<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cart</title>
    <link rel="stylesheet" type="text/css" href="/css/cart/cart.css">
</head>
<body>
<jsp:include page="navbar.jsp" />

<h1>Cart</h1>

<div class="cart-items">
    <table>
        <tr>
            <th>Title</th>
            <th>Author</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>
        <c:forEach items="${cartItems}" var="item">
            <tr>
                <td><c:out value="${order.title}" /></td>
                <td><c:out value="${order.author}" /></td>
                <td><c:out value="${order.price}" /></td>
                <td><c:out value="${order.quantity}" /></td>
            </tr>
        </c:forEach>
    </table>
</div>

<div>
   <a class="checkout-button" href="/html/unavailable.html">Checkout</a>
</div>

</body>
</html>