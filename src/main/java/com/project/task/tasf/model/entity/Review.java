package com.project.task.tasf.model.entity;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class Review extends BaseEntity {
    private Integer clientId;
    private Integer orderId;
    private String text;
    private int rating;
}
