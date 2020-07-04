<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>Фильмы</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link type="text/css" rel="stylesheet" href="../css/style.css">
    <style>
        input[name='id']{
            display: none;
        }
    </style>
</head>
<body>
<h2 class="name">Каталог фильмов</h2>
<div>
    <ul>
        <c:forEach items="${services}" var="service">
            <li>
                <c:choose>
                    <c:when test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                        <div class="admin-list">
                            <form method="post" action='/change_service' name='new_cost' >

                                <input name="id" value=${service.id} readOnly />

                                <input value=${service.name} name="name"/>

                                <input value=${service.cost} name="cost"/>

                                <input value=${service.genre} name="genre"/>

                                <button type="submit">Изменить</button>

                                <a class="remove" href="/remove_service?serviceId=${service.id}">Х</a>

                            </form>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="user-list line">
                            <div class="info">Название: ${service.name}</div>
                            <div class="info">Жанр: ${service.genre}</div>
                            <div class="info">Стоимость: ${service.cost} р.</div>

                            <c:if test="${pageContext.request.isUserInRole('ROLE_USER') && (!(pageContext.request.isUserInRole('ROLE_ADMIN') || pageContext.request.isUserInRole('ROLE_MANAGER')))}">
                                <button><a href="to_basket?serviceId=${service.id}&userName=${pageContext.request.userPrincipal.name}">Добавить в корзину</a></button>
                            </c:if>

                        </div>
                    </c:otherwise>
                </c:choose>
            </li>
        </c:forEach>
    </ul>
    <c:choose>
        <c:when test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
            <div class="line">
                <button class="new_service"><a href="/add_service">Добавить фильм</a></button>
            </div>
        </c:when>
        <c:otherwise>
            <div>
                <c:if test="${pageContext.request.isUserInRole('ROLE_USER') && (!(pageContext.request.isUserInRole('ROLE_ADMIN') || pageContext.request.isUserInRole('ROLE_MANAGER')))}">
                    <div class="line">
                        <button class="new_service"><a href="/basket?userName=${pageContext.request.userPrincipal.name}">Перейти к корзине</a></button>
                    </div>
                </c:if>
            </div>

        </c:otherwise>

    </c:choose>

    <a href="/" class="index">Главная</a>
</div>

</body>

</html>

