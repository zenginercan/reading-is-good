package com.getir.readingisgood.model.request;

import lombok.Data;

@Data
public class CreateBookRequest {

    private String name;

    private String author;

    private String isbn;

    private Long price;

    private Integer status;

    private Integer stockCount;

}
