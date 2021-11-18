package com.home.project.vadym.flightapi.model.externalapi.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Departure extends Flight {


    @JsonProperty("airportToID.code")
    private String airportToIDCode;
    @JsonProperty("airportToID.IATA")
    private String airportToIDIATA;
    @JsonProperty("airportToID.name")
    private String airportToIDName;

    private String checkinNo;
    private String gateNo;

    private Date timeDepShedule;
    private Date timeBoard;
    private Date timeDepExpectCalc;
    private Date timeDepFact;

    @JsonProperty("delayReasonID.code")
    private String delayReasonIDCode;
    @JsonProperty("delayReasonID.name")
    private String delayReasonIDName;
    @JsonProperty("handlerID.code")

    private String handlerIDCode;
    @JsonProperty("handlerID.name")
    private String handlerIDName;

    @JsonProperty("airportToID.name_en")
    private String airportToIDNameEn;
    @JsonProperty("airportToID.city")
    private String airportToIDCity;
    @JsonProperty("airportToID.city_en")
    private String airportToIDCityEn;
    @JsonProperty("airportToID.city_ru")
    private String airportToIDCityRu;

    private boolean delay;

    @Override
    public Date getFlightTime() {
        return getTimeDepExpectCalc();
    }
}
