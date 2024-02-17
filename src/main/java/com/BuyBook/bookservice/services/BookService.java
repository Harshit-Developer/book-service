package com.BuyBook.bookservice.services;

import com.BuyBook.bookservice.entity.AddBookRequest;
import com.BuyBook.bookservice.entity.Books;

public interface BookService {

	public Books addBook(AddBookRequest request);
	public String getAllBooks();
	public String getBook();
}
