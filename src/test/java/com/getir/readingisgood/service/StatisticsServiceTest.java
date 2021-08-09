package com.getir.readingisgood.service;

import com.getir.readingisgood.model.entity.Order;
import com.getir.readingisgood.model.order.OrderItem;
import com.getir.readingisgood.model.order.OrderStatusEnum;
import com.getir.readingisgood.model.request.GetOrdersByDateRequest;
import com.getir.readingisgood.model.response.MonthlyStatistic;
import com.getir.readingisgood.model.response.ResponseStatusEnum;
import com.getir.readingisgood.model.response.WebResponse;
import com.getir.readingisgood.repository.BookRepository;
import com.getir.readingisgood.repository.OrderRepository;
import com.getir.readingisgood.util.MonthEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private StatisticsService statisticsService;

    private Map<String, MonthlyStatistic> monthlyValues;

    private WebResponse getMonthlyCustomerStatisticsResponse;

    private List<Order> orders;


    @Before
    public void setMockOutput(){
        setTestStatisticList();
        ReflectionTestUtils.setField(statisticsService,"orderRepository",orderRepository);
        Mockito.when(orderRepository.getOrdersByOwner(Mockito.anyLong())).thenReturn(orders);
        //Mockito.when(statisticsService.getMonthlyCustomerStatistics(Mockito.anyLong())).thenReturn(getMonthlyCustomerStatisticsResponse);
    }

/*    @Test
    public void getMonthlyStatisticsResponse(){
        WebResponse response = null;
        response = statisticsService.getMonthlyCustomerStatistics(12345L);
        assertNotNull(response.getResponseBody());
    }*/

    @Test
    public void getOrdersByOwner(){
        List<Order> response = null;
        response = orderRepository.getOrdersByOwner(12345L);
        assertEquals(response, orders);
    }

    private void setTestStatisticList(){
        monthlyValues = new LinkedHashMap<>();
        MonthlyStatistic may = new MonthlyStatistic();
        may.setTotalBookCount(10);
        may.setTotalOrderCount(2);
        may.setTotalPurchasedAmount(225L);
        MonthlyStatistic june = new MonthlyStatistic();
        may.setTotalBookCount(1);
        may.setTotalOrderCount(1);
        may.setTotalPurchasedAmount(25L);
        monthlyValues.put(MonthEnum.MAY.getName(), may);
        monthlyValues.put(MonthEnum.JUNE.getName(), june);
        String message = "Getting monthly statistics is successful";
        getMonthlyCustomerStatisticsResponse = new WebResponse(ResponseStatusEnum.OK,message,monthlyValues);
        Order order1 = new Order();
        order1.setName("Test 1");
        order1.setId("order1");
        order1.setTotalPrice(140L);
        order1.setCreationDate(new Date());
        OrderItem item1 = new OrderItem();
        item1.setBookId("12");
        item1.setOrderCount(2);
        List<OrderItem> itemList1 = new ArrayList<>();
        itemList1.add(item1);
        order1.setItemList(itemList1);

        Order order2 = new Order();
        order2.setName("Test 2");
        order2.setId("order2");
        order2.setTotalPrice(240L);
        order2.setCreationDate(new Date());
        OrderItem item2 = new OrderItem();
        item2.setBookId("12");
        item2.setOrderCount(2);
        List<OrderItem> itemList2 = new ArrayList<>();
        itemList2.add(item1);
        order2.setItemList(itemList1);

        orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
    }


}
