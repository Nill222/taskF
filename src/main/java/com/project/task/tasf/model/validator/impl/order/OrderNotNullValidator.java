package com.project.task.tasf.model.validator.impl.order;

import com.project.task.tasf.model.entity.Order;
import com.project.task.tasf.model.validator.ValidationHandler;

public class OrderNotNullValidator extends ValidationHandler<Order> {
    @Override
    protected void check(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
    }
}
