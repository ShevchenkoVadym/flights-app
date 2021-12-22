package com.home.project.vadym.flightapi.data.jpa;

import com.home.project.vadym.flightapi.model.Departure;
import org.springframework.data.repository.CrudRepository;

public interface DepartureRepository extends CrudRepository<Departure, Long> {

    Departure findById(long id);

}
