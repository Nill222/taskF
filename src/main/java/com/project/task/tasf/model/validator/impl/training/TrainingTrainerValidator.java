package com.project.task.tasf.model.validator.impl.training;

import com.project.task.tasf.model.entity.Training;
import com.project.task.tasf.model.validator.ValidationHandler;

public class TrainingTrainerValidator extends ValidationHandler<Training> {
    @Override
    protected void check(Training training) {
        if (training.getTrainerId() == null) {
            throw new IllegalArgumentException("Trainer ID is required");
        }
    }
}
