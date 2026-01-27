package com.project.task.tasf.command.impl.auth;

import com.project.task.tasf.command.Command;
import com.project.task.tasf.controller.Router;
import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return new Router("/login.jsp", Router.Type.REDIRECT);
    }
}
