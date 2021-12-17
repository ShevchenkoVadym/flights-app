package com.home.project.vadym.flightapi.model.frontend;

import lombok.Data;

import java.util.Date;

@Data
public class ArrivalDTO extends FlightDTO {

    private String airportFromName;
    private String airportFromCity;

    private Date timeLandCalc;
    private Date timeLandFact;
}
