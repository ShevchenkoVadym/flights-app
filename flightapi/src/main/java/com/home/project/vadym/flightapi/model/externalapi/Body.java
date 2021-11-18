package com.home.project.vadym.flightapi.model.externalapi;

import com.home.project.vadym.flightapi.model.externalapi.flights.Arrival;
import com.home.project.vadym.flightapi.model.externalapi.flights.Departure;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Body {

    private List<Departure> departure;
    private List<Arrival> arrival;
    
}
