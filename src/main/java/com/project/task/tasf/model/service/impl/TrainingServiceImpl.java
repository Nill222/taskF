package com.project.task.tasf.model.service.impl;

import com.project.task.tasf.model.dao.OrderDao;
import com.project.task.tasf.model.dao.TrainingDao;
import com.project.task.tasf.model.entity.Order;
import com.project.task.tasf.model.entity.OrderType;
import com.project.task.tasf.model.entity.Training;
import com.project.task.tasf.model.service.TrainingService;
import com.project.task.tasf.model.validator.ValidationHandler;

import java.util.List;

public class TrainingServiceImpl implements TrainingService {

    private final TrainingDao trainingDao;
    private final ValidationHandler<Training> validator;
    private final OrderDao orderDao;

    public TrainingServiceImpl(TrainingDao trainingDao, ValidationHandler<Training> validator, OrderDao orderDao) {
        this.trainingDao = trainingDao;
        this.validator = validator;
        this.orderDao = orderDao;
    }

    @Override
    public Training findById(int id) {
        return trainingDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Training not found"));
    }

    @Override
    public List<Training> findByTrainer(int trainerId) {
        return trainingDao.findByTrainerId(trainerId);
    }

    @Override
    public int createTraining(String exercise, int trainerId, int orderId, int countApproaches, double tonnage) {
        Training training = new Training();
        training.setExercise(exercise);
        training.setOrderId(orderId);
        training.setTrainerId(trainerId);
        training.setCountApproaches(countApproaches);
        training.setTonnage(tonnage);
        validator.validate(training);
        return trainingDao.save(training);
    }

    @Override
    public void createTrainingForOrder(int trainerId, int orderId, String exercise, int countApproaches, double tonnage) {
        Order order = orderDao.findById(orderId) .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        if (order.getOrderType() != OrderType.CREATE) {
            throw new IllegalStateException("Order cannot be taken");
        } Training training = new Training();
        training.setTrainerId(trainerId);
        training.setOrderId(orderId);
        training.setExercise(exercise);
        training.setCountApproaches(countApproaches);
        training.setTonnage(tonnage);
        trainingDao.save(training);
        order.setOrderType(OrderType.COMPLETE);
        orderDao.update(order);
    }
}
