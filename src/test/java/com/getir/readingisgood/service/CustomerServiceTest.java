package com.getir.readingisgood.service;

import com.getir.readingisgood.model.entity.Customer;
import com.getir.readingisgood.model.entity.Order;
import com.getir.readingisgood.model.request.CreateCustomerRequest;
import com.getir.readingisgood.model.response.ResponseStatusEnum;
import com.getir.readingisgood.model.response.WebResponse;
import com.getir.readingisgood.repository.CustomerRepository;
import com.getir.readingisgood.repository.OrderRepository;
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
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private CustomerService customerService;

    private List<Customer> findAllCustomerList;

    private CreateCustomerRequest createCustomerRequest;

    private WebResponse findAllCustomersResponse;

    private Customer createdCustomer;

    private List<Order> orders;

    @Before
    public void setMockOutput(){
        setTestCustomerList();
        ReflectionTestUtils.setField(customerService,"customerRepository",customerRepository);
        ReflectionTestUtils.setField(customerService,"orderRepository",orderRepository);
        Mockito.when(customerRepository.findAll()).thenReturn(findAllCustomerList);
        Mockito.when(customerRepository.save(createdCustomer)).thenReturn(createdCustomer);
        Mockito.when(orderRepository.getOrdersByOwner(Mockito.anyLong())).thenReturn(orders);

    }

    @Test
    public void getAllCustomersResponse(){
        WebResponse response = null;
        response = customerService.findAll();
        assertArrayEquals(findAllCustomerList.toArray(), ((List)response.getResponseBody()).toArray());
    }

    @Test
    public void getAllCustomers(){
        List<Customer> customers = null;
        customers = customerRepository.findAll();
        assertEquals(findAllCustomerList,customers);
    }

    private CreateCustomerRequest getCreateCustomerRequest() {
        return createCustomerRequest;
    }

    @Test
    public void createCustomer(){
        Customer customer = null;
        customer = customerRepository.save(createdCustomer);
        assertEquals(customer,createdCustomer);
    }

    @Test
    public void getOrdersOfCustomer(){
        List<Order> orderList = null;
        orderList = orderRepository.getOrdersByOwner(123L);
        assertEquals(orderList,orders);
    }

    private void setTestCustomerList(){
        findAllCustomerList = new ArrayList<>();
        Customer customer1 = new Customer();
        customer1.setId("10xyz12");
        customer1.setStatus(1);
        customer1.setPhoneNumber(5001234567L);
        customer1.setPassword("abc12");
        customer1.setAddress("Istanbul");
        customer1.setFirstName("ALbert");
        customer1.setLastName("Einstein");
        customer1.setEmail("a@a.com");
        customer1.setBirthDate(new Date());
        findAllCustomerList.add(customer1);
        Customer customer2 = new Customer();
        customer2.setId("10xyz12");
        customer2.setStatus(1);
        customer2.setPhoneNumber(5001234567L);
        customer2.setPassword("abc123");
        customer2.setAddress("New York");
        customer2.setFirstName("Steve");
        customer2.setLastName("Jobs");
        customer2.setEmail("a@apple.com");
        customer2.setBirthDate(new Date());
        findAllCustomerList.add(customer2);
        findAllCustomersResponse = new WebResponse(ResponseStatusEnum.OK,"OK",findAllCustomerList);
        CreateCustomerRequest createRequest = new CreateCustomerRequest();
        createRequest.setFirstName("Test");
        createRequest.setLastName("User");
        createRequest.setAddress("Ankara");
        createRequest.setEmail("c@c.com");
        createRequest.setPhoneNumber(5003333344L);
        Customer customerForCreate = new Customer();
        customerForCreate.setFirstName("Test");
        customerForCreate.setLastName("User");
        customerForCreate.setAddress("Ankara");
        customerForCreate.setEmail("c@c.com");
        customerForCreate.setPhoneNumber(5003333344L);
        createdCustomer = customerForCreate;
        Order order1 = new Order();
        order1.setOwner(customer1);
        order1.setName("Test 1");
        order1.setId("order1");
        order1.setTotalPrice(140L);
        Order order2 = new Order();
        order1.setOwner(customer1);
        order1.setName("Test 2");
        order1.setId("order2");
        order1.setTotalPrice(240L);
        orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
    }


}
