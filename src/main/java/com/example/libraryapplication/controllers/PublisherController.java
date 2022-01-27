package com.example.libraryapplication.controllers;

import com.example.libraryapplication.DTOs.request.PublisherRequest;
import com.example.libraryapplication.models.Publisher;
import com.example.libraryapplication.repositories.IPublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private IPublisherRepository repository;

    @GetMapping
    public ResponseEntity<List<Publisher>> get() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Publisher> get(@PathVariable int id) {
        return new ResponseEntity<>(repository.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Publisher> create(@RequestBody PublisherRequest request) {
        Publisher publisher = new Publisher(request.getName());
        publisher = repository.save(publisher);
        return new ResponseEntity<>(publisher, HttpStatus.OK);
    }

    @PostMapping("{id}")
    public ResponseEntity<Publisher> update(@PathVariable int id, @RequestBody PublisherRequest request) {
        Optional<Publisher> publisher = repository.findById(id);

        if(publisher.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        publisher.get().setName(request.getName());

        repository.save(publisher.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
