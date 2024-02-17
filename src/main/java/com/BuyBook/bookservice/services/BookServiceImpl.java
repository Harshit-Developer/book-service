package com.BuyBook.bookservice.services;

import java.io.IOException;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.BuyBook.bookservice.configs.AmazonS3Config;
import com.BuyBook.bookservice.entity.AddBookRequest;
import com.BuyBook.bookservice.entity.Books;
import com.BuyBook.bookservice.repository.BookRepository;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService{

	private BookRepository bookRepository;
	
	 @Autowired
	  private AmazonS3Config amazonS3Config;
	 
	 
	 
	 @Value("${aws.s3.bucketName}")
	 private String bucketName;
	 
	
	@Override
	public Books addBook(AddBookRequest request) {
		// TODO Auto-generated method stub
		// Convert the Incoming Request to Book Type
		
		
		if (request.getImage().getSize() > 6048576) { // 1MB limit
            throw new IllegalArgumentException("Image size exceeds limit");
        }
		
		String fileName = Instant.now().toString()+ request.getImage().getName();
		try {
			amazonS3Config.amazonS3Client().putObject(bucketName,fileName,request.getImage().getContentType()t)
		} catch (AmazonServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SdkClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
        
        String imageUrl = String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName,amazonS3Config.getRegion() , fileName);
        
        Books newBook = new Books(
				Long.parseLong("200000"),
				request.getIsbn(),
				request.getTitle(),
				request.getAuthor(),
				request.getPublisher(),
				request.getPublicationDate(),
				request.getPublicationDate(),
				request.getLanguage(),
				request.getGenre(),
				request.getKeywords(),
				request.getPrice(),
				imageUrl
				);
		
		Books savedBook =  bookRepository.save(newBook);
		System.out.println(request.toString());
		return savedBook;
	}

	@Override
	public String getAllBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBook() {
		// TODO Auto-generated method stub
		return null;
	}
}


