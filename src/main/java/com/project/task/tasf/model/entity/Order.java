package com.project.task.tasf.model.entity;

import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class Order extends BaseEntity {
    private Integer clientId;
    private Double price;
    private OrderType orderType;
}
