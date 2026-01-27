package com.project.task.tasf.model.service.impl;

import com.project.task.tasf.model.dao.ReviewDao;
import com.project.task.tasf.model.entity.Review;
import com.project.task.tasf.model.service.ReviewService;
import com.project.task.tasf.model.validator.impl.ReviewValidator;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {

    private final ReviewDao reviewDao;
    private final ReviewValidator validator;

    public ReviewServiceImpl(ReviewDao reviewDao, ReviewValidator validator) {
        this.reviewDao = reviewDao;
        this.validator = validator;
    }

    @Override
    public List<Review> findByTrainer(int trainerId) {
        return reviewDao.findByClientId(trainerId);
    }

    @Override
    public int createReview(int clientId, int orderId, String text, int rating) {
        Review review = new Review();
        review.setClientId(clientId);
        review.setOrderId(orderId);
        review.setText(text);
        review.setRating(rating);
        validator.validate(review);
        return reviewDao.save(review);
    }
}

