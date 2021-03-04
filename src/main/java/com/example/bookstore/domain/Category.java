package com.example.bookstore.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

// Creating Category class as an database entity
@Entity
public class Category {

	// -Creating Category class its own database id,
	// the value of which is generated on its own
	// -Creating attributes to Category class
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long categoryid;
	private String name;
	
	// -Creating a database relationship to Category class
	//		-By using JsonBackReference, the data of the Book and
	//      Category database tables is stored in JSON format as backup
	// 		-By using OneToMany, one Category object is able
	//  	 to contain many Book objects
	// -Creating a list object of Book objects
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="category")
	private List<Book> books;
	
	
	
	// Creating a Category constructor without parameters
	public Category() {}

	
	// Creating a Category constructor with parameters
	public Category(String name) {
		super();
		this.name = name;
	}

	
	// Creating necessary GET and SET commands
	// for each of the attributes of the Category class 

	public Long getCategoryid() {
		return categoryid;
	}


	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Book> getBooks() {
		return books;
	}


	public void setBooks(List<Book> books) {
		this.books = books;
	}

	
	// Creating a toString function to Category class
	@Override
	public String toString() {
		return "Category [categoryid=" + categoryid + ", name=" + name + "]";
	}
	
}
