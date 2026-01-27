<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="padding: 10px; background: #f0f0f0; margin-bottom: 20px">

    <h2>Fitness App</h2>

    <c:choose>
        <c:when test="${not empty sessionScope.user}">
            <p>Вы вошли как: <b>${sessionScope.user.name}</b> (${sessionScope.user.role})</p>

            <nav>
                <c:if test="${sessionScope.user.role == 'CLIENT'}">
                    <a href="/app?command=client_orders">Мои заказы</a> |
                    <a href="/app?command=client_order_create">Создать заказ</a>
                </c:if>

                <c:if test="${sessionScope.user.role == 'TRAINER'}">
                    <a href="/app?command=trainer_orders_create">Заказы для тренера</a> |
                    <a href="/app?command=trainer_trainings">Мои тренировки</a>
                </c:if>

                |
                <form action="/app" method="post" style="display:inline">
                    <input type="hidden" name="command" value="logout"/>
                    <button type="submit">Выйти</button>
                </form>
            </nav>

        </c:when>

        <c:otherwise>
            <nav>
                <a href="/app?command=login">Вход</a> |
                <a href="/app?command=register">Регистрация</a>
            </nav>
        </c:otherwise>
    </c:choose>

</div>
