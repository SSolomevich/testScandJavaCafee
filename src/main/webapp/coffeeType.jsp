<%--
  Created by IntelliJ IDEA.
  User: 15
  Date: 13.04.2017
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Выбор сортов кофе</title>
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
            background-color: lightgray;
            text-align: center;
        }
        .td
        {
            font-family: "Times New Roman", sans-serif;
            font-size: 14px;
            /*padding: 10px ;*/
            /*height: 40px;*/
            /*width: 150px;*/
            background-color: gainsboro;
            text-align: center;
        }
        .i
        {
            font-family: "Times New Roman", sans-serif;
            font-size: 14px;
            height: 20px;
            width: 20px;
            background-color: gainsboro;
            text-align: center;
        }
        .p1
        {
           color: red;
            font-size: 14px;
        }
        .p2
        {
            font-size: 14px;
        }
        .input
        {
         text-align: right;
        }

    </style>
</head>
<body>

<form action="CoffeeTypeController" method="post">
    <div class="table">
        <table>
            <tr>
                <th class="th" width="20px"> </th>
                <th class="th" width="100px">Название</th>
                <th class="th" width="50px">Цена</th>
                <th class="th" width="40px">Количество</th>
            </tr>

            <c:forEach var="list" items="${list}">
            <tr>
                <td class="td">  <input type="checkbox" name="box" value="${list.id}">  </td>
                <td class="td">${list.type_name}</td>
                <td class="td"width="70px">${list.price}</td>
                <td class="i"> <input type="text" name="q" maxlength="2"  size="14"  width="20px"/></td>
            </tr>
            </c:forEach>
            <tr>
                <td class="td" colspan="4"> <input type="submit" name="success" value="Заказать" class="input"> </td>
            </tr>

        </table>
    </div>
    <a class="p1">*</a><a class="p2"> - каждая третья чашка бесплатно.</a>
</form>
</body>
</html>
