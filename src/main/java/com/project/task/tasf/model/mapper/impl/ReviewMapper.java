package com.project.task.tasf.model.mapper.impl;

import com.project.task.tasf.model.entity.User;
import com.project.task.tasf.model.entity.Review;
import com.project.task.tasf.model.mapper.Mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper implements Mapper<Review> {
    @Override
    public Review to(ResultSet resultSet) throws SQLException {
        Review review = new Review();
        review.setClientId(resultSet.getInt("client_id"));
        review.setOrderId(resultSet.getInt("training_id"));
        review.setText(resultSet.getString("text"));
        review.setRating(resultSet.getInt("rating"));
        return review;
    }

    @Override
    public void mapForInsert(Review review, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, review.getClientId());
        preparedStatement.setInt(2, review.getOrderId());
        preparedStatement.setString(3, review.getText());
        preparedStatement.setInt(4, review.getRating());
    }
}
