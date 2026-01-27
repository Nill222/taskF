package com.project.task.tasf.model.validator;

import com.project.task.tasf.model.entity.BaseEntity;

public abstract class BaseValidator<T extends BaseEntity> implements Validator<T> {
    protected void require(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }
}
