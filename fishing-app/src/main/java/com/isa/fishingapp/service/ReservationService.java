package com.isa.fishingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.isa.fishingapp.repository.ReservationRepository;
import com.isa.fishingapp.model.AvailableDateRange;
import com.isa.fishingapp.model.DateRange;
import com.isa.fishingapp.model.Reservation;
import com.isa.fishingapp.model.User;

@Service
public class ReservationService {
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
    private JavaMailSender mailSender;
	
	public ReservationService() {
	}
	
	public Reservation findById(int id)
	{
		return reservationRepository.findById(id).orElse(null);
	}
	
	public List<Reservation> findByEntityId(int entityId)
	{
		return reservationRepository.findByReservedEntity_Id(entityId);
	}
	
	public List<Reservation> findByEntityIdAndCancelled(int entityId, boolean cancelled) {
		return reservationRepository.findByReservedEntity_IdAndCancelled(entityId, cancelled);
	}
	
	public List<Reservation> findAllReservationsInInterval(Integer entityId, DateRange dateRange)
	{
		return reservationRepository.findReservationEntitiesInInterval(entityId, dateRange.getFromDate(), dateRange.getToDate());
	}
	
	public List<Reservation> findByUserId(Integer userId)
	{
		return reservationRepository.findByUserId(userId);
	}
	
	public Reservation cancelReservation(Integer reservationId)
	{
		Reservation reservationToCancel = reservationRepository.findById(reservationId).orElse(null);
		if(reservationToCancel != null)
		{
			reservationToCancel.setCancelled(true);
			reservationRepository.save(reservationToCancel);
		}
		return reservationToCancel;
	}
	
	public void sendSuccessfulReservationMail(User user, Reservation reservation) {
		String recipientAddress = user.getEmail();
        String subject = "Reservation Made At " + reservation.getReservedEntity().getName();
        String reservableType = "lodging";
        if(reservation.getReservedEntity().getReservableType().equals("BOAT"))
        	reservableType = "boat";
        if(reservation.getReservedEntity().getReservableType().equals("FISHING_LESSON"))
        	reservableType = "fishing_lesson";
        String reservableUrl = "http://localhost:8081/reservable/" + reservableType + "/" + reservation.getReservedEntity().getId();
        String message = "You have made a reservation at " + reservation.getReservedEntity().getName() + " from " + reservation.getDateRange().getFromDate() + " to " + reservation.getDateRange().getToDate() + "\n\nView your reserved entity at:\n\n" + reservableUrl;
        
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
	}

	public void save(Reservation reservation) {
		for(AvailableDateRange r : reservation.getReservedEntity().getAvailableDateRanges())
			if(r.getRange().hasWithinDateRange(reservation.getDateRange()))
				reservationRepository.save(reservation);
	}
}
