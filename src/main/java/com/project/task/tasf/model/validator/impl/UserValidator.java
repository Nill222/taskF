package com.project.task.tasf.model.validator.impl;

import com.project.task.tasf.model.entity.User;
import com.project.task.tasf.model.validator.BaseValidator;

public class UserValidator extends BaseValidator<User> {

    @Override
    public void validate(User user) {

        require(user != null, "User cannot be null");

        require(user.getName() != null && !user.getName().isBlank(),
                "User name is required");

        require(user.getName() != null && !user.getName().isBlank(),
                "Name is required");

        require(user.getPasswordHash() != null && !user.getPasswordHash().isBlank(),
                "Password is required");

        require(user.getRole() != null,
                "User role is required");
    }
}
