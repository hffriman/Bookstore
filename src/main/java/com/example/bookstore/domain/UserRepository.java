package com.example.bookstore.domain;

import org.springframework.data.repository.CrudRepository;

// Creating a public interface, which inherits the methods of CrudRepository
// to CREATE, READ, UPDATE AND DELETE User tables from the database
public interface UserRepository extends CrudRepository<User, Long> {
	// Creating an object which is used
	// to search for the saved username
	User findByUsername(String username);
}
