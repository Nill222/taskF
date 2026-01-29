package com.project.task.tasf.model.validator.impl.user;

import com.project.task.tasf.model.entity.User;
import com.project.task.tasf.model.validator.ValidationHandler;

public class UserRoleValidator extends ValidationHandler<User> {
    @Override
    protected void check(User user) {
        if (user.getRole() == null) {
            throw new IllegalArgumentException("User role is required");
        }
    }
}
