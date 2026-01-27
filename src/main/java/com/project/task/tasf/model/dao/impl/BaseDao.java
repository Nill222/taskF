package com.project.task.tasf.model.dao.impl;

import com.project.task.tasf.model.connection.ConnectionPool;
import com.project.task.tasf.model.dao.SqlConsumer;
import com.project.task.tasf.model.entity.BaseEntity;
import com.project.task.tasf.model.mapper.Mapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public abstract class BaseDao <T extends BaseEntity> {
    protected abstract Mapper<T> getMapper();
    protected final ConnectionPool pool = ConnectionPool.INSTANCE;
    
    protected int insert(String sql, T entity) {
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            getMapper().mapForInsert(entity, ps);
            int rows = ps.executeUpdate();
            if(rows == 0) {
                return -1;
            }
            try(ResultSet keys = ps.getGeneratedKeys()) {
                if(keys.next()) {
                    return keys.getInt(1);
                }
            }
            return -1;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected Optional<T> findByIdInternal(int id, String sql) {
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    return Optional.of(getMapper().to(rs));
                }
            }
            return Optional.empty();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected List<T> queryListInternal(String sql, SqlConsumer<PreparedStatement> consumer) {
        List<T> list = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            consumer.accept(ps);
            try(ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    list.add(getMapper().to(rs));
                }
            }
            return list;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected boolean updateInternal(String sql, SqlConsumer<PreparedStatement> consumer) {
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            consumer.accept(ps);
            return ps.executeUpdate() > 0;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
