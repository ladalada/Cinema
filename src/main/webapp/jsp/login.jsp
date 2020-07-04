<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Логин</title>
    <link type="text/css" rel="stylesheet" href="../css/style.css">

</head>
<body>
<div>
    <form method="POST" action="/login">
        <h2 class="name">Вход в систему</h2>
        <div class="line">
            <input name="username" type="text" placeholder="Username" autofocus="true"/>
        </div>
        <div class="line">
            <input name="password" type="password" placeholder="Password"/>
        </div>
        <div class="line">
            <button type="submit">Войти</button>
        </div>
        <div class="line">
            <a href="/registration" class="registration">Зарегистрироваться</a>
        </div>
        <a href="/" class="index">Главная</a>

    </form>

</div>

</body>

</html>