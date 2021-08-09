package com.getir.readingisgood.service;

import com.getir.readingisgood.model.entity.Order;
import com.getir.readingisgood.model.request.CreateCustomerRequest;
import com.getir.readingisgood.model.entity.Customer;
import com.getir.readingisgood.model.request.GetOrdersOfCustomerRequest;
import com.getir.readingisgood.model.response.ResponseStatusEnum;
import com.getir.readingisgood.model.response.WebResponse;
import com.getir.readingisgood.repository.CustomerRepository;
import com.getir.readingisgood.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    public WebResponse findAll(){
        List<Customer> customers = null;
        try{
            customers = customerRepository.findAll();
            String message = "Getting all customers is successful";
            log.info(message);
            return new WebResponse(ResponseStatusEnum.OK,message,customers);
        }catch (Exception e){
            String message = "Error while getting all customers!";
            log.error(message);
            log.error(e.getMessage());
            return new WebResponse(ResponseStatusEnum.DEFAULT_FAIL_CODE,message,null);
        }
    }

    public WebResponse createCustomer(CreateCustomerRequest request){
        try{
            Boolean isExistingPhoneNumber = checkIfThereIsCustomerWtihPhoneNumber(request.getPhoneNumber());
            Boolean isExistingEmail = checkIfThereIsCustomerWtihEmail(request.getEmail());
            if(isExistingPhoneNumber || isExistingEmail){
                String message = "The phone number or e-mail address already used!";
                log.info(message);
                return new WebResponse(ResponseStatusEnum.DEFAULT_FAIL_CODE,message,null);
            }
            Customer customer = new Customer();
            customer.setFirstName(request.getFirstName());
            customer.setLastName(request.getLastName());
            customer.setBirthDate(new Date());
            customer.setEmail(request.getEmail());
            customer.setAddress(request.getAddress());
            customer.setPhoneNumber(request.getPhoneNumber());
            customer.setPassword(request.getPassword());
            customer.setStatus(1);
            customer.setCreationDate(new Date());
            customer = this.customerRepository.save(customer);
            String message = "Creating new customer is successful";
            log.info(message);
            return new WebResponse(ResponseStatusEnum.OK,message,customer);
        }catch (Exception e){
            String message = "Error while creating new customer!";
            log.error(message);
            log.error(e.getMessage());
            return new WebResponse(ResponseStatusEnum.DEFAULT_FAIL_CODE,message,null);
        }
    }

    public WebResponse getOrdersOfCustomer(GetOrdersOfCustomerRequest request){
        Page<Order> pageOrders;
        List<Order> orders = new ArrayList<>();
        try{
            Pageable paging = PageRequest.of(request.getPageNumber(), request.getPageSize());
            pageOrders = orderRepository.getOrdersByOwner(request.getPhoneNumber(), paging);
            orders = pageOrders.getContent();
            String message = "Getting all orders of customer is successful";
            log.info(message);
            return new WebResponse(ResponseStatusEnum.OK,message,orders);
        }catch (Exception e){
            String message = "Error while getting all orders of customer!";
            log.error(message);
            log.error(e.getMessage());
            return new WebResponse(ResponseStatusEnum.DEFAULT_FAIL_CODE,message,null);
        }
    }

    private Boolean checkIfThereIsCustomerWtihEmail(String email){
        Customer c = null;
        c = customerRepository.findByEmail(email);
        if(c != null){
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }
    }

    private Boolean checkIfThereIsCustomerWtihPhoneNumber(Long phoneNumber){
        Customer c = null;
        c = customerRepository.findByPhoneNumber(phoneNumber);
        if(c != null){
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }
    }
}
