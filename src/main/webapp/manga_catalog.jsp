<%--
  Created by IntelliJ IDEA.
  User: vanom
  Date: 20.03.2023
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of mangas</title>
</head>
<body>
    <a href="/">Back to Home</a>
    <br>
    <table>
        <tr>
            <th>Name of Manga</th>
            <th>Name of Artist</th>
            <th>Name of Author</th>
        </tr>
        <c:forEach var="manga" items="${mangas}">
<%--            <tr><td>${manga.getMangaName()}<c:out value="${manga.getMangaName()}"/></td> </tr>--%>
            <tr>
                <td>${manga.getMangaName()}</td>
                <td>${creators.get(manga.getArtistId())}</td>
                <td>${creators.get(manga.getAuthorId())}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
