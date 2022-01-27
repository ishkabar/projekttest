package com.example.libraryapplication.controllers;

import com.example.libraryapplication.DTOs.request.PersonRequest;
import com.example.libraryapplication.models.Person;
import com.example.libraryapplication.repositories.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired
    private IPersonRepository repository;

    @GetMapping
    public ResponseEntity<List<Person>> get() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Person> get(@PathVariable int id) {
        return new ResponseEntity<>(repository.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody PersonRequest request) {
        Person person = new Person(request.getFirstname(), request.getSurname(),request.getDateOfBirth());
        person = repository.save(person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping("{id}")
    public ResponseEntity<Person> update(@PathVariable int id, @RequestBody PersonRequest request) {
        Optional<Person> person = repository.findById(id);

        if(person.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        person.get().setFirstname(request.getFirstname());
        person.get().setSurname(request.getSurname());
        person.get().setDateOfBirth(request.getDateOfBirth());

        repository.save(person.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }



}
