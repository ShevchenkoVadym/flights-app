package com.home.project.vadym.flightapi.data.jdbc;

import com.home.project.vadym.flightapi.model.entity.ArrivalEntity;
import com.home.project.vadym.flightapi.model.entity.DepartureEntity;

public interface FlightRepository {

    ArrivalEntity save(ArrivalEntity arrival);
    DepartureEntity save(DepartureEntity departure);

}
