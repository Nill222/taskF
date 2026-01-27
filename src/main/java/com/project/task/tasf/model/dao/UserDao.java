package com.project.task.tasf.model.dao;

import com.project.task.tasf.model.entity.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> findById(int id);
    int save(User user);
    Optional<User> findByName(String name);
}
