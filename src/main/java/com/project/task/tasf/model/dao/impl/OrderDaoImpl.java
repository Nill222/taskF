package com.project.task.tasf.model.dao.impl;

import com.project.task.tasf.model.dao.OrderDao;
import com.project.task.tasf.model.entity.Order;
import com.project.task.tasf.model.entity.OrderType;
import com.project.task.tasf.model.mapper.Mapper;
import com.project.task.tasf.model.mapper.impl.OrderMapper;

import java.util.List;
import java.util.Optional;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {
    private final OrderMapper mapper = new OrderMapper();
    private static final String SAVE = "INSERT INTO orders (client_id, trainer_id, type, status, created_at) VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM orders WHERE id = ?";
    private static final String FIND_BY_CLIENT = "SELECT * FROM orders WHERE client_id = ?";
    private static final String FIND_BY_STATUS = "SELECT * FROM orders WHERE status = ?";
    private static final String UPDATE = "UPDATE orders SET client_id = ?, trainer_id = ?, type = ?, status = ? WHERE id = ?";
    @Override
    protected Mapper<Order> getMapper() {
        return mapper;
    }
    @Override
    public Optional<Order> findById(int id) {
        return findByIdInternal(id, FIND_BY_ID);
    }

    @Override
    public List<Order> findByClientId(int clientId) {
        return queryListInternal(FIND_BY_CLIENT, ps -> ps.setInt(1, clientId));
    }

    @Override
    public List<Order> findByStatus(OrderType status) {
        return queryListInternal(FIND_BY_STATUS, ps -> ps.setString(1, status.name()));
    }

    @Override
    public int save(Order order) {
        int id = insert(SAVE, order);
        order.setId(id);
        return id;
    }

    @Override
    public boolean update(Order order) {
        return updateInternal(UPDATE, ps -> mapper.mapForInsert(order, ps));
    }
}
