package com.example.libraryapplication.DTOs.request;

import lombok.Data;

import java.util.Date;

@Data
public class BookRequest {
    private String title;
    private Date releaseDate;
    private int numberOfPages;
    private int authorId;
    private int publisherId;
}
