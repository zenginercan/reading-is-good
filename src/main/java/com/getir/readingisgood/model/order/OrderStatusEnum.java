package com.getir.readingisgood.model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatusEnum {

    ACTIVE(0, "ACTIVE"),
    COMPLETED(1, "COMPLETED"),
    CANCELLED(2, "CANCELLED");

    private Integer statusId;
    private String statusName;
}
