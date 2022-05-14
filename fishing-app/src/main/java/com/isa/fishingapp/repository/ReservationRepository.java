package com.isa.fishingapp.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.isa.fishingapp.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	
	List<Reservation> findByReservedEntity_Id(Integer entityId);
	
	List<Reservation> findByReservedEntity_IdAndCancelled(Integer entityId, boolean cancelled);
	
	@Query(value = "SELECT * "
			+ "FROM reservation "
			+ "WHERE (Now() BETWEEN from_date AND to_date) ", nativeQuery = true)
    List<Reservation> findReservationsInInterval();
	
	@Query(value = "SELECT * "
			+ "FROM reservation "
			+ "WHERE :entityId = reserved_entity_id AND NOT ((:dateFrom > to_date) OR (:dateTo < from_date)) AND !cancelled", nativeQuery = true)
    List<Reservation> findReservationEntitiesInInterval(@Param("entityId") Integer entityId, @Param("dateFrom") LocalDateTime dateFrom, @Param("dateTo") LocalDateTime dateTo);
	
	@Query(value = "SELECT * "
			+ "FROM reservation "
			+ "WHERE user_id = :userId ", nativeQuery = true)
    List<Reservation> findByUserId(@Param("userId") int userId);
}
