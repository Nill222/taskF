package com.project.task.tasf.model.dao;

import com.project.task.tasf.model.entity.Training;

import java.util.List;
import java.util.Optional;

public interface TrainingDao {
    int save(Training training);
    List<Training> findByTrainerId(int id);
    Optional<Training> findById(int id);
}
