package com.project.task.tasf.model.dao;

import com.project.task.tasf.model.entity.Review;

import java.util.List;

public interface ReviewDao {
    int save(Review review);
    List<Review> findByClientId(int clientId);
}
