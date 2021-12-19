package com.home.project.vadym.flightapi.data.jpa;

import com.home.project.vadym.flightapi.model.entity.ArrivalEntity;
import org.springframework.data.repository.CrudRepository;

public interface ArrivalRepository extends CrudRepository<ArrivalEntity, Long> {

    ArrivalEntity findById(long id);
}
