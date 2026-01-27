<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Создать тренировку</title></head>
<body>

<h2>Создание тренировки</h2>

<c:if test="${not empty error}">
  <p style="color:red">${error}</p>
</c:if>

<form action="/app" method="post">
  <input type="hidden" name="command" value="trainer_training_create"/>
  <input type="hidden" name="orderId" value="${orderId}"/>

  <label>Упражнение:</label>
  <input type="text" name="exercise"/><br/>

  <label>Подходы:</label>
  <input type="number" name="countApproaches"/><br/>

  <label>Тоннаж:</label>
  <input type="number" step="0.01" name="tonnage"/><br/>

  <button type="submit">Создать</button>
</form>

</body>
</html>
