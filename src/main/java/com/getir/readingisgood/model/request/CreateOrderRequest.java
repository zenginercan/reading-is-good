package com.getir.readingisgood.model.request;

import com.getir.readingisgood.model.entity.Customer;
import com.getir.readingisgood.model.order.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {

    private String name;

    private List<OrderItem> itemList;

    private Customer owner;

}
