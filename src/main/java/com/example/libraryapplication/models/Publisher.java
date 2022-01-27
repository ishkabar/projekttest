package com.example.libraryapplication.models;

import javax.persistence.*;

@Entity
@Table(name = "publisher")
public class Publisher {

    public Publisher(String name) {
        this.name = name;
    }

    public Publisher() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}