package com.ecolink.api.service;

import com.ecolink.api.model.Book;
import com.ecolink.api.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
    return bookRepository.findAll();
}

public Book findById(String id) {
    return bookRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Book with id " + id + " not found"
            ));
}

public List<Book> findByAuthor(String author) {
    return bookRepository.findByAuthorIgnoreCase(author);
}
public Book create(Book book) {
    return bookRepository.save(book);
}
public Book update(String id, Book updatedBook) {
    Book existing = findById(id);
    existing.setTitle(updatedBook.getTitle());
    existing.setAuthor(updatedBook.getAuthor());
    existing.setPublishedYear(updatedBook.getPublishedYear());
    existing.setIsbn(updatedBook.getIsbn());
    return bookRepository.save(existing);
}
public void delete(String id) {
    findById(id);
    bookRepository.deleteById(id);
}

}