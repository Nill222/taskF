package com.project.task.tasf.command.impl;

import com.project.task.tasf.command.Command;
import com.project.task.tasf.controller.Router;

import javax.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        return new Router("/pages/login.jsp", Router.Type.FORWARD);
    }
}
