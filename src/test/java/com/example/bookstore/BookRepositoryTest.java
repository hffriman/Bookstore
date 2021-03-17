package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookrepository;
	
	
	// TEST 1:
	// CREATING A LIST THAT ONLY CONSISTS 
	// OF BOOKS WITH CERTAIN TITLE
	// AND THEN CHECKING IF ITS THESE TITLES 
	// REALLY HAVE THE CORRECT AUTHOR
	
	@Test
	public void findByTitleShouldReturnBook() {
		List<Book> books = bookrepository.findByTitle("The Melancholy of Haruhi Suzumiya");
	
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Nagaru Tanigawa");
	}
	
	// TEST 2:
	// CREATING A NEW BOOK OBJECT, SAVING IT TO DATABASE
	// AND THEN MAKING A TEST CONDITION TO BE PASSED
	// ONLY IF THAT OBJECT IS REALLY FOUND FROM THE DATABASE
	@Test
	public void createNewBook() {
		Book book = new Book("Mobile Suit Gundam: Hathaway's Flash", "Yoshiyuki Tomino", 1989, "978-123450079", 10.95, new Category("Scifi novel"));
		bookrepository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	
	// TEST 3:
	// CREATING A NEW BOOK OBJECT, 
	// CHECKING ITS INDEX NUMBER,
	// DELETING A DATABASE OBJECT WITH THAT SAME INDEX NUMBER,
	// CREATING A BOOKLIST WITH THE TITLE OF THE REMOVED OBJECT
	// AND THEN CHECKING IF THIS NEW HAS NO OBJECTS
	// ---------------------NOTIFICATION ------------------------
	//--------------THIS TEST SEEMS TO WORK CORRECTLY.----------
	//----------HOWEVER, IT WAS NOT DONE THE MOST EASY WAY---------
	//---------DUE TO MULTIPLE ERRORS FACED WITH OTHER OPTIONS------
	@Test
	public void removeBook() {
		
		Book book = new Book("Final Test Book", "Anonymous", 1989, "123456789", 10.95, new Category("Education"));
		bookrepository.save(book);
		assertThat(book.getId()).isEqualTo(12);
		bookrepository.deleteById((long)12);
		
		List <Book> books = bookrepository.findByTitle("Final Test Book");
		assertThat(books).hasSize(0);
	}
	
}
