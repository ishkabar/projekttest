package com.example.libraryapplication.DTOs.request;

import lombok.Data;

import java.util.Date;

@Data
public class BookBorrowRequest {
    private Date borrowDate;
    private Date expireDate;
    private int borrowerId;
    private int bookId;
}
