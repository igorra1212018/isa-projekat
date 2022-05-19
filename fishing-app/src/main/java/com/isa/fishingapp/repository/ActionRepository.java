package com.isa.fishingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.isa.fishingapp.model.Action;

public interface ActionRepository extends JpaRepository<Action, Integer> {
	@Query(value = "SELECT * "
			+ "FROM action AS a "
			+ "WHERE reservable_id = :reservableId "
			+ "AND NOT EXISTS ( SELECT 1 FROM reservation WHERE action_id = a.id AND cancelled = 'false' ) ", nativeQuery = true)
	List<Action> findByReservable_IdAndAvailable(Integer reservableId);
}
