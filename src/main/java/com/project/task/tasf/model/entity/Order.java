package com.project.task.tasf.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class Order extends BaseEntity {
    private Integer clientId;
    private Double price;
    private OrderType orderType;
}
