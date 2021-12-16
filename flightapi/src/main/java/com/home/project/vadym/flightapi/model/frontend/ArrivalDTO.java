package com.home.project.vadym.flightapi.model.frontend;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ArrivalDTO extends FlightDTO {

    private String airportFromName;
    private String airportFromCity;

    private Date timeLandCalc;
    private Date timeLandFact;
}
