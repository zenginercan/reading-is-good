package com.getir.readingisgood.controller;

import com.getir.readingisgood.model.entity.Customer;
import com.getir.readingisgood.model.request.CreateOrderRequest;
import com.getir.readingisgood.model.request.GetOrdersByDateRequest;
import com.getir.readingisgood.model.response.WebResponse;
import com.getir.readingisgood.service.OrderService;
import com.getir.readingisgood.service.StatisticsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping(path = "/monthly",
                 produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Gets order statistics by month, for the customer has the given phone number")
    public WebResponse createOrder(@RequestParam(name= "phoneNumber") Long phoneNumber) {
        return statisticsService.getMonthlyCustomerStatistics(phoneNumber);
    }

}
