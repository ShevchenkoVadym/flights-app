package com.home.project.vadym.flightapi.data.jpa;

import com.home.project.vadym.flightapi.model.Arrival;
import com.home.project.vadym.flightapi.model.Departure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightsService {

    @Autowired
    private ArrivalRepository arrivalRepository;

    @Autowired
    private DepartureRepository departureRepository;

    public Arrival createOrUpdate(Arrival arrival) {
        Arrival arrivalEntity = arrivalRepository.findById(arrival.getId());
        if (arrivalEntity != null) {
            if(!arrival.equals(arrivalEntity)){
                arrival.setCreatedAt(arrivalEntity.getCreatedAt());
                arrivalRepository.save(arrival);
            }
            // nothing to update
            return arrival;
        }
        arrivalRepository.save(arrival);
        return arrival;
    }

    public Departure createOrUpdate(Departure departure) {
        Departure departureEntity = departureRepository.findById(departure.getId());
        if (departureEntity != null) {
            if(!departure.equals(departureEntity)){
                departure.setCreatedAt(departureEntity.getCreatedAt());
                departureRepository.save(departure);
            }
            // nothing to update
            return departure;
        }
        departureRepository.save(departure);
        return departure;
    }

}
