<%--
  Created by IntelliJ IDEA.
  User: 15
  Date: 13.04.2017
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%--<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />--%>
<%--<c:set var="language" value="${param.language}" scope="session" />--%>
<c:set var="language" value="en_EN" scope="session" />
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html lang="${language}">
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
            background-color: #C0C0C7 ;
            text-align: center;
        }
        .td
        {
            font-family: "Times New Roman", sans-serif;
            font-size: 14px;
            /*padding: 10px ;*/
            /*height: 40px;*/
            /*width: 150px;*/
            /*text-align: center;*/
        }
        tr:nth-child(2n) {
            background: #f0f0f0; /* Цвет фона */
        }
        tr:nth-child(2n+1) {
            background: #E0E0E0; /* Цвет фона */
        }
        .i
        {
            font-family: "Times New Roman", sans-serif;
            font-size: 14px;
            height: 20px;
            width: 20px;
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
    <script type="text/javascript">
        function validLogin() {// java script function is created

            var valid = true;
            var validCount = false;
            var validBox = false;
            var validCountBox = false;
            for (var i = 0; i < document.frm1.q.length; i++) {
//            if (document.frm1.q[i].value.length > 1) {
//                alert("Количество чашек кофе одного вида не может превышать 9 штук!!");
//                valid = false;
//            }
                if (document.frm1.q[i].value > 20) {
                    alert("The number of cups of coffee of one kind can not exceed 20 pieces !! / " +
                        "Количество чашек кофе одного вида не может превышать 20 штук!!");
                    valid = false;
                }

                if (document.frm1.q[i].value!="" && document.frm1.q[i].value!=0) {
                    validCount = true;
                }

                if (document.frm1.box[i].checked) {
                    validBox = true;
                }

                if (document.frm1.q[i].value!="" && document.frm1.box[i].checked  && document.frm1.q[i].value!=0)  {
                    validCountBox = true;
                }

            }
            if (validCount == false && validBox == false) {
                <%--alert(<fmt:message key="label.validCount&Box" />);--%>
                alert("Indicate the number of cups and type of coffee! / " +
                    "Укажите количество чашек и тип кофе!");
                valid = false;}
            else if (validCount == false) {
                alert("Indicate the number of cups! / " +
                    "Укажите количество чашек!");
                <%--alert(<fmt:message key="label.validCount" />);--%>
                valid = false;}
            else if (validBox == false) {
                alert("Indicate the type of coffee! / " +
                    "Укажите тип кофе!");
                <%--alert(<fmt:message key="label.validBox" />);--%>
                valid = false;}
            else if (validCountBox == false) {
                alert("A selected number of cups must fit a selected type of coffee! / " +
                    "Выбранному типу кофе должно соответствовать некоторое количество чашек!");
                <%--alert(<fmt:message key="label.validCountBox" />);--%>
                valid = false;}

            return valid;
        }


    </script>

</head>
<body>

<script type="text/javascript">
//    function validCount() {
        if (${countTest} = "f")
            alert("Invalid number format! / " +
                "Ошибочный формат числа!");

//    }
</script>
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russion</option>
    </select>
</form>
<br>
<br>

<form name="frm1" action="CoffeeTypeController" method="post" onsubmit="return validLogin();">


    <div class="table" >
        <table cellspacing="0px" cellpadding="2px" border="0px">
            <tr>
                <th class="th" width="20px"></th>
                <th class="th" width="100px"><fmt:message key="label.name" /></th>
                <th class="th" width="50px"><fmt:message key="label.price" /></th>
                <th class="th" width="40px"><fmt:message key="label.count" /></th>
            </tr>

            <c:forEach var="list" items="${list}">
            <tr>
                <td class="td" align="center">  <input type="checkbox" name="box" value="${list.id}">  </td>
                <td class="td" align="left">${list.type_name}</td>
                <td class="td" width="70px" align="left">${list.price} TGR</td>
                <td class="i"> <input type="number" name="q" maxlength="2"  size="14"  width="20px"/></td>
            </tr>
            </c:forEach>
            <tr>
                <td class="td" colspan="4" align="right"> <input type="submit" name="success" value="<fmt:message key="label.submit" />" class="input"> </td>
            </tr>

        </table>
    </div>
    <a class="p1">*</a><a class="p2"><fmt:message key="label.star" /></a>
</form>
<%--<form name="contact_form" method="post" action="dumb.htm" onsubmit="return Formdata();">--%>


</body>
</html>
