package com.example.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;
import com.example.bookstore.domain.User;
import com.example.bookstore.domain.UserRepository;

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
	public CommandLineRunner demo (BookRepository bookRepository, CategoryRepository categoryRepository, UserRepository urepository) {
		
	// Under return(args), the following commands happen:
	return (args) -> {
		
		// Before the application starts its run,
		// the already-saved Category and Book objects
		// should be deleted before being created again
		//-----> This prevents them from getting multiplied
		//-----> each time the application starts
		categoryRepository.deleteAll();
		
		// Adding text into the Spring Boot console
		log.info("Saving books");
		
		// Saving new Category objects with parameters
		categoryRepository.save(new Category("Classic Novel"));
		categoryRepository.save(new Category("Modern Novel"));
		categoryRepository.save(new Category("Classic Poetry"));
		categoryRepository.save(new Category("Modern Poetry"));
		categoryRepository.save(new Category("Nonfiction"));
		
		// Saving new Book objects with parameters 
		bookRepository.save(new Book("The Flowers of Evil", "Charles Baudelaire", 1857, "979-8593932143", 16.95, categoryRepository.findByName("Classic Poetry").get(0)));
		bookRepository.save(new Book("A Farewell to Arms", "Ernest Hemingway", 1929, "978-1124000138", 16.00, categoryRepository.findByName("Classic Novel").get(0)));
		bookRepository.save(new Book("Animal Farm", "George Orwell", 1945, "978-0451526342", 7.48, categoryRepository.findByName("Classic Novel").get(0)));
		bookRepository.save(new Book("The Melancholy of Haruhi Suzumiya", "Nagaru Tanigawa", 2003, "978-0316039017", 30.00, categoryRepository.findByName("Modern Novel").get(0)));
		bookRepository.save(new Book("Violet Evergarden: Volume 1", "Kana Akatsuki", 2014, "978-4-907064-43-3", 10.95, categoryRepository.findByName("Modern Novel").get(0)));
		bookRepository.save(new Book("Violet Evergarden: Volume 2", "Kana Akatsuki", 2016, "978-4-907064-44-0", 10.95, categoryRepository.findByName("Modern Novel").get(0)));

		
		// Creating User objects with passwords
		// to test the application
		User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
		User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
		urepository.deleteAll();
		urepository.save(user1);
		urepository.save(user2);
		
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
