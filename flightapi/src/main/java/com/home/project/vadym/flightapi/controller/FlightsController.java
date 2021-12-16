package com.home.project.vadym.flightapi.controller;

import com.home.project.vadym.flightapi.exceptions.NoFlightsFoundException;
import com.home.project.vadym.flightapi.model.Mapper;
import com.home.project.vadym.flightapi.model.frontend.FlightDTO;
import com.home.project.vadym.flightapi.service.FlightAPIService;
import com.home.project.vadym.flightapi.model.externalapi.flights.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/flights")
public class FlightsController {

    @Autowired
    private FlightAPIService flightAPIService;

    @Autowired
    private Mapper mapper;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("next")
    public ResponseEntity getNextFlights(){
        try {
            List<FlightDTO> nextFlights = flightAPIService.getNextFlights().stream()
                    .map(mapper::toDTO).collect(toList());

            return ResponseEntity.ok(nextFlights);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Something went wrong!");
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("past")
    public ResponseEntity getPastFlights(){
        try {
            List<FlightDTO> pastFlights = flightAPIService.getPastFlights().stream()
                    .map(mapper::toDTO).collect(toList());

            return ResponseEntity.ok(pastFlights);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Something went wrong!");
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("showtime")
    public ResponseEntity getAnyFlightSoon(){
        try {
            FlightDTO nextAnyFlight = mapper.toDTO(flightAPIService.getAnyFlightSoon());
            return ResponseEntity.ok(nextAnyFlight);
        } catch (NoFlightsFoundException e){
            return ResponseEntity.ok().body(e);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Something went wrong!");
        }
    }
}
