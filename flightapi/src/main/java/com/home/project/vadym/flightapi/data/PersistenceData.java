package com.home.project.vadym.flightapi.data;

import com.home.project.vadym.flightapi.data.jpa.DepartureRepository;
import com.home.project.vadym.flightapi.data.jpa.FlightsService;
import com.home.project.vadym.flightapi.model.Arrival;
import com.home.project.vadym.flightapi.model.Departure;
import com.home.project.vadym.flightapi.model.Flight;
import com.home.project.vadym.flightapi.service.FlightAPIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@ConfigurationProperties(prefix="persistence.data.property")
public class PersistenceData {

    @Autowired
    private FlightAPIService flightAPIService;

    @Autowired
    private FlightsService flightsService;

    @Autowired
    private DepartureRepository departureRepository;

    private int timePeriod = 60; // minutes

    @PostConstruct
    private void init() {
        this.start();
    }

    public void start() {
        ScheduledExecutorService scheduler
                = Executors.newSingleThreadScheduledExecutor();

        log.info("Trying to schedule the thread.");
        scheduler.scheduleAtFixedRate(() -> {
            List<Flight> pastFlights = flightAPIService.getPastFlights();

            log.info("Retrieval new cached flights to be persisted in the database.");
            List<Departure> departureRows = pastFlights.stream()
                    .filter(f -> f instanceof Departure)
                    .map(Departure.class::cast)
                    .collect(Collectors.toList());
            log.info("Filtered Departure rows: " + departureRows.size());

            List<Arrival> arrivalRows = pastFlights.stream()
                    .filter(f -> f instanceof Arrival)
                    .map(Arrival.class::cast)
                    .collect(Collectors.toList());
            log.info("Filtered Arrival rows: " + arrivalRows.size());

            arrivalRows.forEach(flightsService::createOrUpdate);
            departureRows.forEach(flightsService::createOrUpdate);

            log.info("Database data has been updated.");

        }, timePeriod, timePeriod, TimeUnit.MINUTES);

    }

    public void setTimePeriod(int timePeriod) {
        this.timePeriod = timePeriod;
    }

}
