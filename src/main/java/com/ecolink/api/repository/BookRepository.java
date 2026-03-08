package com.ecolink.api.repository;

import com.ecolink.api.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findByAuthorIgnoreCase(String author);

} // BookRepository er forbindelsen til databasen. Her inde kan man 
// hente alle bøger 
// Finde én bestemt bog 
// Gemme en ny bog 
// Slette en bog