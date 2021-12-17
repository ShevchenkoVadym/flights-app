package com.home.project.vadym.flightapi.model.entity;

import lombok.Data;

import java.util.Date;

@Data
public class DepartureEntity {

    private Long id;
    private Date createdAt;

    private String flightName;
    private String airCompanyName;
    private String airplaneName;
    private String terminal;
    private String departureTo;
    private Date showTime;

}