package com.getir.readingisgood.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrdersOfCustomerRequest {

    private Long phoneNumber;

    private Integer pageNumber;

    private Integer pageSize;
}
