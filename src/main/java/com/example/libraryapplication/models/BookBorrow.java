package com.example.libraryapplication.models;

import javax.persistence.*;
import java.time.Period;
import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "book_borrow")
public class BookBorrow {

    public BookBorrow(Date borrowDate, Date expireDate, Person borrower, Book book) {
        this.borrowDate = borrowDate;
        this.expireDate = expireDate;
        this.borrower = borrower;
        this.book = book;
    }

    public BookBorrow() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private Date borrowDate;
    private Date expireDate;

    @ManyToOne
    private Person borrower;

    @ManyToOne
    private Book book;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Person getBorrower() {
        return borrower;
    }

    public void setBorrower(Person borrower) {
        this.borrower = borrower;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}