package com.example.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	
	// Adding an Logger object which is used to add text into the Spring Boot console
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);	
	}
	
	
	// Adding a function that connects to the BookRepository interface object
	// and makes creation of test commands possible
	@Bean
	public CommandLineRunner demo (BookRepository bookRepository) {
		
	// Under return(args), the following commands happen:
	return (args) -> {
		
		// Adding text into the Spring Boot console
		log.info("Saving books");
		
		// Creating Book objects with parameters
		Book b1 = new Book("The Flowers of Evil", "Charles Baudelaire", 1857, "979-8593932143", 16.95);
		Book b2 = new Book("A Farewell to Arms", "Ernest Hemingway", 1929, "978-1124000138", 16.00);
		Book b3= new Book("Animal Farm", "George Orwell", 1945, "978-0451526342", 7.48);
		Book b4 = new Book("The Melancholy of Haruhi Suzumiya", "Nagaru Tanigawa", 2003, "978-0316039017", 30.00);
		Book b5 = new Book("Violet Evergarden: Volume 1", "Kana Akatsuki", 2014, "978-4-907064-43-3", 10.95);
		
		// Using bookRepository object to save all the created Book objects
		bookRepository.save(b1);
		bookRepository.save(b2);
		bookRepository.save(b3);
		bookRepository.save(b4);
		bookRepository.save(b5);
		
		// Adding text into the Spring Boot console
		log.info("Fetch all books");
		
		// Creating a for loop that:
		//1. finds the content of every saved Book object 
			//by using bookRepository's findAll function
		//2. writes the value of Book object's toString function
			// into the Spring Boot console by using Logger object's info method
		for (Book book : bookRepository.findAll()) {
		log.info(book.toString());
		
	}
	};
	

}
}
