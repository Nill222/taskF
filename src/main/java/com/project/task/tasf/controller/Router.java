package com.project.task.tasf.controller;

import lombok.Getter;

public record Router(String page, com.project.task.tasf.controller.Router.Type type) {

    public enum Type {
        FORWARD,
        REDIRECT
    }

}

