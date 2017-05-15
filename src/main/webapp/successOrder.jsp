<%--
  Created by IntelliJ IDEA.
  User: 15
  Date: 19.04.2017
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html>
<head>
    <title>Успешный заказ</title>
    <style type="text/css">
        .table
        {
            margin-left: 5px;
            margin-top: 5px;
            border-collapse: collapse;
            font-family: Arial, sans-serif;
            color: #333;
            line-height: 2px;
            border: 3px;
        }
        .th
        {
            font-family: "Times New Roman", sans-serif;
            font-weight: bold;
            font-size: 14px;
            /*padding: 10px ;*/
            /*height: 40px;*/
            /*width: 150px;*/

            text-align: center;
            background-color: #C0C0C7;

        }


        .td
        {
            font-family: "Times New Roman", sans-serif;
            font-size: 14px;
            font-weight: bold;
            /*padding: 10px ;*/
            /*height: 40px;*/
            /*width: 150px;*/
            background-color: gainsboro;
            text-align: center;
        }
        .td2
        {
            font-family: "Times New Roman", sans-serif;
            font-size: 14px;
            /*padding: 10px ;*/
            /*height: 40px;*/
            /*width: 150px;*/

            text-align: center;
        }
        .return
        {
            font-size: 11px;
        }
        tr:nth-child(2n) {
            background: #f0f0f0;/* Цвет фона */
        }
        tr:nth-child(2n+1) {
            background:  #E0E0E0; /* Цвет фона */
        }
    </style>
</head>
<body>

<form action="CoffeeOrderController" method="post">
<div class="table">
    <table cellspacing="0px" cellpadding="2px" border="0px">
        <tr>
            <th class="th" width="300px"><fmt:message key="label.confirmation" /></th>
        </tr>
        <tr>
            <td class="td2" ><fmt:message key="label.accepted" /></td>
        </tr>
    </table>
</div>

<br>
</form>
<a href="CoffeeTypeController" class="return"><fmt:message key="label.back_to_Shop" /></a>

</body>
</html>
