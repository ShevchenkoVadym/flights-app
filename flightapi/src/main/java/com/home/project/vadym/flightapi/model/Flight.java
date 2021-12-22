package com.home.project.vadym.flightapi.model;

import com.home.project.vadym.flightapi.model.frontend.FlightDTO;

import java.util.Date;

abstract public class Flight implements Comparable<Flight>{

    public abstract Date getShowTime();
    public abstract FlightDTO toDTO();

    @Override
    public int compareTo(Flight flight) {
        return this.getShowTime().compareTo(flight.getShowTime());
    }
}
