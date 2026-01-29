package com.project.task.tasf.model.validator;

import com.project.task.tasf.model.entity.BaseEntity;

public abstract class ValidationHandler<T extends BaseEntity> {

    private ValidationHandler<T> next;

    public ValidationHandler<T> linkWith(ValidationHandler<T> next) {
        this.next = next;
        return next;
    }

    public void validate(T target) {
        check(target);
        if (next != null) {
            next.validate(target);
        }
    }

    protected abstract void check(T target);
}

