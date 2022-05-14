package com.isa.fishingapp.repository;

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
			+ "FROM (SELECT * FROM reservable WHERE name LIKE CONCAT('%', :name, '%') AND reservable_type = :discriminatorParameter) AS r ", nativeQuery = true)
    List<T> findByName(@Param("name") String nameParameter, @Param("discriminatorParameter") String discriminatorParameter);
	
	@Query(value = "SELECT new ReservableAmenity(id, amenityIcon, amenityName, price) "
			+ "FROM ReservableAmenity WHERE id = :id ")
	Optional<ReservableAmenity> findReservableAmenityById(@Param("id") Integer id);
}
