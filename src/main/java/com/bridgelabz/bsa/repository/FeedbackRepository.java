package com.bridgelabz.bsa.repository;

import com.bridgelabz.bsa.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Long> {

    @Query("SELECT f FROM Feedback f WHERE f.book.id=:bookId")
    List<Feedback> findAllByBookId(@Param("bookId")Integer bookId);
}
