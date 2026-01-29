package com.project.task.tasf.model.validator.impl.user;

import com.project.task.tasf.model.entity.User;
import com.project.task.tasf.model.validator.ValidationHandler;

public class UserNotNullValidator extends ValidationHandler<User> {
    @Override
    protected void check(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
    }
}
