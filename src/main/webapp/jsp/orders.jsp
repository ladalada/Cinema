<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>Заказы</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link type="text/css" rel="stylesheet" href="../css/style.css">
</head>
<body>
<div>
    <h2 class="name">Заказы</h2>

    <c:if test="${not empty orders}">
        <div class="orders">
            <table border="1">
                <thead>
                <td>Номер заказа</td>
                <c:if test="${pageContext.request.isUserInRole('ROLE_MANAGER')}">
                    <td>Пользователь</td>
                </c:if>
                <td>Дата</td>
                <td>Фильмы</td>
                <c:if test="${pageContext.request.isUserInRole('ROLE_MANAGER')}">
                    <td></td>
                </c:if>
                </thead>
                <tbody>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td>${order.id}</td>
                        <c:if test="${pageContext.request.isUserInRole('ROLE_MANAGER')}">
                            <td>${order.user.username}</td>
                        </c:if>
                        <td>${order.date}</td>
                        <td>
                            <ol>
                                <c:set var="orderPrice" value="${0}" />
                                <c:forEach items="${order.service}" var="service">
                                    <c:set var="orderPrice" value="${orderPrice + service.price}" />
                                    <li>
                                        <c:if test="${pageContext.request.isUserInRole('ROLE_MANAGER')}">
                                            <p>${service.services.name} (${service.services.genre})</p>
                                        </c:if>
                                        <c:if test="${pageContext.request.isUserInRole('ROLE_USER') && (!(pageContext.request.isUserInRole('ROLE_ADMIN') || pageContext.request.isUserInRole('ROLE_MANAGER')))}">
                                            <a href="/movie?movieName=${service.services.name}&movieGenre=${service.services.genre}&userName=${pageContext.request.userPrincipal.name}">
                                                <p>${service.services.name} (${service.services.genre})</p>
                                            </a>
                                        </c:if>

                                    </li>
                                </c:forEach>
                                Стоимость заказа: ${orderPrice} р.
                            </ol>
                        </td>
                        <c:if test="${pageContext.request.isUserInRole('ROLE_MANAGER')}">
                            <td><a href="/order_r?orderId=${order.id}" class="remove">X</a></td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
    <c:if test="${empty orders}">
        <h3 class="name">Заказов нет!</h3>
    </c:if>

    <a href="/" class="index">Главная</a>
</div>

</body>

</html>