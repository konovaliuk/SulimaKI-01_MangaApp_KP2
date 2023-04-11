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
    <title>Login</title>
</head>
<body>
<a href="/">Back to Home</a>
<br>

<h1>Login Page</h1>
<form action="login" method="post">

    <label for="login_email">Your email:</label><br>
    <input type="text" id="login_email" name="login_email" placeholder="ivan.mazepa@ukr.net"/><br>

    <label for="login_password">Your password:</label><br>
    <input type="password" id="login_password" name="login_password" placeholder="your password"/>
    <br/>
    ${errorLoginPassMessage}
    <br/>
    <input type="submit" />
</form>
<a href="register">register</a>

</body>
</html>
