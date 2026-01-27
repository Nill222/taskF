package com.project.task.tasf.model.dao.impl;

import com.project.task.tasf.model.dao.TrainingDao;
import com.project.task.tasf.model.entity.Training;
import com.project.task.tasf.model.mapper.Mapper;
import com.project.task.tasf.model.mapper.impl.TrainingMapper;

import java.util.List;
import java.util.Optional;

public class TrainingDaoImpl extends BaseDao<Training> implements TrainingDao {
    private final TrainingMapper mapper = new TrainingMapper();
    private static final String SAVE = "INSERT INTO training (trainer_id, exercise, count_approaches, tonnage) VALUES (?, ?, ?, ?)";
    private static final String FIND_BY_TRAINER = "SELECT * FROM training WHERE trainer_id = ?";
    private static final String FIND_BY_ID = "SELECT * FROM training WHERE trainer_id = ?";

    @Override
    protected Mapper<Training> getMapper() {
        return mapper;
    }

    @Override
    public int save(Training training) {
        int id = insert(SAVE, training);
        training.setId(id);
        return id;
    }

    @Override
    public Optional<Training> findById(int id) {
        return findByIdInternal(id, FIND_BY_ID);
    }

    @Override
    public List<Training> findByTrainerId(int id) {
        return queryListInternal(FIND_BY_TRAINER, ps -> ps.setInt(1, id));
    }
}
