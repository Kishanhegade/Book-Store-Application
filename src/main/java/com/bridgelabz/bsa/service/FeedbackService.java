package com.bridgelabz.bsa.service;

import com.bridgelabz.bsa.exception.BookNotFoundByIdException;
import com.bridgelabz.bsa.exception.UserNotFoundByIdException;
import com.bridgelabz.bsa.mapper.FeedbackMapper;
import com.bridgelabz.bsa.model.Book;
import com.bridgelabz.bsa.model.Feedback;
import com.bridgelabz.bsa.model.User;
import com.bridgelabz.bsa.repository.BookRepository;
import com.bridgelabz.bsa.repository.FeedbackRepository;
import com.bridgelabz.bsa.repository.UserRepository;
import com.bridgelabz.bsa.requestdto.FeedbackRequest;
import com.bridgelabz.bsa.responsedto.FeedbackResponse;
import com.bridgelabz.bsa.security.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackService {

    private FeedbackMapper feedbackMapper;
    private FeedbackRepository feedbackRepo;
    private UserRepository userRepo;
    private BookRepository bookRepo;
    private JwtUtils jwtUtils;

    public FeedbackResponse submitFeedback(String token, Integer bookId, FeedbackRequest feedbackRequest) {
        long userId = jwtUtils.extractUserIdFromToken(token);
        User user = userRepo.findById(userId)
                .orElseThrow(()->new UserNotFoundByIdException("Failed to find user"));
        Book book = bookRepo.findById(bookId)
                .orElseThrow(()->new BookNotFoundByIdException("Failed to find book"));
        Feedback feedback = feedbackMapper.mapToFeedback(feedbackRequest,new Feedback());
        feedback.setUser(user);
        feedback.setBook(book);
        feedback = feedbackRepo.save(feedback);
        return feedbackMapper.mapToFeedbackResponse(feedback);
    }


    public List<FeedbackResponse> getFeedbackForBook(Integer bookId) {
        return feedbackRepo.findAllByBookId(bookId)
                .stream()
                .map(feedback ->
                        feedbackMapper.mapToFeedbackResponse(feedback)).toList();
    }
}
