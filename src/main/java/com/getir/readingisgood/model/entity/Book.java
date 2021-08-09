package com.getir.readingisgood.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    private String id;

    private String name;

    private String author;

    private String isbn;

    private Long price;

    private Integer status;

    private Integer stockCount;

    private Date creationDate;

    private Date lastUpdateDate;

}
