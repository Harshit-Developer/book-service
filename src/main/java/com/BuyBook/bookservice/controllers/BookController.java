package com.BuyBook.bookservice.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BuyBook.bookservice.entity.AddBookRequest;
import com.BuyBook.bookservice.entity.Books;
import com.BuyBook.bookservice.services.BookService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

	private BookService bookService;
	@PostMapping("/books")
	public ResponseEntity<?> AddBook(@ModelAttribute AddBookRequest request) {
		Books savedBook = bookService.addBook(request);
		if(savedBook==null) {
			String message = "Some Error occured while Uploading Books";
			return ResponseEntity.badRequest().body(message);
		}
		return ResponseEntity.ok().body(savedBook);
		
	}
	
	@GetMapping("/books")
	public ResponseEntity<?> getBooks(){
		List<Books> allBooks = bookService.getAllBooks();
		return ResponseEntity.ok().body(allBooks);
	}
	
}
