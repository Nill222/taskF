package com.project.task.tasf.model.validator.impl.review;

import com.project.task.tasf.model.entity.Review;
import com.project.task.tasf.model.validator.ValidationHandler;

public class ReviewOrderValidator extends ValidationHandler<Review> {
    @Override
    protected void check(Review review) {
        if (review.getOrderId() == null) {
            throw new IllegalArgumentException("Order ID is required");
        }
    }
}

