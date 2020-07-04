<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>Просмотр фильма</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link type="text/css" rel="stylesheet" href="../css/style.css">
</head>
<body>
<div>
    <h2 class="name">Просмотр фильма</h2>

    <div class="statistics">
        <h3>${movieName} (${movieGenre})</h3>
    </div>
    <div class="movie">
        <img src="${pageContext.request.contextPath}/css/loading.gif"/>
    </div>

</div>

<a href="/myorders?userName=${pageContext.request.userPrincipal.name}" class="index">Мои заказы</a>
<a href="/" class="index">Главная</a>


</body>

</html>