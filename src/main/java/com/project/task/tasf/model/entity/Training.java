package com.project.task.tasf.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Training extends BaseEntity {
    private Integer trainerId;
    private Integer orderId;
    private String exercise;
    private int countApproaches;
    private Double tonnage;
}
