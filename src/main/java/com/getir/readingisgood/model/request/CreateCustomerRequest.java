package com.getir.readingisgood.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateCustomerRequest {

    private String firstName;

    private String lastName;

    //private Date birthDate;

    @NotNull
    private String email;

    private String address;

    @NotNull
    private Long phoneNumber;

    private String password;
}
