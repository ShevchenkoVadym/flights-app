package com.home.project.vadym.flightapi.model;

import com.home.project.vadym.flightapi.model.externalapi.flights.Arrival;
import com.home.project.vadym.flightapi.model.externalapi.flights.Departure;
import com.home.project.vadym.flightapi.model.externalapi.flights.Flight;
import com.home.project.vadym.flightapi.model.frontend.ArrivalDTO;
import com.home.project.vadym.flightapi.model.frontend.DepartureDTO;
import com.home.project.vadym.flightapi.model.frontend.FlightDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.home.project.vadym.flightapi.model.frontend.FlightDTO.FlightType.ARRIVAL;
import static com.home.project.vadym.flightapi.model.frontend.FlightDTO.FlightType.DEPARTURE;

@Component
public class Mapper {

    public FlightDTO toDTO(Flight flight) {
        FlightDTO flightDTO;
        if(flight instanceof Departure){
            Departure departure = (Departure) flight;
            flightDTO = new DepartureDTO();
            flightDTO.setFlight(DEPARTURE);
            ((DepartureDTO) flightDTO).setGateNumber(departure.getGateNo());
            ((DepartureDTO) flightDTO).setAirportToName(departure.getAirportToIDName());
            ((DepartureDTO) flightDTO).setAirportToCity(departure.getAirportToIDCity());
            ((DepartureDTO) flightDTO).setDelayReason(departure.getDelayReasonIDName());
            ((DepartureDTO) flightDTO).setTimeBoard(departure.getTimeBoard());
            ((DepartureDTO) flightDTO).setTimeDepartureExpCalc(departure.getTimeDepExpectCalc());
            ((DepartureDTO) flightDTO).setTimeDepartureFact(departure.getTimeDepFact());
            ((DepartureDTO) flightDTO).setTimeTakeOffFact(departure.getTimeTakeofFact());
        } else {
            Arrival departure = (Arrival) flight;
            flightDTO = new ArrivalDTO();
            flightDTO.setFlight(ARRIVAL);
            ((ArrivalDTO) flightDTO).setAirportFromName(departure.getAirportFromIDName());
            ((ArrivalDTO) flightDTO).setAirportFromCity(departure.getAirportFromIDCity());
            ((ArrivalDTO) flightDTO).setTimeLandCalc(departure.getTimeLandCalc());
            ((ArrivalDTO) flightDTO).setTimeLandFact(departure.getTimeLandFact());
        }
        flightDTO.setFlightName(Objects.requireNonNull(flight.getCodeShareData().stream().findFirst().orElse(null)).getCodeShare());
        flightDTO.setFlightType(flight.getFltTypeIDName());
        flightDTO.setFlightCategory(flight.getFltCatIDName());
        flightDTO.setAirCompanyName(flight.getAirline().getEn().getName());
        flightDTO.setAirplaneName(flight.getPlaneTypeIDName());
        flightDTO.setTerminal(flight.getTerm());
        return flightDTO;
    }


}
