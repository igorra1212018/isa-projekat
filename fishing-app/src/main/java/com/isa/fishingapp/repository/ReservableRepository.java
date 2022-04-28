package com.isa.fishingapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.isa.fishingapp.model.Reservable;

public interface ReservableRepository<T extends Reservable> extends JpaRepository<T, Integer> {
	@Query(value = "SELECT * "
			+ "FROM reservable "
			+ "WHERE name LIKE CONCAT('%', :name, '%') ", nativeQuery = true)
    List<T> findByName(@Param("name") String nameParameter);
}
