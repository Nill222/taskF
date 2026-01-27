package com.project.task.tasf.model.service;

import com.project.task.tasf.model.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findByTrainer(int trainerId);
    int createReview(int clientId, int orderId, String text, int rating);
}
