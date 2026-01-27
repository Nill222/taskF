package com.project.task.tasf.command;


import com.project.task.tasf.command.impl.DefaultCommand;
import com.project.task.tasf.command.impl.auth.LoginCommand;
import com.project.task.tasf.command.impl.auth.LogoutCommand;
import com.project.task.tasf.command.impl.auth.RegisterCommand;
import com.project.task.tasf.command.impl.client.*;
import com.project.task.tasf.command.impl.trainer.CreateTrainingCommand;
import com.project.task.tasf.command.impl.trainer.ShowOrderCreateCommand;
import com.project.task.tasf.command.impl.trainer.ShowTrainerTrainingsCommand;
import com.project.task.tasf.model.service.factory.ServiceFactory;

public enum CommandType {
    LOGIN(new LoginCommand(ServiceFactory.getInstance().getUserService())),
    REGISTER(new RegisterCommand(ServiceFactory.getInstance().getUserService())),
    LOGOUT(new LogoutCommand()),
    CLIENT_ORDERS(new ShowClientOrdersCommand(ServiceFactory.getInstance().getOrderService())),
    CLIENT_ORDER_CREATE(new CreateOrderCommand(ServiceFactory.getInstance().getOrderService())),
    TRAINER_TRAININGS(new ShowTrainerTrainingsCommand(ServiceFactory.getInstance().getTrainingService())),
    TRAINER_TRAINING_CREATE(new CreateTrainingCommand(ServiceFactory.getInstance().getTrainingService())),
    REVIEW_CREATE(new CreateReviewCommand(ServiceFactory.getInstance().getReviewService())),
    SHOW_ORDER_CREATE(new ShowOrderCreateCommand(ServiceFactory.getInstance().getOrderService())),
    DEFAULT(new DefaultCommand());
    private final Command command;
    CommandType(Command command) {
        this.command = command;
    }
    public static Command defineCommand(String commandStr) {
        if(commandStr.isBlank()) {
            return DEFAULT.command;
        }
        try {
            CommandType current = CommandType.valueOf(commandStr.toUpperCase());
            return current.command;
        } catch (IllegalArgumentException e) {
            return DEFAULT.command;
        }
    }
}
