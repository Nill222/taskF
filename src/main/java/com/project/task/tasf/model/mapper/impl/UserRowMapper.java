package com.project.task.tasf.model.mapper.impl;

import com.project.task.tasf.model.entity.User;
import com.project.task.tasf.model.entity.UserRole;
import com.project.task.tasf.model.mapper.Mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements Mapper<User> {

    @Override
    public User to(ResultSet rs) throws SQLException {
        User user = new User();
        user.setName(rs.getString("name"));
        user.setPasswordHash(rs.getString("password_hash"));
        user.setRole(UserRole.valueOf(rs.getString("role")));
        return user;
    }

    @Override
    public void mapForInsert(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPasswordHash());
        preparedStatement.setString(3, user.getRole().name());
    }

}
