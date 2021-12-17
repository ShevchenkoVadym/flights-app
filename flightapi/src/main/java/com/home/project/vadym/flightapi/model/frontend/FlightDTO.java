package com.home.project.vadym.flightapi.model.frontend;

import lombok.Data;

@Data
public abstract class FlightDTO {

    private FlightType flight;
    private String flightName;
    private String flightType;
    private String flightCategory;
    private String airCompanyName;
    private String airplaneName;
    private String terminal;

    public enum FlightType {
        DEPARTURE, ARRIVAL
    }

}


