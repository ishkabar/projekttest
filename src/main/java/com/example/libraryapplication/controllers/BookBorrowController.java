package com.example.libraryapplication.controllers;

import com.example.libraryapplication.DTOs.request.BookBorrowRequest;
import com.example.libraryapplication.models.Book;
import com.example.libraryapplication.models.BookBorrow;
import com.example.libraryapplication.models.Person;
import com.example.libraryapplication.repositories.IBookBorrowRepository;
import com.example.libraryapplication.repositories.IBookRepository;
import com.example.libraryapplication.repositories.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookBorrows")
public class BookBorrowController {

    @Autowired
    private IBookBorrowRepository repository;

    @Autowired
    private IPersonRepository repositoryPerson;

    @Autowired
    private IBookRepository repositoryBook;

    @GetMapping
    public ResponseEntity<List<BookBorrow>> get() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BookBorrow> get(@PathVariable int id) {
        return new ResponseEntity<>(repository.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookBorrow> create(@RequestBody BookBorrowRequest request) {
        Optional<Person> borrower = repositoryPerson.findById(request.getBorrowerId());
        Optional<Book> book = repositoryBook.findById(request.getBookId());

        if(borrower.isEmpty() || book.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        BookBorrow bookBorrowed = new BookBorrow(request.getBorrowDate(), request.getExpireDate(), borrower.get(), book.get());
        bookBorrowed = repository.save(bookBorrowed);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("{id}")
    public ResponseEntity<BookBorrow> update(@PathVariable int id, @RequestBody BookBorrowRequest request) {
        Optional<BookBorrow> bookBorrow = repository.findById(id);

        if(bookBorrow.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Optional<Person> borrower = repositoryPerson.findById(request.getBorrowerId());
        Optional<Book> book = repositoryBook.findById(request.getBookId());

        if(borrower.isEmpty() || book.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        bookBorrow.get().setBorrowDate(request.getBorrowDate());
        bookBorrow.get().setExpireDate(request.getExpireDate());
        bookBorrow.get().setBook(book.get());
        bookBorrow.get().setBorrower(borrower.get());

        repository.save(bookBorrow.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
