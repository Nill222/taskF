package com.project.task.tasf.model.validator.impl;

import com.project.task.tasf.model.entity.Training;
import com.project.task.tasf.model.validator.BaseValidator;

public class TrainingValidator extends BaseValidator<Training> {

    @Override
    public void validate(Training t) {

        require(t != null, "Training cannot be null");

        require(t.getTrainerId() != null,
                "Trainer ID is required");

        require(t.getExercise() != null && !t.getExercise().isBlank(),
                "Exercise name is required");

        require(t.getCountApproaches() > 0,
                "Count of approaches must be greater than zero");

        require(t.getTonnage() != null && t.getTonnage() >= 0,
                "Tonnage cannot be negative");
    }
}

