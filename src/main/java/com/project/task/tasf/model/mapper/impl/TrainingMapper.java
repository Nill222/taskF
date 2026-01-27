package com.project.task.tasf.model.mapper.impl;

import com.project.task.tasf.model.entity.Training;
import com.project.task.tasf.model.mapper.Mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainingMapper implements Mapper<Training> {

    @Override
    public Training to(ResultSet rs) throws SQLException {
        Training t = new Training();
        t.setId(rs.getInt("id"));
        t.setTrainerId(rs.getInt("trainer_id"));
        t.setOrderId(rs.getInt("order_id"));
        t.setExercise(rs.getString("exercise"));
        t.setCountApproaches(rs.getInt("count_approaches"));
        t.setTonnage(rs.getDouble("tonnage"));
        return t;
    }

    @Override
    public void mapForInsert(Training t, PreparedStatement ps) throws SQLException {
        ps.setInt(1, t.getTrainerId());
        ps.setInt(2, t.getOrderId());
        ps.setString(3, t.getExercise());
        ps.setInt(4, t.getCountApproaches());
        ps.setDouble(5, t.getTonnage());
    }
}
