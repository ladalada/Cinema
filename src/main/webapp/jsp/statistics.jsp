<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>Статистика</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link type="text/css" rel="stylesheet" href="../css/style.css">
</head>
<body>
<div>
    <h2 class="name">Статистика</h2>

    <c:if test="${not empty orders}">

        <c:if test="${pageContext.request.isUserInRole('ROLE_USER') && (!(pageContext.request.isUserInRole('ROLE_ADMIN') || pageContext.request.isUserInRole('ROLE_MANAGER')))}">

            <ul class="statistics">
                <h3>Фильмы по жанрам</h3>
                <c:forEach items="${genresMovies}" var="genresMovies">
                    <b>${genresMovies.key}: </b>
                    <c:set var="genreMoviesCount" value="${0}" />
                    <c:forEach items="${genresMovies.value}" var="movieName">
                        <c:set var="genreMoviesCount" value="${genreMoviesCount + 1}" />
                        ${movieName};
                    </c:forEach>
                    <br>
                    <br>
                    <b>Всего фильмов по жанру ${genresMovies.key}: ${genreMoviesCount}</b>
                    <br>
                    <hr align="center" width="500" size="2" color="#a9a9a9" />
                    <br>
                </c:forEach>
            </ul>

            <ul class="statistics">
                <h3>Все фильмы</h3>
                <c:set var="moviesCount" value="${0}" />
                <c:forEach items="${movies}" var="movies">
                    <c:set var="moviesCount" value="${moviesCount + 1}" />
                    ${movies.key};
                </c:forEach>
                <br>
                <br>
                <b>Всего фильмов: ${moviesCount}</b>
            </ul>

            <ul class="statistics">
                <h3>Все жанры</h3>
                <c:set var="genresCount" value="${0}" />
                <c:forEach items="${genres}" var="genres">
                    <c:set var="genresCount" value="${genresCount + 1}" />
                    ${genres};
                </c:forEach>
                <br>
                <br>
                <b>Всего жанров: ${genresCount}</b>
            </ul>

        </c:if>


        <c:if test="${pageContext.request.isUserInRole('ROLE_MANAGER')}">
            <div class="list-group-item"><b>Всего пользователей, сделавших заказ: ${statUsersCount}</b></div>
            <div class="list-group-item">
                <b>Количество фильмов по жанрам</b>
                <br>
                <c:forEach items="${statUsersGenresMoviesCount}" var="usersGenresMoviesCount">
                    <b>${usersGenresMoviesCount.key.username}</b>
                    <br>
                    <c:forEach items="${usersGenresMoviesCount.value}" var="genresMoviesCount">
                        ${genresMoviesCount.key}: ${genresMoviesCount.value}
                        <br>
                    </c:forEach>
                </c:forEach>
            </div>
            <div class="list-group-item"><b>Пользователи</b></div>
                <c:forEach items="${genresMovies}" var="userGenresMovies">
                    <ul class="statistics">
                    <h3>${userGenresMovies.key.username}</h3>
                        <h4>Фильмы пользователя по жанрам</h4>
                    <c:set var="genresCount" value="${0}" />
                    <c:set var="userMoviesCount" value="${0}" />
                    <c:forEach items="${userGenresMovies.value}" var="genresMovies">
                        <c:set var="genresCount" value="${genresCount + 1}" />
                        <b>${genresMovies.key}: </b>
                        <c:set var="genresMoviesCount" value="${0}" />
                        <c:forEach items="${genresMovies.value}" var="movieName">
                            <c:set var="genresMoviesCount" value="${genresMoviesCount + 1}" />
                            ${movieName};
                        </c:forEach>
                        <br>
                        <c:set var="userMoviesCount" value="${userMoviesCount + genresMoviesCount}" />
                        <b>Всего фильмов пользователя по жанру ${genresMovies.key}: ${genresMoviesCount}</b>
                        <br>
                        <br>
                    </c:forEach>
                    <b>Всего жанров пользователя ${userGenresMovies.key.username}: ${genresCount}</b>
                    <br>
                    <br>
                    <b>Всего фильмов пользователя ${userGenresMovies.key.username}: ${userMoviesCount}</b>
                    <br>
                    <br>
                    </ul>
                </c:forEach>
                <br>
            </ul>
        </c:if>
    </c:if>

    <c:if test="${empty orders}">
        <h3 class="name">Для получения статистики необходимо сделать заказ!</h3>
    </c:if>

    <a href="/" class="index">Главная</a>

</div>

</body>
</html>