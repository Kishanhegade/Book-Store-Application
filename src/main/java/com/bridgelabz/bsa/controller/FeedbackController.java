package com.bridgelabz.bsa.controller;

import com.bridgelabz.bsa.requestdto.FeedbackRequest;
import com.bridgelabz.bsa.responsedto.FeedbackResponse;
import com.bridgelabz.bsa.service.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping("/feedback")
    public ResponseEntity<FeedbackResponse> submitFeedback(@RequestHeader("Authorization") String token,
                                                           @RequestParam Integer bookId,
                                                           @RequestBody FeedbackRequest feedbackRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackService.submitFeedback(token, bookId, feedbackRequest));
    }

    @GetMapping("/feedback/{bookId}")
    public ResponseEntity<List<FeedbackResponse>> getFeedbackForBook(@PathVariable Integer bookId) {
        List<FeedbackResponse> feedback = feedbackService.getFeedbackForBook(bookId);
        return ResponseEntity.status(HttpStatus.OK).body(feedback);
    }

}
