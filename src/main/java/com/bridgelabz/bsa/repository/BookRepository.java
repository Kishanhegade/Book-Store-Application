package com.bridgelabz.bsa.repository;

import com.bridgelabz.bsa.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
