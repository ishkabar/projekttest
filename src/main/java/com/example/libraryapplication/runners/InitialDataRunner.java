package com.example.libraryapplication.runners;

import com.example.libraryapplication.DTOs.request.BookBorrowRequest;
import com.example.libraryapplication.DTOs.request.BookRequest;
import com.example.libraryapplication.DTOs.request.PersonRequest;
import com.example.libraryapplication.DTOs.request.PublisherRequest;
import com.example.libraryapplication.DTOs.response.XMLResponse;
import com.example.libraryapplication.controllers.BookBorrowController;
import com.example.libraryapplication.controllers.BookController;
import com.example.libraryapplication.controllers.PersonController;
import com.example.libraryapplication.controllers.PublisherController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;

import static com.example.libraryapplication.services.Reader.read;

@Component
@Order(1)
public class InitialDataRunner implements CommandLineRunner {

    //CommandLineRunner - Jak cos po tym implementuje to spring uzywa tej klasy przy uruchamianiu apki

    @Autowired
    private BookController bookController;

    @Autowired
    private PersonController personController;

    @Autowired
    private PublisherController publisherController;

    @Autowired
    private BookBorrowController bookBorrowController;

    @Override
    public void run(String... args) throws Exception {

        //Pobieranie danych z XML i mapowanie ich do obiektu XMLResponse
        File file = new File("src/main/resources/data.xml");
        XmlMapper xmlMapper = new XmlMapper();
        String xml = read(new FileInputStream(file));
        TypeReference<XMLResponse> referenceBooks = new TypeReference<>(){};
        XMLResponse response = xmlMapper.readValue(xml, referenceBooks);

        // Dodawanie do bazy zaciągnietych obiektów z XML za pomocą istniejacych kontrolerów
        for(PersonRequest person : response.getPeople()){
            personController.create(person);
        }

        for(PublisherRequest publisher : response.getPublishers()){
            publisherController.create(publisher);
        }

        for (BookRequest book : response.getBooks()) {
            bookController.create(book);
        }

        for (BookBorrowRequest bookBorrow: response.getBookBorrows()){
            bookBorrowController.create((bookBorrow));
        }
    }
}
