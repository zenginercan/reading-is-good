package com.getir.readingisgood.service;

import com.getir.readingisgood.model.entity.Book;
import com.getir.readingisgood.model.entity.Customer;
import com.getir.readingisgood.model.entity.Order;
import com.getir.readingisgood.model.request.CreateBookRequest;
import com.getir.readingisgood.model.request.CreateCustomerRequest;
import com.getir.readingisgood.model.request.UpdateBookStockRequest;
import com.getir.readingisgood.model.response.ResponseStatusEnum;
import com.getir.readingisgood.model.response.WebResponse;
import com.getir.readingisgood.repository.BookRepository;
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
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private List<Book> findAllBookList;

    private CreateBookRequest createBookRequest;

    private UpdateBookStockRequest updateBookStockRequest;

    private WebResponse findAllBooksResponse;

    private Book createdBook;

    @Before
    public void setMockOutput(){
        setTestBookList();
        ReflectionTestUtils.setField(bookService,"bookRepository",bookRepository);
        Mockito.when(bookRepository.findAll()).thenReturn(findAllBookList);
        Mockito.when(bookRepository.save(createdBook)).thenReturn(createdBook);
    }

    @Test
    public void getAllBooksResponse(){
        WebResponse response = null;
        response = bookService.findAll();
        assertArrayEquals(findAllBookList.toArray(), ((List)response.getResponseBody()).toArray());
    }

    @Test
    public void getAllBooks(){
        List<Book> books = null;
        books = bookRepository.findAll();
        assertEquals(findAllBookList,books);
    }


/*    @Test
    public void createCustomerResponse(){
        WebResponse response = null;
        response = customerService.createCustomer(getCreateCustomerRequest());
        assertEquals(createdCustomer, (Customer)response.getResponseBody());
    }*/

 /*   private CreateCustomerRequest getCreateCustomerRequest() {
        return createCustomerRequest;
    }*/

    @Test
    public void createBook(){
        Book book = null;
        book = bookRepository.save(createdBook);
        assertEquals(book,createdBook);
    }


    private void setTestBookList(){
        findAllBookList = new ArrayList<>();
        Book book1 = new Book();
        book1.setName("TLOTR - Fellowship");
        book1.setAuthor("J.R.R. Tolkien");
        book1.setStatus(1);
        book1.setPrice(50L);
        book1.setStockCount(10);
        book1.setCreationDate(new Date());
        Book book2 = new Book();
        book2.setName("TLOTR - The Two Towers");
        book2.setAuthor("J.R.R. Tolkien");
        book2.setStatus(1);
        book2.setPrice(50L);
        book2.setStockCount(10);
        book2.setCreationDate(new Date());
        findAllBookList.add(book1);
        findAllBookList.add(book2);
        findAllBooksResponse = new WebResponse(ResponseStatusEnum.OK,"OK",findAllBookList);
        Book bookForCreate = new Book();
        bookForCreate.setAuthor("Sabahattin Ali");
        bookForCreate.setName("Kuyucaklı Yusuf");
        bookForCreate.setStatus(1);
        bookForCreate.setIsbn("978-145-54-9");
        bookForCreate.setStockCount(25);
        createdBook = bookForCreate;
    }


}
