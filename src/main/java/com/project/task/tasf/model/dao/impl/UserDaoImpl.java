package com.project.task.tasf.model.dao.impl;

import com.project.task.tasf.model.mapper.Mapper;
import com.project.task.tasf.model.mapper.impl.UserRowMapper;
import com.project.task.tasf.model.dao.UserDao;
import com.project.task.tasf.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static final String FIND_BY_ID = "select * from users where id = ?";
    private static final String FIND_BY_NAME = "select * from users where name = ?";
    private static final String SAVE_USER = "INSERT INTO users (name, password_hash, role) VALUES (?, ?, ?)";
    private static final UserDaoImpl INSTANCE = new UserDaoImpl();
    private final UserRowMapper mapper = new UserRowMapper();
    public UserDaoImpl() {}
    public static UserDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    protected Mapper<User> getMapper() {
        return mapper;
    }

    @Override
    public Optional<User> findById(int id){
        return findByIdInternal(id, FIND_BY_ID);
    }

    @Override
    public int save(User user) {
       int id = insert(SAVE_USER, user);
       user.setId(id);
       return id;
    }

    @Override
    public Optional<User> findByName(String name) {
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_NAME)) {
            ps.setString(1, name);
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
}
