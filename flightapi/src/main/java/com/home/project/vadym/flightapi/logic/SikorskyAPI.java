package com.home.project.vadym.flightapi.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.project.vadym.flightapi.model.Arrival;
import com.home.project.vadym.flightapi.model.Departure;
import com.home.project.vadym.flightapi.model.Flight;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
class SikorskyAPI {

    private final String PUBLIC_URL_API = "https://api.iev.aero/api/flights/";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplate restTemplate;

    public List<Flight> getFlights(LocalDate flightsDate) {
        if(flightsDate == null){
            log.error("Illegal argument passed to getFlights method");
            throw new IllegalArgumentException("Null value passed as flight day");
        }
        String dateForAPI = flightsDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String requestURL = PUBLIC_URL_API + dateForAPI;
        log.info(String.format("Send GET request to the external API: %s", requestURL));
        final String response = restTemplate.getForObject(requestURL, String.class);
        return this.parseResponse(response);
    }

    private List<Flight> parseResponse(String json) {
        List<Flight> flightsList = new ArrayList<>();
        try {
            flightsList = parseJson(json);
        } catch (JsonProcessingException | JSONValidatorException e) {
            log.error(String.format("Cannot parse the JSON. Exception: \n%s", e));
        }
        return flightsList;
    }

    private List<Flight> parseJson(String json) throws JsonProcessingException, JSONValidatorException {
        if(json == null || json.isEmpty()){
            throw new JSONValidatorException("JSON parameter can not be null or empty.");
        }
        List<Flight> flightsList = new ArrayList<>();


        JsonNode jsonNodeRoot = objectMapper.readTree(json);
        JsonNode body = getNestedJSON(jsonNodeRoot, "body");

        JsonNode arrivalArray = getNestedJSON(body, "arrival");
        JsonNode departureArray = getNestedJSON(body, "departure");

        Arrival[] arrivals = objectMapper.readValue(arrivalArray.toString(), Arrival[].class);
        Departure[] departures = objectMapper.readValue(departureArray.toString(), Departure[].class);
        log.info(String.format("Retrieved %d arrival flights", arrivals.length));
        log.info(String.format("Retrieved %d departure flights", arrivals.length));
        flightsList.addAll(List.of(arrivals));
        flightsList.addAll(List.of(departures));
        return flightsList;
    }

    private JsonNode getNestedJSON(JsonNode jsonNode, String key) throws JSONValidatorException {
        JsonNode nestedJSON = jsonNode.get(key);

        if(nestedJSON == null){
            throw new JSONValidatorException(
                    String.format("JSON object is 'null' for the key '%s' from provided JSON '%s'",
                            key,
                            jsonNode.toPrettyString()));
        }

        return nestedJSON;
    }

    private static class JSONValidatorException extends Throwable {
        public JSONValidatorException(String s) {
            super(s);
        }
    }
}
