<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Создать заказ</title></head>
<body>

<h2>Создание заказа</h2>

<c:if test="${not empty error}">
  <p style="color:red">${error}</p>
</c:if>

<form action="/app" method="post">
  <input type="hidden" name="command" value="client_order_create"/>

  <label>Цена:</label>
  <input type="number" step="0.01" name="price"/><br/>

  <button type="submit">Создать</button>
</form>

<p><a href="/app?command=client_orders">Назад</a></p>

</body>
</html>
