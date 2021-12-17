package com.home.project.vadym.flightapi.model.frontend;

import lombok.Data;

import java.util.Date;

@Data
public class DepartureDTO extends FlightDTO{

    private String gateNumber;
    private String airportToName;
    private String airportToCity;
    private String delayReason;

    private Date timeBoard;
    private Date timeDepartureExpCalc;
    private Date timeDepartureFact;
    private Date timeTakeOffFact;

}
