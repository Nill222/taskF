package com.project.task.tasf.model.service.factory;

import com.project.task.tasf.model.dao.OrderDao;
import com.project.task.tasf.model.dao.ReviewDao;
import com.project.task.tasf.model.dao.TrainingDao;
import com.project.task.tasf.model.dao.UserDao;
import com.project.task.tasf.model.dao.impl.OrderDaoImpl;
import com.project.task.tasf.model.dao.impl.ReviewDaoImpl;
import com.project.task.tasf.model.dao.impl.TrainingDaoImpl;
import com.project.task.tasf.model.dao.impl.UserDaoImpl;
import com.project.task.tasf.model.entity.Order;
import com.project.task.tasf.model.entity.Review;
import com.project.task.tasf.model.entity.Training;
import com.project.task.tasf.model.entity.User;
import com.project.task.tasf.model.service.OrderService;
import com.project.task.tasf.model.service.ReviewService;
import com.project.task.tasf.model.service.TrainingService;
import com.project.task.tasf.model.service.UserService;
import com.project.task.tasf.model.service.impl.OrderServiceImpl;
import com.project.task.tasf.model.service.impl.ReviewServiceImpl;
import com.project.task.tasf.model.service.impl.TrainingServiceImpl;
import com.project.task.tasf.model.service.impl.UserServiceImpl;
import com.project.task.tasf.model.validator.ValidationHandler;
import com.project.task.tasf.model.validator.impl.order.OrderValidationChain;
import com.project.task.tasf.model.validator.impl.review.ReviewValidationChain;
import com.project.task.tasf.model.validator.impl.training.TrainingValidationChain;
import com.project.task.tasf.model.validator.impl.user.UserValidationChain;
import lombok.Getter;

@Getter
public class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final UserService userService;
    private final OrderService orderService;
    private final TrainingService trainingService;
    private final ReviewService reviewService;

    private ServiceFactory() {
        UserDao userDao = new UserDaoImpl();
        OrderDao orderDao = new OrderDaoImpl();
        TrainingDao trainingDao = new TrainingDaoImpl();
        ReviewDao reviewDao = new ReviewDaoImpl();
        ValidationHandler<User> userValidator = UserValidationChain.build();
        ValidationHandler<Order> orderValidator = OrderValidationChain.build(userDao);
        ValidationHandler<Training> trainingValidator = TrainingValidationChain.build();
        ValidationHandler<Review> reviewValidator = ReviewValidationChain.build();

        this.userService = new UserServiceImpl(userDao, userValidator);
        this.orderService = new OrderServiceImpl(orderDao, orderValidator);
        this.trainingService = new TrainingServiceImpl(trainingDao, trainingValidator, orderDao);
        this.reviewService = new ReviewServiceImpl(reviewDao, reviewValidator);
    }
    public static ServiceFactory getInstance() {
        return INSTANCE;
    }
}

