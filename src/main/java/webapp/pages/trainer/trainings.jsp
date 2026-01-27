<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Мои тренировки</title></head>
<body>

<h2>Ваши тренировки</h2>

<table border="1">
  <tr>
    <th>ID</th>
    <th>Упражнение</th>
    <th>Подходы</th>
    <th>Тоннаж</th>
    <th>Заказ</th>
  </tr>

  <c:forEach var="t" items="${trainings}">
    <tr>
      <td>${t.id}</td>
      <td>${t.exercise}</td>
      <td>${t.countApproaches}</td>
      <td>${t.tonnage}</td>
      <td>${t.orderId}</td>
    </tr>
  </c:forEach>
</table>

</body>
</html>
