package com.home.project.vadym.flightapi.repository;

import com.home.project.vadym.flightapi.model.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<FlightEntity, Integer> {

}
