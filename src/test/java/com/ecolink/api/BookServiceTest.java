package com.ecolink.api;

import com.ecolink.api.model.Book;
import com.ecolink.api.repository.BookRepository;
import com.ecolink.api.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void findById_existingId_returnsBook() {
        Book book = new Book("abc123", "Clean Code", "Robert C. Martin", 2008, "978-0132350884");
        when(bookRepository.findById("abc123")).thenReturn(Optional.of(book));

        Book result = bookService.findById("abc123");

        assertThat(result.getTitle()).isEqualTo("Clean Code");
    }

    @Test
    void findById_notFound_throwsException() {
        when(bookRepository.findById("nonexistent")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.findById("nonexistent"))
                .isInstanceOf(ResponseStatusException.class);
    }

    @Test
    void delete_existingId_callsRepository() {
        Book book = new Book("abc123", "Clean Code", "Robert C. Martin", 2008, null);
        when(bookRepository.findById("abc123")).thenReturn(Optional.of(book));

        bookService.delete("abc123");

        verify(bookRepository, times(1)).deleteById("abc123");
    }
}