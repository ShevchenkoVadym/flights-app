package com.home.project.vadym.flightapi.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class FlightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name="flightnumber")
    private String flightNumber;

    @Column(name= "airline")
    private String airline;

    @Column(name="terminal")
    private String terminal;

    @Column(name="date")
    private Date date;

    public FlightEntity() {
    }

    public FlightEntity(String flightNumber, String airline, String terminal, Date date) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.terminal = terminal;
        this.date = date;
    }
}
