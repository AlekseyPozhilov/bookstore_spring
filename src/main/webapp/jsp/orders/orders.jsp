<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/orders/orders.css">
</head>
<body>
    <jsp:include page="../navbar.jsp" />
    <h1>All orders</h1>
    <table>
        <tr>
            <th>#</th>
            <th>Id</th>
            <th>Total cost</th>
            <th>Status</th>
            <th>Items</th>
        </tr>
        <c:forEach items="${orders}" var="order" varStatus="counter">
            <tr>
                <td>${counter.count}</td>
                <td><a href="bookstore?command=order&id=${order.id}">${order.id}</a></td>
                <td>${order.totalCost}</td>
                <td>${order.status}</td>
                <td>${order.items}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>