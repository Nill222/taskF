package com.project.task.tasf.model.validator.impl;

import com.project.task.tasf.model.entity.Review;
import com.project.task.tasf.model.validator.BaseValidator;

public class ReviewValidator extends BaseValidator<Review> {

    @Override
    public void validate(Review r) {

        require(r != null, "Review cannot be null");

        require(r.getOrderId() != null,
                "Trainer ID is required");

        require(r.getClientId() != null,
                "Client ID is required");

        require(r.getRating() >= 1 && r.getRating() <= 5,
                "Rating must be between 1 and 5");

        require(r.getText() != null && !r.getText().isBlank(),
                "Review comment is required");
    }
}

