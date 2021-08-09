package com.getir.readingisgood.service;

import com.getir.readingisgood.model.entity.Order;
import com.getir.readingisgood.model.order.OrderItem;
import com.getir.readingisgood.util.MonthEnum;
import com.getir.readingisgood.model.response.MonthlyStatistic;
import com.getir.readingisgood.model.response.ResponseStatusEnum;
import com.getir.readingisgood.model.response.WebResponse;
import com.getir.readingisgood.repository.BookRepository;
import com.getir.readingisgood.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
@Slf4j
public class StatisticsService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public WebResponse getMonthlyCustomerStatistics(Long phoneNumber){
        List<Order> orders = null;
        try{
            orders = orderRepository.getOrdersByOwner(phoneNumber);
            Map<String, MonthlyStatistic> monthlyValues = new LinkedHashMap<>();
            for(Order order: orders){
                LocalDate localDate = order.getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int month = localDate.getMonthValue();
                MonthEnum monthEnum = MonthEnum.getFromId(month);
                MonthlyStatistic statistic;
                if(monthlyValues.containsKey(monthEnum.getName())){
                    statistic = monthlyValues.get(monthEnum.getName());
                }else {
                    statistic = new MonthlyStatistic();
                }
                statistic.setTotalOrderCount(statistic.getTotalOrderCount() + 1);
                statistic.setTotalPurchasedAmount(statistic.getTotalPurchasedAmount() + order.getTotalPrice());
                statistic.setTotalBookCount(statistic.getTotalBookCount() + getTotalBookCountFromItemList(order.getItemList()));
                monthlyValues.put(monthEnum.getName(), statistic);
            }
            String message = "Getting monthly statistics for customer is successful";
            log.info(message);
            return new WebResponse(ResponseStatusEnum.OK,message,monthlyValues);
        }catch (Exception e){
            String message = "Error while getting monthly statistics for customer!";
            log.error(message);
            log.error(e.getMessage());
            return new WebResponse(ResponseStatusEnum.DEFAULT_FAIL_CODE,message,null);
        }
    }

    private Integer getTotalBookCountFromItemList(List<OrderItem> itemList){
        Integer totalBookCount = 0;
        for(OrderItem item: itemList){
            totalBookCount += item.getOrderCount();
        }
        return totalBookCount;
    }

/*    public void getStatistics(){

        Criteria criteria = new Criteria().andOperator(
                where("phoneNumber").is("5351234568"));

        ProjectionOperation dateProjection = project()
                .and("totalPrice").as("totalPrice")
                .and("creationDate").extractYear().as("year")
                .and("creationDate").extractMonth().as("month");

        GroupOperation groupBy = group("year", "month")
                .sum("totalPrice").as("total")
                .count().as("count");

        Aggregation agg = newAggregation(
                match(criteria),
                dateProjection,
                groupBy,
                sort(Sort.Direction.ASC, "year", "month")
        );

//Convert the aggregation result into a List
        AggregationResults<Object> groupResults = mongoTemplate.aggregate(agg, Order.class, Object.class);
        List<Object> resultList = groupResults.getMappedResults();

    }*/
}
