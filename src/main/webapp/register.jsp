<%--
  Created by IntelliJ IDEA.
  User: vanom
  Date: 19.03.2023
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<a href="/">Back to Home</a>
<a href="login">Login</a>
<br>
<h1>Registration</h1>

<form action="register" method="post">
    <label for="register_nickname">Choose your nickname:</label><br>
    <input type="text" id = "register_nickname" name="register_nickname" placeholder="your nickname"/><br>

    <label for="register_email">Choose your email:</label><br>
    <input type="text" id="register_email" name="register_email" placeholder="your email"/><br>

    <label for="register_password">Choose your password:</label><br>
    <input type="password" id="register_password" name="register_password" placeholder="your password"/><br>
    <input type="submit"/>
</form>

</body>
</html>
