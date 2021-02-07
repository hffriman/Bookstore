package com.example.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

@Controller
public class bookController {
	
	// Global variable:
	// Creating a bookRepository object that refers to BookRepository class
	// and is used to manipulate data of the applica
	@Autowired
	private BookRepository bookRepository;
	
	
	// Creating a request mapping of html page "booklist" 
	// that automatically shows the Book objects stored in the list
	@GetMapping("/booklist")
	public String booklist(Model model) {
		
		// The outcome of bookRepository object's function findAll,
		// which finds all the Book objects of the application,
		// is stored as an attribute of the Model object
		model.addAttribute("books", bookRepository.findAll());
		
		// The content of page "booklist" is shown
		return "booklist";
	}
	
	
	// Creating a request mapping of html page "add"
	// that is used to add new Book objects into the application
	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		
		// A new empty Book object is stored as an attribute
		// of the Model object
		model.addAttribute("book", new Book());
		
		// The content of page "addbook" is shown
		return "addbook";
		
	}

	
	// Creating a request mapping of html page "save"
	// that is used to save the object added in the "add" page
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(Book book) {
		
		// Using the save functionality of bookRepository object,
		// a Book object's content is added into the application
		bookRepository.save(book);
		
		// After saving, the "booklist" page is shown
		return "redirect:booklist";
		
	}
	
	
	// Creating a request mapping of page "edit"
	// which uses the Book object's id as a parameter
	// to get an access of editing its content
	@RequestMapping(value = "/edit/{id}")
	public String editBook(@PathVariable("id") Long id, Model model) {
		
		// The outcome of bookRepository object's function findById,
		// which uses the given id variable to find the right Book object,
		// is stored as an attribute of the Model object
		model.addAttribute("book", bookRepository.findById(id));
		
		//The page "editbook" is shown
		return "editbook";
		
	}
	
	// Creating a request mapping of page "delete"
	// which uses the Book object's id as a parameter
	// to get an access of deleting it entirely
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		
		// The bookRepository object's function is used
		// to remove the Book object with its id matching
		// the given id parameter
		bookRepository.deleteById(id);
		
		// The change is stored as an attribute to a Model object
		model.addAttribute("book", model);
		
		// The "booklist" page is shown
		return "redirect:../booklist";
	}
	
}
