package com.home.project.vadym.flightapi.data.jpa;

import com.home.project.vadym.flightapi.model.Departure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartureRepository extends JpaRepository<Departure, Long> {

    Departure findById(long id);

}
