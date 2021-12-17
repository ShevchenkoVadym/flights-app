package com.home.project.vadym.flightapi.model.externalapi.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.home.project.vadym.flightapi.model.externalapi.airline.Airline;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
abstract public class Flight implements Comparable<Flight>{

    @JsonProperty("ID")
    private long ID;
    @JsonProperty("fltTypeID.code")
    private String fltTypeIDCode;
    @JsonProperty("fltTypeID.name")
    private String fltTypeIDName;
    @JsonProperty("fltCatID.code")
    private String fltCatIDCode;
    @JsonProperty("fltCatID.name")
    private String fltCatIDName;
    @JsonProperty("planeTypeID.code")
    private String planeTypeIDCode;
    @JsonProperty("planeTypeID.IATA")
    private String planeTypeIDIATA;
    @JsonProperty("planeTypeID.name")
    private String planeTypeIDName;
    private String planeNo;
    private String term;
    @JsonProperty("standID.code")
    private String standIDCode;
    @JsonProperty("carrierID.code")
    private String carrierIDCode;
    @JsonProperty("carrierID.IATA")
    private String carrierIDIATA;
    private Date timeTakeofFact;
    private Date timeToStand;
    private Date timeLandFact;
    private int psgCount;
    private int cargoCount;
    private int mailCount;
    private int bagCount;
    private int bagWeigth;
    private Date actual;
    private String status;
    private int showOnSite;
    private String logo;
    private Airline airline;
    private List<CodeShareData> codeShareData;
    private String note;

    public abstract Date getFlightTime();

    @Override
    public int compareTo(Flight flight) {
        return this.getFlightTime().compareTo(flight.getFlightTime());
    }
}
