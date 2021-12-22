package com.home.project.vadym.flightapi.controller;

import com.home.project.vadym.flightapi.exceptions.NoFlightsFoundException;
import com.home.project.vadym.flightapi.model.Flight;
import com.home.project.vadym.flightapi.model.frontend.FlightDTO;
import com.home.project.vadym.flightapi.service.FlightAPIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@RestController
@RequestMapping("/api/flights")
public class FlightsController {

    @Autowired
    private FlightAPIService flightAPIService;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("next")
    public ResponseEntity getNextFlights(){
        try {
            List<FlightDTO> nextFlights = flightAPIService.getNextFlights().stream()
                    .map(Flight::toDTO).collect(toList());
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
                    .map(Flight::toDTO).collect(toList());

            return ResponseEntity.ok(pastFlights);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Something went wrong!");
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("showtime")
    public ResponseEntity getAnyFlightSoon(){
        try {
            FlightDTO nextAnyFlight = flightAPIService.getAnyFlightSoon().toDTO();
            return ResponseEntity.ok(nextAnyFlight);
        } catch (NoFlightsFoundException e){
            return ResponseEntity.ok().body(e);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Something went wrong!");
        }
    }
}
