package com.getir.readingisgood.service;

import com.getir.readingisgood.model.entity.Customer;
import com.getir.readingisgood.model.entity.Order;
import com.getir.readingisgood.model.order.OrderStatusEnum;
import com.getir.readingisgood.model.request.CreateCustomerRequest;
import com.getir.readingisgood.model.request.GetOrdersByDateRequest;
import com.getir.readingisgood.model.response.ResponseStatusEnum;
import com.getir.readingisgood.model.response.WebResponse;
import com.getir.readingisgood.repository.BookRepository;
import com.getir.readingisgood.repository.CustomerRepository;
import com.getir.readingisgood.repository.OrderRepository;
import com.getir.readingisgood.util.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private OrderService orderService;

    private List<Order> findAllOrderList;

    private WebResponse findAllOrdersResponse;

    private Order createdOrder;

    @Before
    public void setMockOutput(){
        setTestOrderList();
        ReflectionTestUtils.setField(orderService,"orderRepository",orderRepository);
        ReflectionTestUtils.setField(orderService,"bookRepository",bookRepository);
        Mockito.when(orderRepository.findAll()).thenReturn(findAllOrderList);
        Mockito.when(orderRepository.save(createdOrder)).thenReturn(createdOrder);
        Mockito.when(orderRepository.getOrdersByCreationDateBetween(Mockito.any(Date.class),Mockito.any(Date.class))).thenReturn(findAllOrderList);

    }

    @Test
    public void getAllOrdersResponse(){
        WebResponse response = null;
        response = orderService.findAll();
        assertArrayEquals(findAllOrderList.toArray(), ((List)response.getResponseBody()).toArray());
    }

    @Test
    public void getAllOrders(){
        List<Order> orders = null;
        orders = orderRepository.findAll();
        assertEquals(findAllOrderList,orders);
    }

    @Test
    public void getOrdersByDate(){
        List<Order> orders = null;
        orders = orderRepository.getOrdersByCreationDateBetween(new Date(),new Date());
        assertEquals(findAllOrderList,orders);
    }

    @Test
    public void getOrdersByDateResponse(){
        WebResponse response = null;
        GetOrdersByDateRequest req = new GetOrdersByDateRequest();
        req.setStartDate("2021-03-03 23:34:00.000");
        req.setEndDate("2021-02-03 23:34:00.000");
        response = orderService.getOrdersByDate(req);
        assertArrayEquals(findAllOrderList.toArray(), ((List)response.getResponseBody()).toArray());
    }

    @Test
    public void createOrder(){
        Order order = null;
        order = orderRepository.save(createdOrder);
        assertEquals(order,createdOrder);
    }

    private void setTestOrderList(){
        findAllOrderList = new ArrayList<>();
        Order order1 = new Order();
        order1.setName("Order 1");
        order1.setStatus(OrderStatusEnum.ACTIVE);
        order1.setId("af345");
        order1.setOrderCode(123L);
        order1.setCreationDate(new Date());
        order1.setTotalPrice(155L);
        Order order2 = new Order();
        order2.setName("Order 2");
        order2.setStatus(OrderStatusEnum.CANCELLED);
        order2.setId("af346");
        order2.setOrderCode(124L);
        order2.setCreationDate(new Date());
        order2.setTotalPrice(165L);
        findAllOrderList.add(order1);
        findAllOrderList.add(order2);
        findAllOrdersResponse = new WebResponse(ResponseStatusEnum.OK,"OK",findAllOrderList);
        Order orderForCreate = new Order();
        orderForCreate.setName("Test");
        orderForCreate.setOrderCode(125L);
        orderForCreate.setStatus(OrderStatusEnum.COMPLETED);
        orderForCreate.setTotalPrice(100L);
        orderForCreate.setCreationDate(new Date());
        createdOrder = orderForCreate;
    }


}
