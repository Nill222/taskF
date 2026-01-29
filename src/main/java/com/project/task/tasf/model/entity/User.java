package com.project.task.tasf.model.entity;


import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
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
