package com.project.task.tasf.model.validator.impl.training;

import com.project.task.tasf.model.entity.Training;
import com.project.task.tasf.model.validator.ValidationHandler;

public class TrainingNotNullValidator extends ValidationHandler<Training> {
    @Override
    protected void check(Training training) {
        if (training == null) {
            throw new IllegalArgumentException("Training cannot be null");
        }
    }
}
