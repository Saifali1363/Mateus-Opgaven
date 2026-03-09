package com.ecolink.api;

import com.ecolink.api.model.Book;
import com.ecolink.api.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    void postBook_validPayload_returns201WithId() throws Exception {
        Book newBook = new Book(null, "Clean Code", "Robert C. Martin", 2008, "978-0132350884");

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newBook)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    void getBookById_existingBook_returns200WithCorrectFields() throws Exception {
        Book saved = bookRepository.save(new Book(null, "The Pragmatic Programmer", "Andy Hunt", 1999, "978-0135957059"));

        mockMvc.perform(get("/api/books/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("The Pragmatic Programmer")));
    }

    @Test
    void getBookById_nonExistingId_returns404() throws Exception {
        mockMvc.perform(get("/api/books/000000000000000000000099"))
                .andExpect(status().isNotFound());
    }
}