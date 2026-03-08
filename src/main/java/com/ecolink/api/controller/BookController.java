package com.ecolink.api.controller;

import com.ecolink.api.model.Book;
import com.ecolink.api.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
public ResponseEntity<List<Book>> getBooks(
        @RequestParam(required = false) String author) {
    if (author != null && !author.isBlank()) {
        return ResponseEntity.ok(bookService.findByAuthor(author));
    }
    return ResponseEntity.ok(bookService.findAll());
}
@GetMapping("/{id}")
public ResponseEntity<Book> getBookById(@PathVariable String id) {
    return ResponseEntity.ok(bookService.findById(id));
}
@PostMapping
public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
    return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(book));
}

@PutMapping("/{id}")
public ResponseEntity<Book> updateBook(
        @PathVariable String id,
        @Valid @RequestBody Book book) {
    return ResponseEntity.ok(bookService.update(id, book));
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteBook(@PathVariable String id) {
    bookService.delete(id);
    return ResponseEntity.noContent().build();
}

}