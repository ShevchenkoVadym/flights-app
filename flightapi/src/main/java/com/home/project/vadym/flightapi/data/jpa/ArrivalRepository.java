package com.home.project.vadym.flightapi.data.jpa;

import com.home.project.vadym.flightapi.model.Arrival;
import org.springframework.data.repository.CrudRepository;

public interface ArrivalRepository extends CrudRepository<Arrival, Long> {

    Arrival findById(long id);

}
