<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>Новости</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link type="text/css" rel="stylesheet" href="../css/style.css">
</head>
<body>
<h2 class="name">Новости</h2>
<div>
    <div>
        <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
            <button onclick=add()><a href="/add_news">Добавить новость</a></button>
        </c:if>
    </div>
    <c:if test="${not empty news}">
        <ul class="news">
            <c:forEach items="${news}" var="news">
                <li>
                    <h2>${news.title}</h2>
                    <div>${news.text}</div>
                </li>
            </c:forEach>
        </ul>
    </c:if>

    <c:if test="${empty news}">
        <h3 class="name">Новостей нет!</h3>
    </c:if>

    <a href="/" class="index">Главная</a>

</div>

</body>
</html>