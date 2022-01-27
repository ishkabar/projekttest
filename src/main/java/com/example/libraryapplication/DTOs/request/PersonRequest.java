package com.example.libraryapplication.DTOs.request;

import lombok.Data;
import java.util.Date;

@Data
public class PersonRequest {
    private String firstname;
    private String surname;
    private Date dateOfBirth;
}
