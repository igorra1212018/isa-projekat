package com.isa.fishingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.isa.fishingapp.model.Lodging;

public interface LodgingRepository extends JpaRepository<Lodging, Integer> {
	@Query(value = "SELECT * "
			+ "FROM lodging "
			+ "WHERE name LIKE CONCAT('%', :name, '%') ", nativeQuery = true)
    List<Lodging> getLodgingsByName(@Param("name") String nameParameter);
}
