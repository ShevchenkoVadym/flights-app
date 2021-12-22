package com.home.project.vadym.flightapi.model.frontend;

import lombok.Data;

@Data
public abstract class FlightDTO {

    public enum FlightType {
        Departure, Arrival
    }

}


