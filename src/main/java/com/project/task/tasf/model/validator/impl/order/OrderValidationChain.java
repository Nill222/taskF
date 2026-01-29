package com.project.task.tasf.model.validator.impl.order;

import com.project.task.tasf.model.dao.UserDao;
import com.project.task.tasf.model.entity.Order;
import com.project.task.tasf.model.validator.ValidationHandler;

public class OrderValidationChain {

    public static ValidationHandler<Order> build(UserDao userDao) {
        ValidationHandler<Order> start = new OrderNotNullValidator();
        start
                .linkWith(new OrderClientValidator(userDao))
                .linkWith(new OrderTypeValidator())
                .linkWith(new OrderPriceValidator());
        return start;
    }
}
