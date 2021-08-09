package com.getir.readingisgood.model.request;

import lombok.Data;

@Data
public class GetOrdersByDateRequest {

    private String startDate;

    private String endDate;
}
