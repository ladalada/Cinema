<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html >

<html>
<head>
    <meta charset="utf-8">
    <title>Главная</title>
    <link type="text/css" rel="stylesheet" href="../css/style.css">
</head>

<body>
<h2 class="name">Онлайн-кинотеатр</h2>
<ul class="list-group">

    <sec:authorize access="!isAuthenticated()">
        <h3>Добро пожаловать, гость!</h3>
        <li class="list-group-item"><a href="/login">Войти</a></li>
        <li class="list-group-item"><a href="/registration">Зарегистрироваться</a></li>
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <h3>Добро пожаловать, ${pageContext.request.userPrincipal.name}!</h3>

        <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
            <h3>Ваша роль: администратор</h3>
        </c:if>

        <c:if test="${pageContext.request.isUserInRole('ROLE_MANAGER')}">
            <h3>Ваша роль: менеджер</h3>
        </c:if>

        <li class="list-group-item"><a href="/logout">Выйти</a></li>
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <li class="list-group-item"><a href="/news">Новости</a></li>
    </sec:authorize>

    <li class="list-group-item"><a href="/services">Каталог фильмов</a></li>

    <c:if test="${pageContext.request.isUserInRole('ROLE_USER') && (!(pageContext.request.isUserInRole('ROLE_ADMIN') || pageContext.request.isUserInRole('ROLE_MANAGER')))}">
        <li class="list-group-item"><a href="/basket?userName=${pageContext.request.userPrincipal.name}">Корзина</a></li>
    </c:if>

    <c:if test="${pageContext.request.isUserInRole('ROLE_USER') && (!(pageContext.request.isUserInRole('ROLE_ADMIN') || pageContext.request.isUserInRole('ROLE_MANAGER')))}">
        <li class="list-group-item"><a href="/myorders?userName=${pageContext.request.userPrincipal.name}">Мои заказы</a></li>
    </c:if>

    <c:if test="${pageContext.request.isUserInRole('ROLE_USER') && (!(pageContext.request.isUserInRole('ROLE_ADMIN') || pageContext.request.isUserInRole('ROLE_MANAGER')))}">
        <li class="list-group-item"><a href="/mystatistics?userName=${pageContext.request.userPrincipal.name}">Моя статистика</a></li>
    </c:if>

    <c:if test="${pageContext.request.isUserInRole('ROLE_USER') && (!(pageContext.request.isUserInRole('ROLE_ADMIN') || pageContext.request.isUserInRole('ROLE_MANAGER')))}">
        <li class="list-group-item"><a href="/recommend?userName=${pageContext.request.userPrincipal.name}">Рекомендации</a></li>
    </c:if>

    <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
        <li class="list-group-item"><a href="/users">Все пользователи</a></li>
    </c:if>

    <c:if test="${pageContext.request.isUserInRole('ROLE_MANAGER')}">
        <li class="list-group-item"><a href="/statistics">Статистика по пользователям</a></li>
    </c:if>

    <c:if test="${pageContext.request.isUserInRole('ROLE_MANAGER')}">
        <li class="list-group-item"><a href="/orders">Заказы пользователей</a></li>
    </c:if>

</ul>
</body>
</html>