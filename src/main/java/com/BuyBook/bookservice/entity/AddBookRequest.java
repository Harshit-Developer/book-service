package com.BuyBook.bookservice.entity;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddBookRequest {

	
    private String isbn;
	
    private String title;
	
	
    private List<String> author;
	
	
    private String publisher;
	
    private String publicationDate;
    
    
    private String language;
    private List<String> genre;
    
    private List<String> keywords;
    private String description;
    
    
    private double price;
    
    
    private MultipartFile image;
}
