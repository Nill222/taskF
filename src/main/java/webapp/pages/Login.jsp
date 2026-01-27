<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Login</title></head>
<body>

<h2>Вход</h2>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<form action="/app" method="post">
    <input type="hidden" name="command" value="login"/>

    <label>Имя:</label>
    <input type="text" name="name" value="${name}"/><br/>

    <label>Пароль:</label>
    <input type="password" name="password"/><br/>

    <button type="submit">Войти</button>
</form>

<p><a href="/app?command=register">Регистрация</a></p>

</body>
</html>
