package com.isa.fishingapp.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.isa.fishingapp.model.Reservation;
import com.isa.fishingapp.model.Lodging;
import com.isa.fishingapp.model.ReservationLodging;

public interface ReservationLodgingRepository extends JpaRepository<ReservationLodging, Integer> {
	
}
