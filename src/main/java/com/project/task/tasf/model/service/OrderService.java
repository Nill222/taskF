package com.project.task.tasf.model.service;

import com.project.task.tasf.model.entity.Order;
import com.project.task.tasf.model.entity.OrderType;

import java.util.List;

public interface OrderService {
    int createOrder(int clientId, double price);
    boolean update(int orderId);
    List<Order> findOrdersByClient(int clientId);
    List<Order> findOrderByStatus(OrderType type);
    Order findOrderById(String id);
}
