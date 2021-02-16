package com.example.bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

// Creating a public interface that obtains the abilities of CrudRepository,
// and thus is able to CREATE, READ, UPDATE and DELETE the data of Category database
public interface CategoryRepository extends CrudRepository<Category, Long> {

	// Adding a new function to CategoryRepository
	// that finds all the objects created from Category class
	List<Category>findByName(String name);
}
