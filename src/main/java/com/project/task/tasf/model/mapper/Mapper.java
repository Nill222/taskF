package com.project.task.tasf.model.mapper;

import com.project.task.tasf.model.entity.BaseEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T extends BaseEntity>  {
    T to(ResultSet resultSet) throws SQLException;
    void mapForInsert(T t, PreparedStatement preparedStatement) throws SQLException;
}
