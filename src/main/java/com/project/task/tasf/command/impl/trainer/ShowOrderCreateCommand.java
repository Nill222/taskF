package com.project.task.tasf.command.impl.trainer;

import com.project.task.tasf.command.Command;
import com.project.task.tasf.controller.Router;
import com.project.task.tasf.model.entity.Order;
import com.project.task.tasf.model.entity.OrderType;
import com.project.task.tasf.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowOrderCreateCommand implements Command {

    private final OrderService orderService;

    public ShowOrderCreateCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Router execute(HttpServletRequest req) {
        if ("GET".equalsIgnoreCase(req.getMethod())) {
            List<Order> orders = orderService.findOrderByStatus(OrderType.CREATE);
            req.setAttribute("orders", orders);
            return new Router("/pages/trainer/orders_create.jsp", Router.Type.FORWARD);
        }
        return new Router("/app?command=trainer_trainings", Router.Type.REDIRECT);
    }
}
