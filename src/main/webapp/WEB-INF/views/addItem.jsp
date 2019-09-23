<%--
  Created by IntelliJ IDEA.
  User: Nik
  Date: 20.09.2019
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Item</title>
</head>
<body>
<form action="addItem" method="post">
    <div class="container">
        <%--@declare id="item-prise"--%><%--@declare id="item-name"--%>
        <h1>Add Item</h1>
        <p>Please fill in this form to create an item.</p>
        <hr>

        <label for="item-name"><b>Name</b></label>
        <input type="text" placeholder="Enter Item Name" name="item-name" required>

        <label for="item-prise"><b>Prise</b></label>
        <input type="text" placeholder="Enter Item Prise" name="item-prise" required>

        <hr>

        <button type="submit" class="add-item-btn">Add Item</button>
    </div>
</form>
</body>
</html>
