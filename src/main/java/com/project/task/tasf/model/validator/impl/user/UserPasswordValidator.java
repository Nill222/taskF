package com.project.task.tasf.model.validator.impl.user;

import com.project.task.tasf.model.entity.User;
import com.project.task.tasf.model.validator.ValidationHandler;

public class UserPasswordValidator extends ValidationHandler<User> {
    @Override
    protected void check(User user) {
        if (user.getPasswordHash() == null || user.getPasswordHash().isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }
    }
}
