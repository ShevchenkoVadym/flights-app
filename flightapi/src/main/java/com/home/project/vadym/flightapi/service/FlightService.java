package com.home.project.vadym.flightapi.service;

import com.home.project.vadym.flightapi.model.entity.FlightEntity;

import java.util.List;

public interface FlightService {

    void addFlight(FlightEntity flightEntity);
    List<FlightEntity> getAllFlights();
    FlightEntity getFlightById(int id);
    void updateFlightTerminal(int id);
    void deleteFlightById(int id);


}
