package com.bridgelabz.bsa.repository;

import com.bridgelabz.bsa.model.Book;
import com.bridgelabz.bsa.model.Cart;
import com.bridgelabz.bsa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserAndBook(User user, Book book);
    @Query("SELECT c FROM Cart c WHERE c.user.id = :userId")
    List<Cart> findAllByUserId(@Param("userId") Long userId);
}
