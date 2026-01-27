package com.project.task.tasf.model.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends BaseEntity {
    private String name;
    private String passwordHash;
    private UserRole role;

    public User(String name, String passwordHash, UserRole role) {
        this.name = name;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public User() {
    }
}
