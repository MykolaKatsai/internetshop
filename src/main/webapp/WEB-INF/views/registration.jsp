<%--
  Created by IntelliJ IDEA.
  User: Nik
  Date: 20.09.2019
  Time: 0:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link href="resources/styles.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<form action="registration" method="post">
    <div class="container">
        <%--@declare id="psw"--%><%--@declare id="psw-repeat"--%>
        <%--@declare id="user-name"--%><%--@declare id="user-surname"--%><%--@declare id="login"--%><h1>Register</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>

        <label for="login"><b>Login</b></label>
        <input type="text" placeholder="Enter Login" name="login" required>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>

        <label for="psw-repeat"><b>Repeat Password</b></label>
        <input type="password" placeholder="Repeat Password" name="psw-repeat" required>

        <label for="user-name"><b>Name</b></label>
        <input type="text" placeholder="Enter Name" name="user-name" required>

        <label for="user-surname"><b>Surname</b></label>
        <input type="text" placeholder="Enter Surname" name="user-surname" required>

        <hr>

        <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
        <button type="submit" class="register-btn">Register</button>
    </div>
</form>
</body>
</html>
