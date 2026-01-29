package com.project.task.tasf.model.validator.impl.user;

import com.project.task.tasf.model.entity.User;
import com.project.task.tasf.model.validator.ValidationHandler;

public class UserValidationChain {

    public static ValidationHandler<User> build() {
        ValidationHandler<User> start = new UserNotNullValidator();
        start
                .linkWith(new UserNameValidator())
                .linkWith(new UserNameValidator())
                .linkWith(new UserPasswordValidator())
                .linkWith(new UserRoleValidator());
        return start;
    }
}

