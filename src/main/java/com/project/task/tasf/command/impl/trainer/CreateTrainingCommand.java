package com.project.task.tasf.command.impl.trainer;

import com.project.task.tasf.command.Command;
import com.project.task.tasf.controller.Router;
import com.project.task.tasf.model.entity.User;
import com.project.task.tasf.model.service.TrainingService;

import javax.servlet.http.HttpServletRequest;

public class CreateTrainingCommand implements Command {

    private final TrainingService trainingService;

    public CreateTrainingCommand(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @Override
    public Router execute(HttpServletRequest req) {

        if ("GET".equalsIgnoreCase(req.getMethod())) {
            return new Router("/pages/trainer/training_create.jsp", Router.Type.FORWARD);
        }
        User trainer = (User) req.getSession().getAttribute("user");
        int trainerId = trainer.getId();
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        String exercise = req.getParameter("exercise");
        int countApproaches = Integer.parseInt(req.getParameter("countApproaches"));
        double tonnage = Double.parseDouble(req.getParameter("tonnage"));
        try {
            trainingService.createTrainingForOrder(
                    trainerId,
                    orderId,
                    exercise,
                    countApproaches,
                    tonnage
            );
            return new Router("/app?command=trainer_orders_create", Router.Type.REDIRECT);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.setAttribute("orderId", orderId);
            return new Router("/pages/trainer/training_create.jsp", Router.Type.FORWARD);
        }
    }
}
