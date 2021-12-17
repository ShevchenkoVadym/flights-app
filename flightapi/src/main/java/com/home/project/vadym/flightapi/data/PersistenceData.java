package com.home.project.vadym.flightapi.data;

import com.home.project.vadym.flightapi.data.jdbc.JDBCFlightRepository;
import com.home.project.vadym.flightapi.model.Mapper;
import com.home.project.vadym.flightapi.model.entity.ArrivalEntity;
import com.home.project.vadym.flightapi.model.entity.DepartureEntity;
import com.home.project.vadym.flightapi.model.externalapi.flights.Arrival;
import com.home.project.vadym.flightapi.model.externalapi.flights.Departure;
import com.home.project.vadym.flightapi.model.externalapi.flights.Flight;
import com.home.project.vadym.flightapi.service.FlightAPIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PersistenceData {

    @Autowired
    private FlightAPIService flightAPIService;

    @Autowired
    private Mapper mapper;

    @Autowired
    private JDBCFlightRepository flightRepository;

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
            List<DepartureEntity> departureRows = pastFlights.stream()
                    .filter(f -> f instanceof Departure)
                    .map(dep -> mapper.toEntity((Departure) dep)).collect(Collectors.toList());
            log.info("Filtered Departure rows: " + departureRows.size());

            List<ArrivalEntity> arrivalRows = pastFlights.stream()
                    .filter(f -> f instanceof Arrival)
                    .map(arr -> mapper.toEntity((Arrival) arr)).collect(Collectors.toList());
            log.info("Filtered Arrival rows: " + arrivalRows.size());

            departureRows.forEach(flightRepository::save);
            arrivalRows.forEach(flightRepository::save);

            log.info("Database data has been updated.");

        }, 1, 1, TimeUnit.HOURS);

    }

}
