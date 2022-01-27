package com.example.libraryapplication.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "person")
public class Person {

    public Person(String firstname, String surname, Date dateOfBirth) {
        this.firstname = firstname;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

    public Person() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String firstname;
    private String surname;
    private Date dateOfBirth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}