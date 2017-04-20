<%--
  Created by IntelliJ IDEA.
  User: 15
  Date: 16.04.2017
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
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
            background-color: gainsboro;
            text-align: right;
        }
        .i
        {
            font-family: "Times New Roman", sans-serif;
            font-size: 14px;
            height: 20px;
            width: 210px;
            background-color: gainsboro;
            text-align: center;
        }
        .i2
        {
            font-family: "Times New Roman", sans-serif;
            font-size: 14px;
            height: 20px;
            width: 200px;
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
    </style>
</head>
<body>
<form action="CoffeeOrderController" method="post">

<div class="table">
    <table>
        <tr>
            <td class="th"> </td>
            <th class="th" >Доставка</th>

        </tr>


            <tr>
                <td class="td">  ФИО </td>
                <td class="i" > <input type="text" name="NameAndFamily" maxlength="50"  size="14"  class="i2"/></td>
            </tr>
        <tr>
            <td class="td">  Адрес </td>
            <td class="i" > <input type="text" name="Address" maxlength="50"  size="14"  class="i2"/></td>
        </tr>
        <tr>

            <td class="td"> </td>

            <td class="td2"> <input type="submit" name="success" value="Заказать" class="input"> </td>
        </tr>
    </table>
</div>
<br>

<div class="table">
    <table >
        <tr>
            <th class="th" width="100px">Название</th>
            <th class="th" width="50px">Цена</th>
            <th class="th" width="40px">Количество</th>
            <th class="th" width="20px">Всего </th>
        </tr>
        <c:forEach items="${type}" var="type">
            <tr>
                <td class="td2" width="100px">${type.type_name}</td>
                <td class="td2" width="50px">${type.price}</td>
                <td class="td2" width="40px">${type.quantity}</td>
                <td class="td2" width="20px">${type.quantity*type.price}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</form>
<a href="CoffeeTypeController">Вернуться в магазин </a>
</body>
</html>
