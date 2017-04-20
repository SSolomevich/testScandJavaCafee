<%--
  Created by IntelliJ IDEA.
  User: 15
  Date: 19.04.2017
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    </style>
</head>
<body>
<h1>Заказ принят!</h1>
<form action="CoffeeOrderController" method="post">
<div class="table">
    <table>
        <tr>
            <th class="th">Подтверждение</th>
        </tr>
        <tr>
            <td class="td2">Ваш заказ принят</td>
        </tr>
    </table>
</div>
<%--<a href="CoffeeOrderController">Вернуться в магазин </a>--%>
<br>
</form>
<a href="CoffeeTypeController">Вернуться в магазин</a>

</body>
</html>
