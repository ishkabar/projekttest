package com.example.libraryapplication.repositories;

import com.example.libraryapplication.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPublisherRepository extends JpaRepository<Publisher, Integer> {
}