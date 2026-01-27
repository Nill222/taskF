<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Заказы для тренера</title></head>
<body>

<h2>Заказы, ожидающие тренировку</h2>

<table border="1">
  <tr>
    <th>ID заказа</th>
    <th>Цена</th>
    <th>Действие</th>
  </tr>

  <c:forEach var="o" items="${orders}">
    <tr>
      <td>${o.id}</td>
      <td>${o.price}</td>
      <td>
        <a href="/app?command=trainer_training_create&orderId=${o.id}">
          Создать тренировку
        </a>
      </td>
    </tr>
  </c:forEach>
</table>

</body>
</html>
