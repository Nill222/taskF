package com.project.task.tasf.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Review extends BaseEntity {
    private Integer clientId;
    private Integer orderId;
    private String text;
    private int rating;
}
