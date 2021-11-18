package com.home.project.vadym.flightapi.model.externalapi.flights;

import com.home.project.vadym.flightapi.model.externalapi.airline.Airline;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class CodeShareData {

    private String icao;
    private String codeShare;
    private String logo;
    private Airline airline;

}
