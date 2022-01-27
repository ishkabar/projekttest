package com.example.libraryapplication.repositories;

import com.example.libraryapplication.models.BookBorrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookBorrowRepository extends JpaRepository<BookBorrow, Integer> {
}