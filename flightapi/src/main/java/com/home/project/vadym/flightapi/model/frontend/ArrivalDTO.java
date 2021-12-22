package com.home.project.vadym.flightapi.model.frontend;

import lombok.Data;

import java.util.Date;

import static com.home.project.vadym.flightapi.model.frontend.FlightDTO.FlightType.Arrival;

@Data
public class ArrivalDTO extends FlightDTO {

    private long id;
    private final FlightType flightType = Arrival;
    private String flightName;
    private String airportFromCityNameEn;
    private String airportFromCityNameRu;
    private String flightNumber;
    private String flightTypeName;
    private String flightCategoryName;
    private String airCompanyName;
    private String planeName;
    private String terminal;
    private String status;
    private Date showTime;

}
