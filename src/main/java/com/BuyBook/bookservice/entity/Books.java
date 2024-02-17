package com.BuyBook.bookservice.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String isbn;
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String publisher;
    private String publicationDate;
    
    @Column(nullable = false)
    private String language;
    private String genre;
    private String keywords;
    private String description;
    @Column(nullable = false)
    private double price;
    private String headerImageUrl;	
}