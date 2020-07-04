<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Регистрация</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
<div>
    <form method="POST" name="userForm" action="/registration">
        <h2 class="name">Регистрация</h2>
        <div class="line">
            <input type="text" name="username" placeholder="Username"/>
        </div>
        <div class="line">
            <input type="password" name="password" placeholder="Password"/>
        </div>
        <div class="line">
            <input type="password" name="passwordConfirm"
                   placeholder="Confirm your password"/>
        </div>

        <div class="line">
            <button type="submit">Зарегистрироваться</button>
        </div>

        <a href="/" class="index">Главная</a>

    </form>

</div>
</body>
</html>
