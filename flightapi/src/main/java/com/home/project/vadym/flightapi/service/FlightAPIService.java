package com.home.project.vadym.flightapi.service;

import com.home.project.vadym.flightapi.exceptions.NoFlightsFoundException;
import com.home.project.vadym.flightapi.logic.FlightsCache;
import com.home.project.vadym.flightapi.model.Flight;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FlightAPIService {

    @Autowired
    private FlightsCache flightsCache;

    public List<Flight> getNextFlights() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        List<Flight> flights = flightsCache.getCachedFlightsList();
        List<Flight> filteredFlights = flights.stream()
                .filter(flight -> flight.getShowTime().after(currentTimestamp)).sorted()
                .collect(Collectors.toList());
        log.info(String.format("Return %d flights which will be in future", filteredFlights.size()));
        return filteredFlights;
    }

    public Flight getAnyFlightSoon() throws NoFlightsFoundException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis()); // current
        long deltaTime = TimeUnit.MINUTES.toMillis(15);
        timestamp.setTime(timestamp.getTime() - deltaTime); // timestamp from the past using delta

        List<Flight> flights = flightsCache.getCachedFlightsList();
        return flights.stream()
                .filter(flight -> flight.getShowTime().after(timestamp)).sorted().findFirst()
                .orElseThrow(() -> new NoFlightsFoundException("There are no any flights soon."));
    }

    public List<Flight> getPastFlights() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        List<Flight> flights = flightsCache.getCachedFlightsList();
        List<Flight> filteredFlights = flights.stream()
                .filter(flight -> flight.getShowTime().before(currentTimestamp))
                .sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        log.info(String.format("Return %d flights which were in the past", filteredFlights.size()));
        return filteredFlights;
    }
}
