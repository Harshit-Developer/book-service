package com.BuyBook.bookservice.controllers;

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
	public String AddBook(@ModelAttribute AddBookRequest request) {
		Books savedBook = bookService.addBook(request);
		return request.toString();
	}
}
