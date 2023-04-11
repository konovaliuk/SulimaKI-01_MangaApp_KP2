<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Basic application menu</h1>
<br/>
<c:if test="${!(login_status)}">
    <a href="login">Login</a>
    <a href="register">Register</a>
</c:if>
<c:if test="${login_status}">
    <a href="logout">Logout</a>
    <a href="catalog">List of added mangas</a>
</c:if>
<c:out value="login status = ${login_status.toString()}" />
<br/>
<h2>Static links</h2>
<br/>
<a href="/">Home</a>
<a href="login">Login</a>
<a href="register">Register</a>
<a href="logout">Logout</a>
<a href="catalog">List of added mangas</a>
</body>
</html>