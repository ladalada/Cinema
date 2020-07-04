<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Рекомендации</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link type="text/css" rel="stylesheet" href="../css/style.css">
    <style>
        input[name='id']{
            display: none;
        }
    </style>
</head>
<body>

<div>
    <h2 class="name">Рекомендации</h2>
    <c:if test="${not empty recommend}">

        <ul>
            <c:forEach items="${recommend}" var="service">
                <li>
                    <c:choose>
                        <c:when test="${!(pageContext.request.isUserInRole('ROLE_ADMIN'))}">
                            <div class="user-list line">
                                <div class="info">Название: ${service.name}</div>
                                <div class="info">Жанр: ${service.genre}</div>
                                <div class="info">Стоимость: ${service.cost} р.</div>

                                <c:if test="${pageContext.request.isUserInRole('ROLE_USER') && (!(pageContext.request.isUserInRole('ROLE_ADMIN') || pageContext.request.isUserInRole('ROLE_MANAGER')))}">
                                    <button><a href="to_basketFromRecommend?serviceId=${service.id}&userName=${pageContext.request.userPrincipal.name}">Добавить в корзину</a></button>
                                </c:if>

                            </div>
                        </c:when>
                    </c:choose>
                </li>
            </c:forEach>
        </ul>
        <c:choose>
            <c:when test="${pageContext.request.isUserInRole('ROLE_USER') && (!(pageContext.request.isUserInRole('ROLE_ADMIN') || pageContext.request.isUserInRole('ROLE_MANAGER')))}">
                <div class="line">
                    <button class="new_service"><a href="/basket?userName=${pageContext.request.userPrincipal.name}">Перейти к корзине</a></button>
                </div>

            </c:when>

        </c:choose>

    </c:if>

    <c:if test="${empty recommend}">
        <h3 class="name">Для получения рекомендаций необходимо сделать заказ!</h3>
    </c:if>

    <a href="/" class="index">Главная</a>
</div>

</body>

</html>