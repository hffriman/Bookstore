package com.example.bookstore.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;


//Making the Book class capable of being created as a database object
@Entity
public class Book {

	// Creating an inner database index to the clas
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	//The attributes of the Book class
	private long id;
	private String title;
	private String author;
	private long year;
	private String isbn;
	private double price;
	
	// -Creating a database relationship between Book class and Category class
	// -ManyToOne means that there can be many Book classes in one Category class
	// -By using JoinColumn and using Category classe's attribute as an foreign key,
	// 	the connection between database classes is made
	// -By using JsonManagedReference, the data of the Book and Category 
	//  database tables is possible to recognize and to be stored as backup JSON data
	
	@ManyToOne
	@JoinColumn(name = "categoryid")
	@JsonManagedReference
	private Category category;
	
	
	// A constructor without parameters
	public Book () {}
	
	// A constructor with parameters
	public Book(String title, String author, long year, String isbn, double price, Category category) {
		
		super();
		this.title = title;
		this.author = author;
		this.year = year;
		this.isbn = isbn;
		this.price = price;
		this.category = category;
		
	}

	// Get and Set commands for each attribute of the Book class

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getYear() {
		return year;
	}

	public void setYear(long year) {
		this.year = year;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
	// a toString method for Book class
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", year=" + year + ", isbn=" + isbn
				+ ", price=" + price + ", category=" + this.getCategory() + "]";
	}

}
