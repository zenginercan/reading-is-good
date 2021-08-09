package com.getir.readingisgood.repository;

import com.getir.readingisgood.model.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    public Customer findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);
    public Customer findByPhoneNumber(Long phoneNumber);
    public Customer findByEmail(String email);
}
