package com.project.task.tasf.model.service.impl;

import com.project.task.tasf.model.dao.UserDao;
import com.project.task.tasf.model.entity.User;
import com.project.task.tasf.model.entity.UserRole;
import com.project.task.tasf.model.service.UserService;
import com.project.task.tasf.model.validator.impl.UserValidator;
import com.project.task.tasf.util.PasswordHash;
import lombok.Getter;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UserValidator validator;

    public UserServiceImpl(UserDao userDao, UserValidator validator) {
        this.userDao = userDao;
        this.validator = validator;
    }

    @Override
    public User register(String name, String password, String role) {
        if (userDao.findByName(name).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }
        User user = new User();
        user.setName(name);
        user.setPasswordHash(PasswordHash.hash(password));
        user.setRole(UserRole.valueOf(role.toUpperCase()));
        validator.validate(user);
        int id = userDao.save(user);
        user.setId(id);
        return user;
    }


    @Override
    public User authenticate(String name, String password) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (password.isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }
        User user = userDao.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if (!PasswordHash.verify(password, user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return user;
    }

    @Override
    public boolean authorize(User user, UserRole requiredRole) {
        if (user == null) {
            throw new IllegalArgumentException("User is required");
        }
        return user.getRole() == requiredRole;
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}

