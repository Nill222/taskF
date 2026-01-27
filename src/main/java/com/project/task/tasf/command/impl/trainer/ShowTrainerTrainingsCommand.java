package com.project.task.tasf.command.impl.trainer;

import com.project.task.tasf.command.Command;
import com.project.task.tasf.controller.Router;
import com.project.task.tasf.model.entity.Training;
import com.project.task.tasf.model.entity.User;
import com.project.task.tasf.model.service.TrainingService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowTrainerTrainingsCommand implements Command {

    private final TrainingService trainingService;

    public ShowTrainerTrainingsCommand(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @Override
    public Router execute(HttpServletRequest req) {
        User trainer = (User) req.getSession().getAttribute("user");
        int trainerId = trainer.getId();
        List<Training> trainings = trainingService.findByTrainer(trainerId);
        req.setAttribute("trainings", trainings);
        return new Router("/pages/trainer/trainings.jsp", Router.Type.FORWARD);
    }
}
