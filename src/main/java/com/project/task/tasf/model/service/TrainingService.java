package com.project.task.tasf.model.service;

import com.project.task.tasf.model.entity.Order;
import com.project.task.tasf.model.entity.Training;

import java.util.List;

public interface TrainingService {
    Training findById(int id);
    List<Training> findByTrainer(int trainerId);
    int createTraining(String exercise, int trainerId, int orderId, int countApproaches, double tonnage);
    void createTrainingForOrder(int trainerId, int orderId, String exercise, int countApproaches, double tonnage);
}

