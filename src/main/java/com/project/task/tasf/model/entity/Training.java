package com.project.task.tasf.model.entity;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class Training extends BaseEntity {
    private Integer trainerId;
    private Integer orderId;
    private String exercise;
    private int countApproaches;
    private Double tonnage;
}
