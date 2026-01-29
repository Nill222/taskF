package com.project.task.tasf.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class BaseEntity implements Serializable {
    private Integer id;
}
