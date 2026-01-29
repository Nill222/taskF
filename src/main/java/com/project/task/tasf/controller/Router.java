package com.project.task.tasf.controller;

public record Router(String page, com.project.task.tasf.controller.Router.Type type) {
    public enum Type {
        FORWARD,
        REDIRECT
    }
}
