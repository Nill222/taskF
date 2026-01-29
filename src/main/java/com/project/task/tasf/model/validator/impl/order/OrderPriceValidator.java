package com.project.task.tasf.model.validator.impl.order;

import com.project.task.tasf.model.entity.Order;
import com.project.task.tasf.model.validator.ValidationHandler;

public class OrderPriceValidator extends ValidationHandler<Order> {
    @Override
    protected void check(Order order) {
        if (order.getPrice() == null) {
            throw new IllegalArgumentException("Price is required");
        }
        if (order.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }
}
