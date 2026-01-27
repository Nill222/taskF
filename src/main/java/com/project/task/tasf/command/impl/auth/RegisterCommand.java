package com.project.task.tasf.command.impl.auth;

import com.project.task.tasf.command.Command;
import com.project.task.tasf.controller.Router;
import com.project.task.tasf.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {

    private final UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest req) {
        if ("GET".equalsIgnoreCase(req.getMethod())) {
            return new Router("/pages/register.jsp", Router.Type.FORWARD);
        }
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        req.setAttribute("name", name);
        try {
            userService.register(name, password, role);
            return new Router("/app?command=login", Router.Type.REDIRECT);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            return new Router("/pages/register.jsp", Router.Type.FORWARD);
        }
    }
}
