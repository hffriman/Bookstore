package com.example.bookstore.domain;


import org.springframework.data.repository.CrudRepository;


// Creating a public interface that is connected into the CrudRepository
// and thus gets a capability to CREATE, READ, UPDATE and DELETE
// the data of the application
public interface BookRepository extends CrudRepository<Book, Long> {

	
}