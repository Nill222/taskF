package com.project.task.tasf.model.validator.impl.review;

import com.project.task.tasf.model.entity.Review;
import com.project.task.tasf.model.validator.ValidationHandler;

public class ReviewRaitingValidator extends ValidationHandler<Review> {
    @Override
    protected void check(Review review) {
        if (review.getRating() < 1 || review.getRating() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
    }
}

