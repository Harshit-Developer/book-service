package com.BuyBook.bookservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BuyBook.bookservice.entity.AddBookRequest;
import com.BuyBook.bookservice.entity.Books;


public interface BookRepository extends JpaRepository<Books, Long>{

	
	Optional<Books> findByTitle(String email);

	Books save(AddBookRequest request);

}
