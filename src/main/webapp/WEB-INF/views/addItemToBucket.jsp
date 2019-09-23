<%--
  Created by IntelliJ IDEA.
  User: Nik
  Date: 21.09.2019
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add items to bucket</title>
</head>
<body>
<form action="addItemToBucket">
    <%--@declare id="bucket-id"--%>
    <%--@declare id="item-id"--%>
    <label for="bucket-id"><b>Bucket id</b></label>
    <input type="text" placeholder="Enter bucket id" name="bucket-id" required>

    <label for="item-id"><b>Item id</b></label>
    <input type="text" placeholder="Enter item id" name="item-id" required>
    <button type="submit">Add</button>
</form>
</body>
</html>
