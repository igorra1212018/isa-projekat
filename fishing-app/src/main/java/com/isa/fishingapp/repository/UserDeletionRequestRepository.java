package com.isa.fishingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isa.fishingapp.model.UserDeletionRequest;

@Repository
public interface UserDeletionRequestRepository extends JpaRepository<UserDeletionRequest, Integer> {

}
