<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Оставить отзыв</title></head>
<body>

<h2>Оставить отзыв</h2>

<c:if test="${not empty error}">
  <p style="color:red">${error}</p>
</c:if>

<form action="/app" method="post">
  <input type="hidden" name="command" value="review_create"/>
  <input type="hidden" name="orderId" value="${orderId}"/>

  <label>Текст:</label><br/>
  <textarea name="text"></textarea><br/>

  <label>Оценка (1–5):</label>
  <input type="number" name="rating" min="1" max="5"/><br/>

  <button type="submit">Отправить</button>
</form>

<p><a href="/app?command=client_orders">Назад</a></p>

</body>
</html>
