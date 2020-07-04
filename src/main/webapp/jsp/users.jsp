<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Пользователи</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div>
    <h2 class="name">Список пользователей</h2>
    <table border="1">
        <thead>
        <th>ID</th>
        <th>UserName</th>
        <th>Roles</th>
        <th></th>
        </thead>
        <c:forEach items="${allUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>
                    <ul>
                        <c:forEach items="${user.roles}" var="role">
                            <li>${role.name}
                                <c:if test="${role.roleId == 3}">
                                    <a href="/remove_users_role?userId=${user.id}&roleId=${role.roleId}">X</a>
                                </c:if>
                            </li>
                        </c:forEach>
                    </ul>

                    <c:if test="${!(pageContext.request.isUserInRole('ROLE_MANAGER') && pageContext.request.isUserInRole('ROLE_USER'))}">
                        <button><a href="/make_manager?userId=${user.id}">Сделать менджером</a></button>
                    </c:if>
                </td>
                <td><a href="/remove_user?userId=${user.id}">X</a></td>

            </tr>
        </c:forEach>
    </table>
    <a href="/" class="index">Главная</a>
</div>
</body>
</html>
