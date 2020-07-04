<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
    <title>Корзина</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div>
    <h2 class="name">Корзина</h2>
    <c:if test="${not empty basket}">
        <div class="basket">
            <table border="1">
                <thead>
                <tr>
                    <td>Фильм</td>
                    <td>Жанр</td>
                    <td>Цена</td>
<%--                    <td>Время</td>--%>
<%--                    <td>Зал</td>--%>
                </tr>
                <thead>
                <tbody>
                <c:forEach items="${basket.service}" var="service">
                    <tr>
                        <td name="name">${service.services.name}</td>
                        <td name="genre">${service.services.genre}</td>
                        <td name="price">${service.price}</td>
<%--                        <td name="time">${service.services.timetable.time}</td>--%>
<%--                        <td name="hall">${service.services.timetable.hall}</td>--%>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="actions">
                <button><a href="/clear_order?basketId=${basket.id}">Очистить корзину</a></button>
                <button><a href="/make_order?basketId=${basket.id}">Сделать заказ</a></button>
            </div>
        </div>
    </c:if>
    <c:if test="${empty basket}">
        <h3 class="name">Корзина пуста!</h3>
    </c:if>
    <a href="/" class="index">Главная</a>
</div>

</body>

</html>