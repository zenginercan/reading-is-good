package com.getir.readingisgood.repository;

import com.getir.readingisgood.model.entity.Book;
import com.getir.readingisgood.model.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    @Query(value = "{$and : [{'id' : ?0  },{'stockCount' :  {$gte : ?1} }]}")
    Optional<Book> getBookByStockCount(String bookId, int orderCount);
}
