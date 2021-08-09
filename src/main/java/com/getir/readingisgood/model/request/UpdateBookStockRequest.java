package com.getir.readingisgood.model.request;

import lombok.Data;

@Data
public class UpdateBookStockRequest {

    private String id;

    private Integer stockCount;
}
