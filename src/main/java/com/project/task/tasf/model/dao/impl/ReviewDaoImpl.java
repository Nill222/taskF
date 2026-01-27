package com.project.task.tasf.model.dao.impl;

import com.project.task.tasf.model.dao.ReviewDao;
import com.project.task.tasf.model.entity.Review;
import com.project.task.tasf.model.mapper.Mapper;
import com.project.task.tasf.model.mapper.impl.ReviewMapper;

import java.util.List;

public class ReviewDaoImpl extends BaseDao<Review> implements ReviewDao {
    private static final String SAVE_REVIEW = "INSERT INTO reviews (client_id, trainer_id, text, rating) VALUES (?, ?, ?, ?)";
    private static final String FIND_BY_CLIENT = "SELECT * FROM reviews WHERE client_id = ?";
    private final ReviewMapper reviewMapper = new ReviewMapper();
    @Override
    protected Mapper<Review> getMapper() {
        return reviewMapper;
    }

    @Override
    public int save(Review review) {
        int id = insert(SAVE_REVIEW, review);
        review.setId(id);
        return id;
    }

    @Override
    public List<Review> findByClientId(int clientId) {
        return queryListInternal(FIND_BY_CLIENT, ps -> ps.setInt(1, clientId));
    }
}
