package com.project.task.tasf.model.validator.impl.review;

import com.project.task.tasf.model.entity.Review;
import com.project.task.tasf.model.validator.ValidationHandler;

public class ReviewNotNullValidator extends ValidationHandler<Review> {
    @Override
    protected void check(Review review) {
        if (review == null) {
            throw new IllegalArgumentException("Review cannot be null");
        }
    }
}

