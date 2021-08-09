package com.getir.readingisgood.service;

import com.getir.readingisgood.model.request.CreateBookRequest;
import com.getir.readingisgood.model.request.UpdateBookStockRequest;
import com.getir.readingisgood.model.entity.Book;
import com.getir.readingisgood.model.response.ResponseStatusEnum;
import com.getir.readingisgood.model.response.WebResponse;
import com.getir.readingisgood.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public WebResponse findAll(){
        List<Book> books = null;
        try{
            books = bookRepository.findAll();
            String message = "Getting all books is successful";
            log.info(message);
            return new WebResponse(ResponseStatusEnum.OK,message,books);
        }catch (Exception e){
            String message = "Error while getting all books!";
            log.error(message);
            log.error(e.getMessage());
            return new WebResponse(ResponseStatusEnum.DEFAULT_FAIL_CODE,message,null);
        }
    }

    public WebResponse createBook(CreateBookRequest request){
        try{
            Book book = new Book();
            book.setName(request.getName());
            book.setIsbn(request.getIsbn());
            book.setAuthor(request.getAuthor());
            book.setPrice(request.getPrice());
            book.setStatus(request.getStatus());
            book.setStockCount(request.getStockCount());
            book.setCreationDate(new Date());
            book.setLastUpdateDate(new Date());
            book = this.bookRepository.save(book);
            String message = "Creating new book is successful";
            log.info(message);
            return new WebResponse(ResponseStatusEnum.OK,message,book);
        }catch(Exception e){
            String message = "Error while creating book!";
            log.error(message);
            log.error(e.getMessage());
            return new WebResponse(ResponseStatusEnum.DEFAULT_FAIL_CODE,message,null);
        }
    }

    public WebResponse updateBookStock(UpdateBookStockRequest request){
        try {
            Book book = null;
            Optional<Book> existingBook = this.bookRepository.findById(request.getId());
            if (existingBook.isPresent()) {
                book = existingBook.get();
                book.setStockCount(request.getStockCount());
                book = this.bookRepository.save(book);
            }
            String message = "Updating book stock is successful";
            log.info(message);
            return new WebResponse(ResponseStatusEnum.OK,message,book);
        } catch (Exception e){
            String message = "Error while updating book stock!";
            log.error(message);
            log.error(e.getMessage());
            return new WebResponse(ResponseStatusEnum.DEFAULT_FAIL_CODE,message,null);
        }
    }
}
