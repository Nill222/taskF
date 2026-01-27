package com.project.task.tasf.command.impl.client;

import com.project.task.tasf.command.Command;
import com.project.task.tasf.controller.Router;
import com.project.task.tasf.model.entity.User;
import com.project.task.tasf.model.service.ReviewService;
import javax.servlet.http.HttpServletRequest;

public class CreateReviewCommand implements Command {

    private final ReviewService reviewService;

    public CreateReviewCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public Router execute(HttpServletRequest req)  {
        if ("GET".equalsIgnoreCase(req.getMethod())) {
            return new Router("/pages/client/review_create.jsp", Router.Type.FORWARD);
        }
        User user = (User) req.getSession().getAttribute("user");
        int clientId = user.getId();
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        String text = req.getParameter("text");
        int rating = Integer.parseInt(req.getParameter("rating"));
        try {
            reviewService.createReview(clientId, orderId, text, rating);
            return new Router(
                    "/app?command=trainer_reviews&trainerId=" + orderId,
                    Router.Type.REDIRECT
            );
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            return new Router("/pages/client/review_create.jsp", Router.Type.FORWARD);
        }
    }
}
