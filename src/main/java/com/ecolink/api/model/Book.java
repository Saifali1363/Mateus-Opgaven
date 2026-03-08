package com.ecolink.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Document(collection = "books") // Gem dette i MongoDB i en samling der hedder books

public class Book {
    
    @Id // Dette felt er det unikke ID som MongoDB genererer automatisk
    private String id;

    @NotBlank // Dette felt må ikke være tomt
    @Size(max = 200) // Titlen må max være 200 tegn lang
    @Field("title") // I databasen skal dette felt hedde title
    private String title;

    @NotBlank
    @Field("author")
    private String author;

    @Field("publishedYear")
    private Integer publishedYear;

    @Indexed(unique = true) // Ingen to bøger må have samme ISBN
    @Field("isbn")
    private String isbn;
    
    public String getId() { return id; }
public void setId(String id) { this.id = id; }

public String getTitle() { return title; }
public void setTitle(String title) { this.title = title; }

public String getAuthor() { return author; }
public void setAuthor(String author) { this.author = author; }

public Integer getPublishedYear() { return publishedYear; }
public void setPublishedYear(Integer publishedYear) { this.publishedYear = publishedYear; }

public String getIsbn() { return isbn; }
public void setIsbn(String isbn) { this.isbn = isbn; }

// getters og setters: felterne er private så ingen udefra kan læse eller ændre dem direkte
// Getters og setters er de "døre" man skal igennem 

// getTitle()- Hvad er titlen? 
// setTitle("Clean Code")- Sæt titlen til Clean Code



}
