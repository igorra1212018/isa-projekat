package com.isa.fishingapp.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.model.Action;
import com.isa.fishingapp.model.User;
import com.isa.fishingapp.repository.ActionRepository;

@Service
public class ActionService {
	@Autowired
	private ActionRepository actionRepository;
	@Autowired
    private JavaMailSender mailSender;
	
	public List<Action> findByReservable_IdAndAvailable(Integer reservableId)
	{
		return actionRepository.findByReservable_IdAndAvailable(reservableId);
	}
	
	public void save(Action action) {
		actionRepository.save(action);
		Set<User> subscribedUsers = action.getReservable().getSubscribers();
		if(subscribedUsers != null && !subscribedUsers.isEmpty()) {
			for(User user : subscribedUsers) {
				String recipientAddress = user.getEmail();
		        String subject = "Action For " + action.getReservable().getName();
		        String reservableType = "lodging";
		        if(action.getReservable().getReservableType().equals("BOAT"))
		        	reservableType = "boat";
		        if(action.getReservable().getReservableType().equals("FISHING_LESSON"))
		        	reservableType = "fishing_lesson";
		        String reservableUrl = "http://localhost:8081/reservable/" + reservableType + "/" + action.getReservable().getId();
		        String message = "A new discount of " + action.getDiscount() + " is available from " + action.getDateRange().getFromDate() + " to " + action.getDateRange().getToDate() + " at " + action.getReservable().getName() + "\n\nView it here at:\n\n" + reservableUrl;
		        
		        SimpleMailMessage email = new SimpleMailMessage();
		        email.setTo(recipientAddress);
		        email.setSubject(subject);
		        email.setText(message);
		        mailSender.send(email);
			}
		}
	}
}
