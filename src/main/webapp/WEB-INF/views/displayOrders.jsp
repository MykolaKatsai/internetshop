<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="orders" scope="request" type="java.util.List<katsai.nikolai.internetshop.models.Order>"/>
<%--
  Created by IntelliJ IDEA.
  User: Nik
  Date: 22.09.2019
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<table border="1px">
    <tr>
        <th>Order id</th>
        <th>User id</th>
        <th>Items</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.orderId}</td>
            <td>${order.user.userId}</td>
            <td><c:forEach var="item" items="${order.items}">
                <table>
                    <tr>
                        <td>${item.name}</td>
                        <td>${item.price}</td>
                    </tr>
                </table>
            </c:forEach></td>
            <td><a href="deleteOrder?order-id=${order.orderId}">DELETE</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
