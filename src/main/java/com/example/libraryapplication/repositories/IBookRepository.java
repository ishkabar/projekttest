package com.example.libraryapplication.repositories;

import com.example.libraryapplication.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book, Integer> {

}