package com.BuyBook.bookservice.entity;

import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddBookRequest {

	
    private String isbn;
	
    private String title;
	
	
    private String author;
	
	
    private String publisher;
	
    private String publicationDate;
    
    
    private String language;
    private String genre;
    
    private String keywords;
    private String description;
    
    
    private double price;
    
    
    private MultipartFile image;
}
