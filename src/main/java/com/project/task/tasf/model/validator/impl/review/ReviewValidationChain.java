package com.project.task.tasf.model.validator.impl.review;

import com.project.task.tasf.model.entity.Review;
import com.project.task.tasf.model.validator.ValidationHandler;

public class ReviewValidationChain {

    public static ValidationHandler<Review> build() {
        ValidationHandler<Review> start = new ReviewNotNullValidator();
        start
                .linkWith(new ReviewOrderValidator())
                .linkWith(new ReviewRaitingValidator());
        return start;
    }
}

