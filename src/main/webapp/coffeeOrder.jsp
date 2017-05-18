<%--
  Created by IntelliJ IDEA.
  User: 15
  Date: 16.04.2017
  Time: 17:58
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
            background-color: #C0C0C7;
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

            text-align: center;
        }
        .td2
        {
            font-family: "Times New Roman", sans-serif;
            font-size: 14px;
            text-align: right;
        }
        .td3
        {
            font-family: "Times New Roman", sans-serif;
            font-size: 14px;
            text-align: left;
        }
        .td4
        {
            font-family: "Times New Roman", sans-serif;
            font-size: 14px;
            text-align: right;
            font-weight: bold;
        }

        .i
        {
            font-family: "Times New Roman", sans-serif;
            font-size: 14px;
            height: 20px;
            width: 210px;

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
        .p1 {
            color: red;
        }
        .return
        {
            font-size: 11px;
        }
        tr:nth-child(2n) {
            background: #f0f0f0; /* Цвет фона */
        }
        tr:nth-child(2n+1) {
            background: #E0E0E0; /* Цвет фона */
        }

    </style>
    <script type="text/javascript">
        function validLogin() {// java script function is created

            var valid = true;
//            var a = parseInt(document.frm1.q.value, 10);
//            var a = parseInt( document.frm2.Address.value, 10);
            if (document.frm2.Address.value.length < 4)
            { alert ( "!!!!!!!!!!!!!!!" );
//                document.frm1.q.focus();
                valid = false;

            }
            return valid ; }
    </script>
</head>
<body>

<form name="frm2"  action="CoffeeOrderController" method="post" onsubmit="return validLogin();">

<div class="table">
    <table cellspacing="0px" cellpadding="2px" border="0px">
        <tr> <th class="th" colspan="2"><fmt:message key="label.delivery" /></th> </tr>

        <tr>
            <td class="td"><fmt:message key="label.full_name" /></td>
            <td class="i" > <input type="text" name="NameAndFamily" maxlength="50"  size="14"  class="i2"/></td>
        </tr>

        <tr style="background-color: #F0F0F0">
            <td class="td" > <fmt:message key="label.address" /></td>
            <td class="i" > <input type="text" name="Address" maxlength="50"  size="14"  class="i2"/></td>
        </tr>

        <tr>
            <td class="td"> </td>
            <td class="td2"> <input type="submit" name="success" value="<fmt:message key="label.submit" />" class="input"> </td>
        </tr>
    </table>
</div>
<br>

<div class="table">
    <table cellspacing="0px" cellpadding="2px" border="0px">
        <tr>
            <th class="th" width="220px"><fmt:message key="label.name" /></th>
            <th class="th" width="50px"><fmt:message key="label.price" /></th>
            <th class="th" width="40px"><fmt:message key="label.count" /></th>
            <th class="th" width="60px"><fmt:message key="label.total" /></th>
        </tr>
        <c:forEach var="list" items="${list}">
            <tr>
                <td class="td3" >${list.type_name}</td>
                <td class="td2" >${list.price} TGR</td>
                <td class="td2" >${list.count}</td>
                <td class="td2" ><a class="p1">${list.count*list.price}</a> TGR</td>
            </tr>
        </c:forEach>

        <tr>
            <td class="td4" colspan="3"><fmt:message key="label.amount" />:</td>
            <td class="td2"><c:out value="${sum} TGR"/>  </td>
        </tr>
        <tr>
            <td class="td4" colspan="3"><fmt:message key="label.delivery" />:</td>
            <td class="td2"> <c:out value="${m} TGR"/> </td>
        </tr>
        <tr>
            <td class="td4" colspan="3"><fmt:message key="label.total" />:</td>
            <td class="td2"><c:out value="${m+sum} TGR"/></td>
        </tr>

    </table>
</div>

</form>
<a href="CoffeeTypeController" class="return"><fmt:message key="label.back_to_Shop" /></a>
</body>
</html>
