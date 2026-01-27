package com.project.task.tasf.command.impl.auth;

import com.project.task.tasf.command.Command;
import com.project.task.tasf.controller.Router;
import com.project.task.tasf.model.entity.User;
import com.project.task.tasf.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest req) {
        if ("GET".equalsIgnoreCase(req.getMethod())) {
            return new Router("/pages/login.jsp", Router.Type.FORWARD);
        }
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        try {
            User user = userService.authenticate(name, password);
            req.getSession().setAttribute("user", user);
            return new Router("/app?command=client_orders", Router.Type.REDIRECT);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            return new Router("/pages/login.jsp", Router.Type.FORWARD);
        }
    }
}
