package com.home.project.vadym.flightapi.data.jpa;

import com.home.project.vadym.flightapi.model.Arrival;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArrivalRepository extends JpaRepository<Arrival, Long> {

    Arrival findById(long id);

}
