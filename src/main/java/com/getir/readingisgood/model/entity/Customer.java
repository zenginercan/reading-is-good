package com.getir.readingisgood.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("Customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private Date birthDate;

    private String email;

    private String address;

    private Long phoneNumber;

    private String password;

    private Integer status;

    private Date creationDate;

    public Customer(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
