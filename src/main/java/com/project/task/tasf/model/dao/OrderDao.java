package com.project.task.tasf.model.dao;

import com.project.task.tasf.model.entity.Order;
import com.project.task.tasf.model.entity.OrderType;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    Optional<Order> findById(int id);
    List<Order> findByClientId(int clientId);
    List<Order> findByStatus(OrderType status);
    int save(Order order);
    boolean update(Order order);

}
