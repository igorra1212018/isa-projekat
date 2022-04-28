package com.isa.fishingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.fishingapp.model.FishingLesson;

public interface FishingLessonRepository extends JpaRepository<FishingLesson, Integer> {

}
