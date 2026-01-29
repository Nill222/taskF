package com.project.task.tasf.model.validator.impl.training;

import com.project.task.tasf.model.entity.Training;
import com.project.task.tasf.model.validator.ValidationHandler;

public class TrainingExerciseValidator extends ValidationHandler<Training> {
    @Override
    protected void check(Training training) {
        if (training.getExercise() == null || training.getExercise().isBlank()) {
            throw new IllegalArgumentException("Exercise name is required");
        }
    }
}
