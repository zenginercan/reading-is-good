package com.getir.readingisgood.controller;

import com.getir.readingisgood.model.entity.Customer;
import com.getir.readingisgood.model.request.CreateCustomerRequest;
import com.getir.readingisgood.model.request.GetOrdersOfCustomerRequest;
import com.getir.readingisgood.model.response.WebResponse;
import com.getir.readingisgood.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/create",
                 consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Creates a new Customer object")
    public WebResponse createCustomer(@RequestBody @Valid CreateCustomerRequest request) {
        return this.customerService.createCustomer(request);
    }

    @PostMapping(path = "/getorders",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Getss all orders of customer with the given ID")
    public WebResponse getOrdersOfCustomer(@RequestBody @Valid GetOrdersOfCustomerRequest request) {
        return this.customerService.getOrdersOfCustomer(request);
    }

    @GetMapping(path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Gets all Customer objects")
    public WebResponse getAllCustomers() {
        return this.customerService.findAll();
    }
}
