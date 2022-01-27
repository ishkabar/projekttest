package com.example.libraryapplication.controllers;

import com.example.libraryapplication.DTOs.request.BookRequest;
import com.example.libraryapplication.models.Book;
import com.example.libraryapplication.models.Person;
import com.example.libraryapplication.models.Publisher;
import com.example.libraryapplication.repositories.IBookRepository;
import com.example.libraryapplication.repositories.IPersonRepository;
import com.example.libraryapplication.repositories.IPublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books") // Deklarowanie sciezki do danego kontrolera np. localhost:8080/books
public class BookController {

    // @Autowired - Wstrzykiwanie zależności. Dodając tę adnotację spring znajduje sam obiekty i je inicjalizuje

    @Autowired
    private IBookRepository repository;

    @Autowired
    private IPersonRepository repositoryPerson;

    @Autowired
    private IPublisherRepository repositoryPublisher;

    //@GetMapping, @PostMapping - Określa typ metody w jaki przesyłane są dane z danej końcówki
    //ResponseEntity - Pozwala na zwrócenie statusu i obiektu

    // Pobieranie wszystkich książek
    @GetMapping
    public ResponseEntity<List<Book>> get() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    // Pobieranie książki po Id
    @GetMapping("{id}")
    public ResponseEntity<Book> get(@PathVariable int id) {
        return new ResponseEntity<>(repository.getById(id), HttpStatus.OK);
    }

    // Dodawanie nowej książki
    @PostMapping
    public ResponseEntity<Book> create(@RequestBody BookRequest request) {

        //Pobieranie author i publisher po id
        //Optional pozwala na sprawdzanie czy obiekt został zwrócony
        Optional<Person> author = repositoryPerson.findById(request.getAuthorId());
        Optional<Publisher> publisher = repositoryPublisher.findById(request.getPublisherId());

        //Sprawdzanie czy obiekt został zwrócony, jeżeli nie to zwraca NOT FOUND
        if(author.isEmpty() || publisher.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        // Tworzenie obiektu i zapisywanie go w bazie
        Book book = new Book(request.getTitle(),request.getReleaseDate(), request.getNumberOfPages(), author.get(), publisher.get());
        book = repository.save(book);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("{id}")
    public ResponseEntity<Book> update(@PathVariable int id, @RequestBody BookRequest request) {
        // Pobranie i sprawdzenie czy book do updatu w ogole istnieje
        Optional<Book> book = repository.findById(id);

        if(book.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Optional<Person> author = repositoryPerson.findById(request.getAuthorId());
        Optional<Publisher> publisher = repositoryPublisher.findById(request.getPublisherId());

        if(author.isEmpty() || publisher.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        // Nadpisywanie obiektu nowymi danymi i zapis jego
        book.get().setAuthor(author.get());
        book.get().setPublisher(publisher.get());
        book.get().setTilte(request.getTitle());
        book.get().setNumberOfPages(request.getNumberOfPages());
        book.get().setReleaseDate(request.getReleaseDate());

        repository.save(book.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
