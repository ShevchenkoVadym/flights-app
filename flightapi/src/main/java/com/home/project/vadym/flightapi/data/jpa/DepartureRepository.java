package com.home.project.vadym.flightapi.data.jpa;

import com.home.project.vadym.flightapi.model.entity.ArrivalEntity;
import com.home.project.vadym.flightapi.model.entity.DepartureEntity;
import org.springframework.data.repository.CrudRepository;

public interface DepartureRepository extends CrudRepository<DepartureEntity, Long> {

    DepartureEntity findById(long id);

}
