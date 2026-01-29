package com.project.task.tasf.model.validator.impl.order;

import com.project.task.tasf.model.entity.Order;
import com.project.task.tasf.model.validator.ValidationHandler;


public class OrderTypeValidator extends ValidationHandler<Order> {
    @Override
    protected void check(Order order) {
        if (order.getOrderType() == null) {
            throw new IllegalArgumentException("Order type is required");
        }
    }
}
