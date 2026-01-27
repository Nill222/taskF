package com.project.task.tasf.model.validator;

import com.project.task.tasf.model.entity.BaseEntity;

public interface Validator<T extends BaseEntity> {
    void validate(T t);
}
