package com.BuyBook.bookservice.services;

import java.util.List;

import com.BuyBook.bookservice.entity.AddBookRequest;
import com.BuyBook.bookservice.entity.Books;

public interface BookService {

	public Books addBook(AddBookRequest request);
	public List<Books> getAllBooks();
	public String getBook();
}
