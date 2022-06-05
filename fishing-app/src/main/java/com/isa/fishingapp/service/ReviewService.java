package com.isa.fishingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.model.Review;
import com.isa.fishingapp.model.User;
import com.isa.fishingapp.repository.ReviewRepository;

@Service
public class ReviewService {
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
    private JavaMailSender mailSender;

	public List<Review> findByUser_IdAndReservable_Id(Integer userId, Integer reservableId) {
		return reviewRepository.findByUser_IdAndReservable_Id(userId, reservableId);
	}
	
	public void save(Review review) {
		reviewRepository.save(review);
	}

	public ResponseEntity<String> approveRequest(int id) {
		Review r = reviewRepository.findById(id).orElse(null);
		if (r != null){
			r.setApproved(true);
			reviewRepository.save(r);
			sendReviewApprovedMail(r.getReservable().getOwner(), r);
		}
		
		return new ResponseEntity<>(
			      "Request approved!", 
			      HttpStatus.OK);
	}

	public ResponseEntity<String> deleteReview(int id) {
		Review r = reviewRepository.findById(id).orElse(null);
		if (r != null)
			reviewRepository.deleteById(r.getId());
		else
			return new ResponseEntity<>(
				      "Review not found!", 
				      HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(
			      "Review rejected!", 
			      HttpStatus.OK);
	}

	public ResponseEntity<List<Review>> findAll() {
		
		return new ResponseEntity<>(
			      reviewRepository.findAll(), 
			      HttpStatus.OK);
	}
	
	public ResponseEntity<List<Review>> findByUser_Id(Integer userId) {
		return new ResponseEntity<>(
			      reviewRepository.findByUser_Id(userId), 
			      HttpStatus.OK);
	}
	
	public void sendReviewApprovedMail(User user, Review review) {
		String recipientAddress = user.getEmail();
        String subject = "New Review From " + review.getUser().getFirstName() + " " + review.getUser().getLastName();
        String message = review.getReservable().getName() + " has received a new " + review.getRating() + "-star review from " + review.getUser().getFirstName() + " " + review.getUser().getLastName() + ":\n\n\"" + review.getDescription() + "\"";
        
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
	}
}
