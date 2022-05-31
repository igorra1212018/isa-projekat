package com.isa.fishingapp.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.isa.fishingapp.model.Reservable;
import com.isa.fishingapp.model.ReservableAmenity;
import com.isa.fishingapp.model.User;

public interface ReservableRepository<T extends Reservable> extends JpaRepository<T, Integer> {
	
    Optional<T> findById(Integer id);
	
	@Query(value = "SELECT * "
			+ "FROM (SELECT * FROM reservable WHERE reservable_type = :discriminatorParameter) AS r ", nativeQuery = true)
    List<T> findAll(@Param("discriminatorParameter") String discriminatorParameter);
	
	@Query(value = "SELECT * "
			+ "FROM (SELECT * FROM reservable WHERE reservable_type = :discriminatorParameter) AS r ", nativeQuery = true)
    Page<T> findAll(@Param("discriminatorParameter") String discriminatorParameter, Pageable pageable);
	
	@Query(value = "SELECT * "
			+ "FROM reservable AS r "
			+ "WHERE reservable_type = :discriminatorParameter "
			+ "AND LOWER(name) LIKE LOWER(CONCAT('%', :name, '%')) "
			+ "AND LOWER(city) LIKE LOWER(CONCAT('%', :city, '%')) "
			+ "AND country_id = :countryId OR :countryId = 0 "
			+ "AND EXISTS ( SELECT 1 FROM available_date_range WHERE reservable_id = r.id AND (:dateFrom BETWEEN from_date AND to_date) AND (:dateTo BETWEEN from_date AND to_date) ) "
			+ "AND NOT EXISTS ( SELECT 1 FROM reservation WHERE reserved_entity_id = r.id AND (:dateFrom BETWEEN from_date AND to_date) OR (:dateTo BETWEEN from_date AND to_date) )", nativeQuery = true)
    Page<T> findBySearch(@Param("discriminatorParameter") String discriminatorParameter,
    		@Param("name") String name,
    		@Param("city") String city,
    		@Param("countryId") Integer countryId,
    		@Param("dateFrom") LocalDateTime dateFrom,
    		@Param("dateTo") LocalDateTime dateTo, 
    		Pageable pageable);
	
	Page<T> findByReservableTypeAndNameContainingIgnoreCaseAndAddressCityContainingIgnoreCase(String reservableType, String name, String city, Pageable pageable);
	Page<T> findByReservableTypeAndNameContainingIgnoreCaseAndAddressCountryIdAndAddressCityContainingIgnoreCase(String reservableType, String name, Integer countryId, String city, Pageable pageable);
	
	@Query(value = "SELECT new ReservableAmenity(id, amenityIcon, amenityName, price) "
			+ "FROM ReservableAmenity WHERE id = :id ")
	Optional<ReservableAmenity> findReservableAmenityById(@Param("id") Integer id);
	
	@Query(value = "SELECT user_id "
			+ "FROM user_subscriptions WHERE user_id = :user_id AND reservable_id = :reservable_id ", nativeQuery = true)
	Optional<Integer> isUserSubscribed(@Param("user_id") Integer user_id, @Param("reservable_id") Integer reservable_id);
}
