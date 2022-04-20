package com.isa.fishingapp.repository;

import java.time.LocalDateTime;
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
			+ "WHERE lodging_id = :lodgingId AND NOT ((:dateFrom > to_date) OR (:dateTo < from_date))", nativeQuery = true)
    List<ReservationLodging> getReservationLodgingsInInterval(@Param("lodgingId") Integer lodgingId, @Param("dateFrom") LocalDateTime dateFrom, @Param("dateTo") LocalDateTime dateTo);
	
	@Query(value = "SELECT * "
			+ "FROM reservation_lodging "
			+ "WHERE lodging_id = :lodgingId", nativeQuery = true)
    List<ReservationLodging> getReservationLodgings(@Param("lodgingId") Integer lodgingId);
}
