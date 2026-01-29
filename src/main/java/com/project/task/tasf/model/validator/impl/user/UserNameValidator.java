package com.project.task.tasf.model.validator.impl.user;

import com.project.task.tasf.model.entity.User;
import com.project.task.tasf.model.validator.ValidationHandler;

public class UserNameValidator extends ValidationHandler<User> {
    @Override
    protected void check(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            throw new IllegalArgumentException("User name is required");
        }
    }
}
