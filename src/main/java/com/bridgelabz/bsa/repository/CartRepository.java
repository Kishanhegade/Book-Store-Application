package com.bridgelabz.bsa.repository;

import com.bridgelabz.bsa.model.Book;
import com.bridgelabz.bsa.model.Cart;
import com.bridgelabz.bsa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserAndBook(User user, Book book);
}
