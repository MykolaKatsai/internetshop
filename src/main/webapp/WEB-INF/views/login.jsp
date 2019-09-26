<%--
  Created by IntelliJ IDEA.
  User: Nik
  Date: 24.09.2019
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div>${errorMsg}</div>
<form action="login" method="post">
    <div class="container">
        <%--@declare id="psw"--%><%--@declare id="login"--%><h1>Login</h1>
        <p>Please fill in this form to login.</p>
        <hr>

        <label for="login"><b>Login</b></label>
        <input type="text" placeholder="Enter Login" name="login" required>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>
        <p>
            <button type="submit" class="register-btn">Login</button>
        </p>
    </div>
</form>
</body>
</html>
