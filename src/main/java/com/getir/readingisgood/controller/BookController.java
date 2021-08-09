package com.getir.readingisgood.controller;

import com.getir.readingisgood.model.request.CreateBookRequest;
import com.getir.readingisgood.model.request.UpdateBookStockRequest;
import com.getir.readingisgood.model.response.WebResponse;
import com.getir.readingisgood.service.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;


    @PostMapping(path = "/create",
                 consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Creates a new Book object")
    public WebResponse createBook(@RequestBody @Valid CreateBookRequest request) {
        return this.bookService.createBook(request);
    }

    @PostMapping(path = "/updatestock",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Updates the stock count of the book with given ID")
    public WebResponse updateStock(@RequestBody @Valid UpdateBookStockRequest request) {
        return this.bookService.updateBookStock(request);
    }

    @GetMapping(path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Gets all Book objects")
    public WebResponse getAllBooks() {
        return this.bookService.findAll();
    }


}
