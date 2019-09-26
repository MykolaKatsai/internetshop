<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="users" scope="request" type="java.util.List<mate.academy.internetshop.models.User>"/>
<%--
  Created by IntelliJ IDEA.
  User: Nik
  Date: 19.09.2019
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>To see user bucket click on bucket id</h3>
<table border="1px">
    <tr>
        <th>Id</th>
        <th>Login</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Bucket id</th>
        <th>Orders</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td><c:out value="${user.userId}"></c:out></td>
            <td><c:out value="${user.login}"></c:out></td>
            <td><c:out value="${user.name}"></c:out></td>
            <td><c:out value="${user.surname}"></c:out></td>
            <td><a href="getUserBucket?bucket-id=${user.bucketId}"><c:out value="${user.bucketId}"></c:out></a></td>
            <td><a href="getUserOrders?user-id=${user.userId}">orders</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
