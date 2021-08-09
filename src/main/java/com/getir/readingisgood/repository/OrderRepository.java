package com.getir.readingisgood.repository;

import com.getir.readingisgood.model.entity.Book;
import com.getir.readingisgood.model.entity.Customer;
import com.getir.readingisgood.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    //public Customer findByFirstName(String firstName);
    //public List<Customer> findByLastName(String lastName);

    @Query("{'creationDate': {$gte: ?0, $lte:?1 }}")
    List<Order> getOrdersByCreationDateBetween(Date startDate, Date endDate);

    @Query(value = "{'owner.phoneNumber' : ?0}")
    Page<Order> getOrdersByOwner(Long mobileNumber, Pageable pageable);

    @Query(value = "{'owner.phoneNumber' : ?0}")
    List<Order> getOrdersByOwner(Long mobileNumber);


    //@Query(value = "{'owner.phoneNumber' : ?0}")
    //Page<Order> getOrdersByOwner (Customer owner, Pageable pageable);
}
