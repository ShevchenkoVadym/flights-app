package com.home.project.vadym.flightapi.model.externalapi.airline;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class En {

    private int id;
    private String name;
    private String icao;
    private String about;
    private String logoName;
    private String logoSmallName;
    private String locale;
    private boolean showOnSite;
    private int registrationTime;
    private int createdAt;
    private int updatedAt;

}
