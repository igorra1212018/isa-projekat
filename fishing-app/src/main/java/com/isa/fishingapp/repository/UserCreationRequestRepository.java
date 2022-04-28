package com.isa.fishingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.fishingapp.model.UserCreationRequest;

@Repository
public interface UserCreationRequestRepository extends JpaRepository<UserCreationRequest, Integer> {

}
