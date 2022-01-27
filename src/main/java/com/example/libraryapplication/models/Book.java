package com.example.libraryapplication.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "book")
public class Book {

    public Book(String tilte, Date releaseDate, int numberOfPages, Person author, Publisher publisher) {
        this.tilte = tilte;
        this.releaseDate = releaseDate;
        this.numberOfPages = numberOfPages;
        this.author = author;
        this.publisher = publisher;
    }

    public Book() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String tilte;
    private Date releaseDate;
    private int numberOfPages;

    @ManyToOne
    private Person author;

    @ManyToOne
    private Publisher publisher;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTilte() {
        return tilte;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
