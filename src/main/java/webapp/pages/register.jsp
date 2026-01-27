<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Register</title></head>
<body>

<h2>Регистрация</h2>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<form action="/app" method="post">
    <input type="hidden" name="command" value="register"/>

    <label>Имя:</label>
    <input type="text" name="name" value="${name}"/><br/>

    <label>Пароль:</label>
    <input type="password" name="password"/><br/>

    <label>Роль:</label>
    <select name="role">
        <option value="CLIENT">Клиент</option>
        <option value="TRAINER">Тренер</option>
    </select><br/>

    <button type="submit">Зарегистрироваться</button>
</form>

<p><a href="/app?command=login">Назад к входу</a></p>

</body>
</html>
