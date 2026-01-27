package com.project.task.tasf.command;

import com.project.task.tasf.controller.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    Router execute(HttpServletRequest request);
}
