package com.example.libraryapplication.DTOs.response;

import com.example.libraryapplication.DTOs.request.BookBorrowRequest;
import com.example.libraryapplication.DTOs.request.BookRequest;
import com.example.libraryapplication.DTOs.request.PersonRequest;
import com.example.libraryapplication.DTOs.request.PublisherRequest;
import lombok.Data;

import java.util.List;

@Data
public class XMLResponse {
    private List<BookRequest> books;
    private List<PersonRequest> people;
    private List<PublisherRequest> publishers;
    private List<BookBorrowRequest> bookBorrows;
}
