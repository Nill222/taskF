package com.project.task.tasf.model.service.impl;


import com.project.task.tasf.model.dao.OrderDao;
import com.project.task.tasf.model.entity.Order;
import com.project.task.tasf.model.entity.OrderType;
import com.project.task.tasf.model.service.OrderService;
import com.project.task.tasf.model.validator.ValidationHandler;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final ValidationHandler<Order> validator;

    public OrderServiceImpl(OrderDao orderDao, ValidationHandler<Order> validator) {
        this.orderDao = orderDao;
        this.validator = validator;
    }

    @Override
    public List<Order> findOrdersByClient(int clientId) {
        if(clientId < 1) {
            throw new IllegalArgumentException("clientId must be greater than 0");
        }
        return orderDao.findByClientId(clientId);
    }

    @Override
    public List<Order> findOrderByStatus(OrderType type) {
        if (type == null) {
            throw new IllegalArgumentException("Order status type is required");
        }
        return orderDao.findByStatus(type);
    }

    @Override
    public Order findOrderById(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Order ID is required");
        }
        int parsedId;
        try {
            parsedId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Order ID must be a valid integer", e);
        }
        return orderDao.findById(parsedId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    @Override
    public int createOrder(int clientId, double price) {
        Order order = new Order();
        order.setClientId(clientId);
        order.setPrice(price);
        order.setOrderType(OrderType.CREATE);
        validator.validate(order);
        return orderDao.save(order);
    }

    @Override
    public boolean update(int orderId) {
        Order order = orderDao.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        if (order.getOrderType() == OrderType.COMPLETE) {
            throw new IllegalStateException("Completed orders cannot be updated");
        }
        order.setOrderType(OrderType.COMPLETE);
        validator.validate(order);
        return orderDao.update(order);
    }
}
