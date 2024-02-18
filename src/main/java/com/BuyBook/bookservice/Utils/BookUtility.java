package com.BuyBook.bookservice.Utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.BuyBook.bookservice.entity.AddBookRequest;
import com.BuyBook.bookservice.entity.Books;

import lombok.NoArgsConstructor;

@Configuration
@NoArgsConstructor
public class BookUtility {

	private String convertListToString(List<String> list) {	
		String resultString  = String.join(",", list);
		return resultString;
	}
	
	private Date stringToDate(String date) throws ParseException {		
		String format = "dd-MM-yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		java.util.Date javaDate =  dateFormat.parse(date);
		return new Date(javaDate.getTime());
	}
	
	public Books convertToBooks(AddBookRequest request, String ImageUrl) throws ParseException {
		
		// Convert List<String> for Authors to String
		String authors = convertListToString(request.getAuthor());
		
		// Convert List<String> for Authors to String
		String genres = convertListToString(request.getGenre());
				
		// Convert List<String> for Authors to String
		String keywords = convertListToString(request.getKeywords());		
		
		// Convert to Date
		Date publicationDate = stringToDate(request.getPublicationDate());
		
		Books newbook = new Books(
				Long.parseLong("20000"),
				request.getIsbn(),
				request.getTitle(),
				authors,
				request.getPublisher(),
				(java.sql.Date) publicationDate,
				request.getLanguage(),
				genres,
				keywords,
				request.getDescription(),
				request.getPrice(),
				ImageUrl
				);
		return newbook;
	}
	
}
