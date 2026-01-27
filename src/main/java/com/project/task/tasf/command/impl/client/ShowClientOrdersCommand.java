package com.project.task.tasf.command.impl.client;

import com.project.task.tasf.command.Command;
import com.project.task.tasf.controller.Router;
import com.project.task.tasf.model.entity.Order;
import com.project.task.tasf.model.entity.User;
import com.project.task.tasf.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowClientOrdersCommand implements Command {

    private final OrderService orderService;

    public ShowClientOrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Router execute(HttpServletRequest req) {

        User user = (User) req.getSession().getAttribute("user");
        int clientId = user.getId();

        List<Order> orders = orderService.findOrdersByClient(clientId);
        req.setAttribute("orders", orders);

        return new Router("/pages/client/orders.jsp", Router.Type.FORWARD);
    }
}
