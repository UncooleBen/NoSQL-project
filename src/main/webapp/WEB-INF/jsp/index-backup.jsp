<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Steamed Egg</title>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.4.1.min.js"></script>
    <a href=""><h1>Steamed Egg Database</h1></a>
    <meta charset="UTF-8">
</head>
<script>
    function appDetail(appId) {
        window.location = "detail?appid=" + appId;
    }

    function toPage(page) {
        window.location = "list?page=" + page;
    }
</script>
<body>
<div>
    <table id="id_game_table">
        <tr>
            <td>Games</td>
        </tr>
        <c:forEach varStatus="i" var="app" items="${appList}">
            <tr>
                <td width="150">${app.name }</td>
                <td width="250">${app.type}</td>
            </tr>
            <tr>
                <td><img src=${app.headerURL} onclick=appDetail(${app.steamAppId})></td>
                <td>${(empty app.latestPrice) ? "免费" : "￥"}${(empty app.latestPrice) ? "" : app.latestPrice}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<div>
    <table id="id_page_table">
        <tr>
            <td><b>${firstPages[0]}</b></td>
            <c:forEach begin="1" varStatus="i" var="pageNumber" items="${firstPages}">
                <td onclick=toPage(${pageNumber})>${pageNumber}</td>
            </c:forEach>
            <td>......</td>
            <c:forEach varStatus="i" var="pageNumber" items="${secondPages}">
                <td onclick=toPage(${pageNumber})>${pageNumber}</td>
            </c:forEach>
        </tr>
    </table>
</div>
</body>
</html>