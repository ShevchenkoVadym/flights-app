package com.home.project.vadym.flightapi.service;

import com.home.project.vadym.flightapi.model.entity.FlightEntity;
import com.home.project.vadym.flightapi.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public void addFlight(FlightEntity flight) {
        flightRepository.save(flight);
    }

    @Override
    public List<FlightEntity> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public FlightEntity getFlightById(int id) {
        return flightRepository.getById(id);
    }

    @Override
    public void updateFlightTerminal(int id) {

    }

    @Override
    public void deleteFlightById(int id) {

    }
}
