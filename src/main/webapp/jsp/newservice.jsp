<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:form="http://www.w3.org/1999/html">
<head>
    <title>Добавить фильм</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"  />
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div>
    <h2 class="name">Добавление фильма</h2>
    <form method="POST" name = "service" action="/addService">

        <label>Название</label>
        <input type="text" name="name" placeholder='Матрица'/>

        <label>Стоимость:</label>
        <input type="text" name="cost" placeholder='200'/> р.

        <label>Жанр:</label>
        <input type="text" name="genre" placeholder="фантастика"/>

        <button type="submit">Добавить</button>

    </form>
    <a href="/" class="index">Главная</a>

</div>

</body>

</html>
