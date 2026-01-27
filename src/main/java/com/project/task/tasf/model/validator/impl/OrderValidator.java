package com.project.task.tasf.model.validator.impl;

import com.project.task.tasf.model.dao.UserDao;
import com.project.task.tasf.model.entity.Order;
import com.project.task.tasf.model.validator.BaseValidator;

public class OrderValidator extends BaseValidator<Order> {

    private final UserDao userDao;
    public OrderValidator(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void validate(Order order) {
        require(order != null, "Order cannot be null");
        require(order.getClientId() != null, "Client ID is required");
        require(userDao.findById(order.getClientId()).isPresent(),
                "Client does not exist");
        require(order.getOrderType() != null, "Order type is required");
        require(order.getPrice() != null, "Price is required");
        require(order.getPrice() >= 0, "Price cannot be negative");
    }
}
