package com.isa.fishingapp.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.isa.fishingapp.model.Reservation;

public interface ReservationRepository<T extends Reservation> extends JpaRepository<T, Integer> {
	@Query(value = "SELECT * "
			+ "FROM reservation "
			+ "WHERE (Now() BETWEEN from_date AND to_date) ", nativeQuery = true)
    List<T> getReservationLodgingsInInterval();
	
	@Query(value = "SELECT * "
			+ "FROM reservation "
			+ "WHERE :entityId = reserved_entity_id AND NOT ((:dateFrom > to_date) OR (:dateTo < from_date))", nativeQuery = true)
    List<T> findReservationEntitiesInInterval(@Param("entityId") Integer entityId, @Param("dateFrom") LocalDateTime dateFrom, @Param("dateTo") LocalDateTime dateTo);
	
	@Query(value = "SELECT * "
			+ "FROM reservation "
			+ "WHERE reserved_entity_id = :entityId", nativeQuery = true)
    List<T> findByEntityId(@Param("entityId") Integer entityId);
}
