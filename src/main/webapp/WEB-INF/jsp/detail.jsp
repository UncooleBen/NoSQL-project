<%--
  Created by IntelliJ IDEA.
  User: Juntao Peng
  Date: 12/23/2019
  Time: 1:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${app.name} - Steamed Egg Database</title>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.4.1.min.js"></script>
    <a href="" ><h1>${app.name} - Steamed Egg Database</h1></a>
    <meta charset="UTF-8">
</head>
<body background=${app.backgroundURL}>
<div>
    <table id="id_table">
        <tr>
            <td width="150">${app.name }</td>
            <td width="250">${app.type}</td>
        </tr>
        <tr>
            <td><img src = ${app.headerURL} ></td>
            <td>${(empty app.latestPrice) ? "免费" : "￥"}${(empty app.latestPrice) ? "" : app.latestPrice}</td>
        </tr>
        <tr>
            <td>
                ${app.detailedDescription}
            </td>
        </tr>
    </table>
    <div id="id_dynamic_div"></div>
</div>
</body>
</html>
