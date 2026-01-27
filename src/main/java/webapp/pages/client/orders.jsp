<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Мои заказы</title></head>
<body>

<h2>Ваши заказы</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Цена</th>
        <th>Статус</th>
    </tr>

    <c:forEach var="o" items="${orders}">
        <tr>
            <td>${o.id}</td>
            <td>${o.price}</td>
            <td>${o.status}</td>
        </tr>
    </c:forEach>
</table>

<p><a href="/app?command=client_order_create">Создать заказ</a></p>

<form action="/app" method="post">
    <input type="hidden" name="command" value="logout"/>
    <button type="submit">Выйти</button>
</form>

</body>
</html>
