package com.home.project.vadym.flightapi.model.externalapi.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.Date;

@Getter
@Setter
public class Arrival extends Flight {

    private String fltNo;

    @JsonProperty("airportFromID.code")
    private String airportFromIDCode;
    @JsonProperty("airportFromID.IATA")
    private String airportFromIDIATA;
    @JsonProperty("airportFromID.name")
    private String airportFromIDName;

    private Date timeArrShedule;
    private Date timeArrExpectCalc;
    private Date timeLandCalc;
    private Date timeStandCalc;
    private Date timeStandFact;

    @JsonProperty("airportFromID.name_en")
    private String airportFromIDNameEn;
    @JsonProperty("airportFromID.city")
    private String airportFromIDCity;
    @JsonProperty("airportFromID.city_en")
    private String airportFromIDCityEn;
    @JsonProperty("airportFromID.city_ru")
    private String airportFromIDCityRu;

    private String belt;

    @Override
    public Date getFlightTime() {
        return getTimeLandCalc();
    }

    @Override
    public int compareTo(Flight flight) {
        return Comparator.comparing(Flight::getFlightTime)
                .thenComparing(Flight::getTimeToStand)
                .compare(this, flight);
    }

}
