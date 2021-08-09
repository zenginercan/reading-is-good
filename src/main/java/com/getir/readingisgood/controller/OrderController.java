package com.getir.readingisgood.controller;

import com.getir.readingisgood.model.request.CreateOrderRequest;
import com.getir.readingisgood.model.request.GetOrdersByDateRequest;
import com.getir.readingisgood.model.response.WebResponse;
import com.getir.readingisgood.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(path = "/create",
                 consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Creates a new Order for the given Customer object")
    public WebResponse createOrder(@RequestBody @Valid CreateOrderRequest request) {
        return this.orderService.createOrder(request);
    }

    @GetMapping(path="",
                produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Gets Order object with given ID")
    public WebResponse getOrderById(@RequestParam(name= "id") String id) {
        return this.orderService.getOrderById(id);
    }

    @PostMapping(path = "/getordersbydate",
                 consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Gets all orders between two dates")
    public WebResponse getOrdersByDate(@RequestBody @Valid GetOrdersByDateRequest request) {
        return this.orderService.getOrdersByDate(request);
    }

}
