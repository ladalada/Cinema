# Cinema

Веб-приложение "Онлайн-кинотеатр" с использованием Spring Boot и Spring Security 

Запуск: *localhost:8081*

Веб-сервер: *Apache Tomcat*

БД: *PostgreSQL*

## Роли
С помощью Spring Security реализована регистрация и авторизация на **4 роли**:
* гость (неавторизованный пользователь)
* пользователь *(ROLE_USER)*
* менеджер *(ROLE_MANAGER)*
* администратор *(ROLE_ADMIN)*

## Функции, доступные разным ролям

### Гость
* регистрация
* вход в систему
* просмотр каталога фильмов

### Пользователь
* выход из системы
* просмотр новостей
* просмотр каталога фильмов, добавление фильмов в корзину
* просмотр фильмов из корзины, очистка корзины и формирование заказа
* просмотр своих заказов, переход на страницу просмотра фильма из заказа
* просмотр своей статистики, основанной на сформированных данным пользователем заказах 
(фильмы какого жанры, сколько фильмов по каждому жанру и всего фильмов, которые попали в заказы пользователя)
* просмотр своих рекомендаций, основанных на жанрах фильмов, добавленных пользователем в заказы

### Менеджер
* выход из системы
* просмотр новостей
* просмотр каталога фильмов
* просмотр заказов пользователей, удаление заказов
* просмотр статистики по всем пользователям, основанной на заказах всех пользователей (сколько пользователей сделало заказы и статистика, доступная каждому пользователю)

### Администратор
* выход из системы
* просмотр новостей, добавление новостей
* просмотр каталога фильмов, добавление фильмов (с указанием названия, жанра и стоимости), изменение и удаление фильма
* просмотр всех пользователей, удаление пользователей, наделение пользователей ролью менеджера
