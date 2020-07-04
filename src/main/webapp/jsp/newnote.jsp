<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:form="http://www.w3.org/1999/html">
<head>
    <title>Добавить новость</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"  />
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div>
    <form method="POST" name = "news" action="/addNews">
        <h2 class="name">Создание новости</h2>
        <div class="line">
            <label>Заголовок:</label>
            <input type="text" name="title"/>
        </div>
        <div class="line">
            <label>Текст:</label>
            <input type="text" name="text"/>
        </div>
        <div class="line">
            <button type="submit">Создать</button>
        </div>
        <a href="/" class="index">Главная</a>
    </form>

</div>

</body>

</html>
