package com.isa.fishingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.isa.fishingapp.model.ReservationLodging;

public interface ReservationLodgingRepository extends JpaRepository<ReservationLodging, Integer> {
	@Query(value = "SELECT * "
			+ "FROM reservation_lodging "
			+ "WHERE (Now() BETWEEN from_date AND to_date) ", nativeQuery = true)
    List<ReservationLodging> getReservationLodgingsInInterval();
	
	@Query(value = "SELECT * "
			+ "FROM reservation_lodging "
			+ "WHERE lodging_id = :lodgingId", nativeQuery = true)
    List<ReservationLodging> getReservationLodgingsInInterval(@Param("lodgingId") Integer lodgingId);
}
