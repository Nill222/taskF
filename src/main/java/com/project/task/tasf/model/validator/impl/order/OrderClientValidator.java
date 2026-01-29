package com.project.task.tasf.model.validator.impl.order;

import com.project.task.tasf.model.dao.UserDao;
import com.project.task.tasf.model.entity.Order;
import com.project.task.tasf.model.validator.ValidationHandler;

public class OrderClientValidator extends ValidationHandler<Order> {

    private final UserDao userDao;

    public OrderClientValidator(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected void check(Order order) {
        if (order.getClientId() == null) {
            throw new IllegalArgumentException("Client ID is required");
        }
        if (userDao.findById(order.getClientId()).isEmpty()) {
            throw new IllegalArgumentException("Client does not exist");
        }
    }
}

