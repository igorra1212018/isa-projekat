package com.isa.fishingapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.isa.fishingapp.model.Reservable;

public interface ReservableRepository<T extends Reservable> extends JpaRepository<T, Integer> {
	@Query(value = "SELECT * "
			+ "FROM (SELECT * FROM reservable WHERE id = :id AND reservable_type = :discriminatorParameter) AS r "
			+ "LEFT JOIN lodging l ON l.id = r.id AND r.reservable_type = :discriminatorParameter "
			+ "LEFT JOIN boat b ON b.id = r.id AND r.reservable_type = :discriminatorParameter "
			+ "LEFT JOIN fishing_lesson f ON f.id = r.id AND r.reservable_type = :discriminatorParameter ", nativeQuery = true)
    Optional<T> findById(@Param("id") Integer id, @Param("discriminatorParameter") String discriminatorParameter);
	
	@Query(value = "SELECT * "
			+ "FROM (SELECT * FROM reservable WHERE name LIKE CONCAT('%', :name, '%') AND reservable_type = :discriminatorParameter) AS r "
			+ "LEFT JOIN lodging l ON l.id = r.id AND r.reservable_type = :discriminatorParameter "
			+ "LEFT JOIN boat b ON b.id = r.id AND r.reservable_type = :discriminatorParameter "
			+ "LEFT JOIN fishing_lesson f ON f.id = r.id AND r.reservable_type = :discriminatorParameter ", nativeQuery = true)
    List<T> findByName(@Param("name") String nameParameter, @Param("discriminatorParameter") String discriminatorParameter);
	
	@Query(value = "SELECT * "
			+ "FROM (SELECT * FROM reservable WHERE reservable_type = :discriminatorParameter) AS r "
			+ "LEFT JOIN lodging l ON l.id = r.id AND r.reservable_type = :discriminatorParameter "
			+ "LEFT JOIN boat b ON b.id = r.id AND r.reservable_type = :discriminatorParameter "
			+ "LEFT JOIN fishing_lesson f ON f.id = r.id AND r.reservable_type = :discriminatorParameter ", nativeQuery = true)
    List<T> findAll(@Param("discriminatorParameter") String discriminatorParameter);
}
