package com.bridgelabz.bsa.mapper;

import com.bridgelabz.bsa.model.Feedback;
import com.bridgelabz.bsa.requestdto.FeedbackRequest;
import com.bridgelabz.bsa.responsedto.FeedbackResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class FeedbackMapper {

    public Feedback mapToFeedback(FeedbackRequest feedbackRequest,Feedback feedback) {
        feedback.setRating(feedbackRequest.getRating());
        feedback.setComment(feedbackRequest.getComment());
        feedback.setSubmittedDate(LocalDate.now());
        return feedback;
    }

    public FeedbackResponse mapToFeedbackResponse(Feedback feedback) {
        FeedbackResponse feedbackResponse = new FeedbackResponse();
        feedbackResponse.setFeedbackId(feedback.getFeedbackId());
        feedbackResponse.setBookId(feedback.getBook().getBookId());
        feedbackResponse.setUserId(feedback.getUser().getUserId());
        feedbackResponse.setComment(feedback.getComment());
        feedbackResponse.setRating(feedback.getRating());
        return feedbackResponse;
    }
}
