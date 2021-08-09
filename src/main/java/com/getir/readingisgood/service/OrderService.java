package com.getir.readingisgood.service;

import com.getir.readingisgood.model.request.CreateOrderRequest;
import com.getir.readingisgood.model.request.GetOrdersByDateRequest;
import com.getir.readingisgood.model.request.UpdateBookStockRequest;
import com.getir.readingisgood.model.entity.Book;
import com.getir.readingisgood.model.entity.Order;
import com.getir.readingisgood.model.order.OrderItem;
import com.getir.readingisgood.model.order.OrderStatusEnum;
import com.getir.readingisgood.model.response.ResponseStatusEnum;
import com.getir.readingisgood.model.response.WebResponse;
import com.getir.readingisgood.repository.BookRepository;
import com.getir.readingisgood.repository.OrderRepository;
import com.getir.readingisgood.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BookRepository bookRepository;

    public WebResponse findAll(){
        List<Order> orders = null;
        try{
            orders = orderRepository.findAll();
            String message = "Getting all orders is successful";
            log.info(message);
            return new WebResponse(ResponseStatusEnum.OK,message,orders);
        }catch (Exception e){
            String message = "Error while getting all orders!";
            log.error(message);
            log.error(e.getMessage());
            return new WebResponse(ResponseStatusEnum.DEFAULT_FAIL_CODE,message,null);
        }
    }

    public WebResponse createOrder(CreateOrderRequest request){
        try{
            List<OrderItem> itemList = request.getItemList();
            Order order = new Order();
            order.setName(request.getName());
            Long orderCount = orderRepository.count();
            order.setOrderCode(orderCount+1);
            List<String> nonExistingBookIds = getBookIdsHaveNoEnounghStock(itemList);
            Boolean isThereNegativeOrderCount = checkForNegativeOrderCounts(itemList);
            if(!nonExistingBookIds.isEmpty()){
                String message = "There are not enough stock for the books! Please change order count.";
                log.info(message);
                return new WebResponse(ResponseStatusEnum.DEFAULT_FAIL_CODE, message, nonExistingBookIds);
            }
            if(isThereNegativeOrderCount){
                String message = "There are wrong order counts. Please check them.";
                log.info(message);
                return new WebResponse(ResponseStatusEnum.DEFAULT_FAIL_CODE, message, null);
            }
            order.setItemList(itemList);
            order.setOwner(request.getOwner());
            order.setTotalPrice(calculateOrderTotalPrice(request.getItemList()));
            order.setStatus(OrderStatusEnum.ACTIVE);
            order.setCreationDate(new Date());
            updateStockCounts(itemList);
            order = orderRepository.save(order);
            String message = "Creating new order is successful";
            log.info(message);
            return new WebResponse(ResponseStatusEnum.OK, message, order);
        }catch(Exception e){
            String message = "Error while creating order!";
            log.error(message);
            log.error(e.getMessage());
            return new WebResponse(ResponseStatusEnum.DEFAULT_FAIL_CODE,message,null);
        }
    }

    public WebResponse getOrderById(String orderId){
        try{
            Order orderEntity = null;
            Optional<Order> order = orderRepository.findById(orderId);
            if(order.isPresent()){
                orderEntity = order.get();
            }
            String message = "Getting order by ID is successful";
            log.info(message);
            return new WebResponse(ResponseStatusEnum.OK, message, orderEntity);
        }catch (Exception e){
            String message = "Error while getting order by order ID!";
            log.error(message);
            log.error(e.getMessage());
            return new WebResponse(ResponseStatusEnum.DEFAULT_FAIL_CODE, message, null);
        }
    }

    public WebResponse getOrdersByDate(GetOrdersByDateRequest request){
        try{
            Date startDate = DateUtil.dateStringToDate(request.getStartDate());
            Date endDate = DateUtil.dateStringToDate(request.getEndDate());
            List<Order> orders = orderRepository.getOrdersByCreationDateBetween(startDate, endDate);
            String message = "Getting orders by date is successful";
            log.info(message);
            return new WebResponse(ResponseStatusEnum.OK, message, orders);
        }catch (Exception e){
            String message = "Error while getting orders by date!";
            log.error(message);
            return new WebResponse(ResponseStatusEnum.DEFAULT_FAIL_CODE, message, null);
        }
    }

    private List<String> getBookIdsHaveNoEnounghStock(List<OrderItem> orderItems){
        List<String> bookIdsHaveNoEnoughStock = new ArrayList<>();
        for(OrderItem item: orderItems){
            Optional<Book> book = bookRepository.getBookByStockCount(item.getBookId(), item.getOrderCount());
            if(!book.isPresent()){
                bookIdsHaveNoEnoughStock.add(item.getBookId());
            }
        }
        return bookIdsHaveNoEnoughStock;
    }

    private Boolean checkForNegativeOrderCounts(List<OrderItem> orderItems){
        Boolean isThereNegativeOrderCount =  Boolean.FALSE;
        for(OrderItem item: orderItems){
            if(item.getOrderCount() < 1){
                isThereNegativeOrderCount = Boolean.TRUE;
            }
        }
        return isThereNegativeOrderCount;
    }

    private Long calculateOrderTotalPrice(List<OrderItem> itemList){
        Long totalPrice = 0L;
        Optional<Book> book;
        for(OrderItem item: itemList){
            book = bookRepository.findById(item.getBookId());
            if(book.isPresent()){
                Long itemPrice = book.get().getPrice() * item.getOrderCount();
                totalPrice += itemPrice;
            }
        }
        return totalPrice;
    }

    private void updateStockCounts(List<OrderItem> itemList){
        Optional<Book> book;
        for(OrderItem item: itemList){
            book = bookRepository.findById(item.getBookId());
            if(book.isPresent()){
                Book bookEntity = book.get();
                bookEntity.setStockCount(bookEntity.getStockCount() - item.getOrderCount());
                bookRepository.save(bookEntity);
            }
        }
    }
}
