package com.bridgelabz.bsa.repository;

import com.bridgelabz.bsa.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Long> {

    @Query("SELECT w FROM Wishlist w WHERE w.user.id = :userId")
    List<Wishlist> findAllByUserId(@Param("userId") long userId);
}
