package com.project.task.tasf.model.service;

import com.project.task.tasf.model.entity.User;
import com.project.task.tasf.model.entity.UserRole;

import java.util.Optional;

public interface UserService {
    User register(String name, String password, String role);
    User authenticate(String name, String password);
    boolean authorize(User user, UserRole requiredRole);
    User findById(int id);
}

