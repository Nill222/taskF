package com.project.task.tasf.model.mapper.impl;

import com.project.task.tasf.model.entity.Order;
import com.project.task.tasf.model.entity.OrderType;
import com.project.task.tasf.model.mapper.Mapper;
import com.project.task.tasf.model.mapper.UpdatableMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements UpdatableMapper<Order> {

    @Override
    public Order to(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setClientId(rs.getInt("client_id"));
        order.setOrderType(OrderType.valueOf(rs.getString("type")));
        order.setPrice(rs.getDouble("price"));
        return order;
    }

    @Override
    public void mapForInsert(Order order, PreparedStatement ps) throws SQLException {
        ps.setInt(1, order.getClientId());
        ps.setString(3, order.getOrderType().name());
        ps.setDouble(4, order.getPrice());
    }

    @Override
    public void update(Order order, PreparedStatement ps) throws SQLException {
        ps.setInt(1, order.getClientId());
        ps.setString(3, order.getOrderType().name());
        ps.setDouble(4, order.getPrice());
        ps.setInt(5, order.getId());
    }
}

