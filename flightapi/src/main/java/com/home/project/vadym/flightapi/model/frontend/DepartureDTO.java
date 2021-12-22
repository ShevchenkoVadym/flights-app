package com.home.project.vadym.flightapi.model.frontend;

import lombok.Data;

import java.util.Date;

import static com.home.project.vadym.flightapi.model.frontend.FlightDTO.FlightType.Arrival;
import static com.home.project.vadym.flightapi.model.frontend.FlightDTO.FlightType.Departure;

@Data
public class DepartureDTO extends FlightDTO {

    private long id;
    private final FlightType flightType = Departure;
    private String flightName;
    private String airportToCityNameEn;
    private String airportToCityNameRu;
    private String flightNumber;
    private String flightTypeName;
    private String flightCategoryName;
    private String airCompanyName;
    private String planeName;
    private String terminal;
    private String status;
    private Date showTime;

}
