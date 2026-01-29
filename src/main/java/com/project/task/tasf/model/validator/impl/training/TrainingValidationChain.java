package com.project.task.tasf.model.validator.impl.training;

import com.project.task.tasf.model.entity.Training;
import com.project.task.tasf.model.validator.ValidationHandler;

public class TrainingValidationChain {

    public static ValidationHandler<Training> build() {
        ValidationHandler<Training> start = new TrainingNotNullValidator();
        start
                .linkWith(new TrainingTrainerValidator())
                .linkWith(new TrainingExerciseValidator());
        return start;
    }
}

