package com.home.project.vadym.flightapi.logic;

import com.home.project.vadym.flightapi.model.externalapi.JsonRootContainer;
import com.home.project.vadym.flightapi.model.externalapi.flights.Arrival;
import com.home.project.vadym.flightapi.model.externalapi.flights.Departure;
import com.home.project.vadym.flightapi.model.externalapi.flights.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class SikorskyAPI {

    private final Logger log = LoggerFactory.getLogger(SikorskyAPI.class);
    private final String PUBLIC_URL_API = "https://api.iev.aero/api/flights/";

    private final RestTemplate restTemplate;

    SikorskyAPI() {
        this.restTemplate = new RestTemplateBuilder().build();
    }

    List<Flight> getFlights(LocalDate flightsDate) {
        if(flightsDate == null){
            log.error("Illegal argument passed to getFlights method");
            throw new IllegalArgumentException("Null value passed as flight day");
        }
        String dateForAPI = flightsDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String requestURL = PUBLIC_URL_API + dateForAPI;
        log.info(String.format("Send GET request to the external API: %s", requestURL));
        JsonRootContainer response = restTemplate.getForObject(requestURL, JsonRootContainer.class);
        return retrievalFlights(response);
    }

    private List<Flight> retrievalFlights(JsonRootContainer response) {
        List<Flight> result = new ArrayList<>();
        if(response != null && response.getBody() != null) {
            List<Arrival> arrivals = response.getBody().getArrival();
            List<Departure> departure = response.getBody().getDeparture();
            log.info(String.format("Retrieved %d arrival flights", arrivals.size()));
            log.info(String.format("Retrieved %d departure flights", departure.size()));
            result.addAll(arrivals);
            result.addAll(departure);
        }
        return result;
    }
}
