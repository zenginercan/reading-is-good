package com.getir.readingisgood.model.entity;

import com.getir.readingisgood.model.order.OrderItem;
import com.getir.readingisgood.model.order.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    private String id;

    private String name;

    private Long orderCode;

    private List<OrderItem> itemList;

    private Customer owner;

    private Long totalPrice;

    private OrderStatusEnum status;

    private Date creationDate;


}
