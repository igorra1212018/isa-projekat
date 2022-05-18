package com.isa.fishingapp.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.isa.fishingapp.model.Reservable;
import com.isa.fishingapp.model.ReservableAmenity;

public interface ReservableRepository<T extends Reservable> extends JpaRepository<T, Integer> {
	
    Optional<T> findById(Integer id);
	
	@Query(value = "SELECT * "
			+ "FROM (SELECT * FROM reservable WHERE reservable_type = :discriminatorParameter) AS r ", nativeQuery = true)
    List<T> findAll(@Param("discriminatorParameter") String discriminatorParameter);
	
	@Query(value = "SELECT * "
			+ "FROM (SELECT * FROM reservable WHERE reservable_type = :discriminatorParameter AND name LIKE CONCAT('%', :name, '%')) AS r "
			+ "WHERE EXISTS ( SELECT 1 FROM available_date_range WHERE reserable_id = r.id (:dateFrom BETWEEN from_date AND to_date) AND (:dateTo BETWEEN from_date AND to_date) )", nativeQuery = true)
    List<T> findBySearch(@Param("discriminatorParameter") String discriminatorParameter,
    		@Param("name") String name,
    		@Param("dateFrom") LocalDateTime dateFrom,
    		@Param("dateTo") LocalDateTime dateTo);
	
	List<T> findByReservableTypeAndNameContainingIgnoreCaseAndAddressCountryContainingIgnoreCaseAndAddressCityContainingIgnoreCase(String reservableType, String name, String country, String city);
	
	@Query(value = "SELECT new ReservableAmenity(id, amenityIcon, amenityName, price) "
			+ "FROM ReservableAmenity WHERE id = :id ")
	Optional<ReservableAmenity> findReservableAmenityById(@Param("id") Integer id);
}
