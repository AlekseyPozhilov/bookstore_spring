<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/orders/orders.css">
</head>
<body>
    <jsp:include page="navbar.jsp" />
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
                <td><c:out value="${counter.count}" /></td>
                <td><a href="/orders/${order.id}">"${order.id}" /></a></td>
                <td><c:out value="${order.totalCost}" /></td>
                <td><c:out value="${order.status}" /></td>
                <td>
                    <ul>
                        <c:forEach items="${order.items}" var="item">
                            <li>${item}</li>
                        </c:forEach>
                    </ul>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>