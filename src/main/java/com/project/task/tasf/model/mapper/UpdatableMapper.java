package com.project.task.tasf.model.mapper;

import com.project.task.tasf.model.entity.BaseEntity;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface UpdatableMapper <T extends BaseEntity> extends Mapper<T> {
    void update(T entity, PreparedStatement ps) throws SQLException;
}
