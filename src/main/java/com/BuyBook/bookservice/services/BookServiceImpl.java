package com.BuyBook.bookservice.services;

import java.io.IOException;
import java.text.ParseException;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.BuyBook.bookservice.Utils.AmazonS3Config;
import com.BuyBook.bookservice.Utils.BookUtility;
import com.BuyBook.bookservice.entity.AddBookRequest;
import com.BuyBook.bookservice.entity.Books;
import com.BuyBook.bookservice.repository.BookRepository;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.CannedAccessControlList;

import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService{

	private BookRepository bookRepository;
	
	 @Autowired
	  private AmazonS3Config amazonS3Config;
	 
	 @Autowired
	 private BookUtility bookUtility;
	 
	 
	 @Value("${aws.s3.bucketName}")
	 private String bucketName;
	 
	
	@Override
	public Books addBook(AddBookRequest request) {
		
		if (request.getImage().getSize() > 6048576) { // 1MB limit
            throw new IllegalArgumentException("Image size exceeds limit");
        }
		
		
		String fileName = Instant.now().toString()+ request.getImage().getOriginalFilename();
		
		        
		try {
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName,request.getImage().getInputStream(),null).withCannedAcl(CannedAccessControlList.PublicRead);
			amazonS3Config.amazonS3Client().putObject(putObjectRequest);
			
		} catch (AmazonServiceException e) {
			String message = "Error occured while uploading Image";
			return null;
		} catch (SdkClientException e) {
			String message = "Error occured while uploading Image";
			return null;
		} catch (IOException e) {
			String message = "Error occured while uploading Image";
			return null;
		}
		
		
        String imageUrl = String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName,amazonS3Config.getRegion() , fileName);
        
      
		// Convert AddBookRequest to Books object to save to DB.
        Books newBook;
		try {
			newBook = bookUtility.convertToBooks(request, imageUrl);
			Books savedBook =  bookRepository.save(newBook);
			return savedBook;
			
		} catch (Exception e) {
			String message = "Some error occured";
			return null;
		}
		
		
	}

	@Override
	public List<Books> getAllBooks() {
		List<Books> allBooks = bookRepository.findAll();
		return allBooks;
	}

	@Override
	public String getBook() {
		// TODO Auto-generated method stub
		return null;
	}
}


