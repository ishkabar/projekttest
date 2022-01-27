package com.example.libraryapplication.repositories;

import com.example.libraryapplication.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository extends JpaRepository<Person, Integer> {
}