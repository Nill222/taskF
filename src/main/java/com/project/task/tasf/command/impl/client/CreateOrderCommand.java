package com.project.task.tasf.command.impl.client;

import com.project.task.tasf.command.Command;
import com.project.task.tasf.controller.Router;
import com.project.task.tasf.model.entity.User;
import com.project.task.tasf.model.service.OrderService;
import javax.servlet.http.HttpServletRequest;

public class CreateOrderCommand implements Command {

    private final OrderService orderService;

    public CreateOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Router execute(HttpServletRequest req) {
        if ("GET".equalsIgnoreCase(req.getMethod())) {
            return new Router("/pages/client/order_create.jsp", Router.Type.FORWARD);
        }
        User user = (User) req.getSession().getAttribute("user");
        int clientId = user.getId();
        double price = Double.parseDouble(req.getParameter("price"));
        try {
            orderService.createOrder(clientId, price);
            return new Router("/app?command=client_orders", Router.Type.REDIRECT);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            return new Router("/pages/client/order_create.jsp", Router.Type.FORWARD);
        }
    }
}
