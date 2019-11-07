<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="bucket" scope="request" type="katsai.nikolai.internetshop.models.Bucket"/>
<%--
  Created by IntelliJ IDEA.
  User: Nik
  Date: 21.09.2019
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bucket</title>
</head>
<body>
<table border="1px">
    <tr>
        <th>Item id</th>
        <th>Name</th>
        <th>Price</th>
        <th>Delete from bucket</th>
    </tr>
    <c:forEach var="item" items="${bucket.items}">
        <tr>
            <td>${item.itemId}</td>
            <td>${item.name}</td>
            <td>${item.price}</td>
            <td><a href="deleteItemFromBucket?item-id=${item.itemId}">DELETE</a></td>
        </tr>
    </c:forEach>
</table>
<hr>
<a href="addNewOrder">
    <button>Make order</button>
</a>
</body>
</html>
