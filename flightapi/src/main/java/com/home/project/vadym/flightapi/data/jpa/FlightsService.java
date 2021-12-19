package com.home.project.vadym.flightapi.data.jpa;

import com.home.project.vadym.flightapi.model.entity.ArrivalEntity;
import com.home.project.vadym.flightapi.model.entity.DepartureEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightsService {

    @Autowired
    private ArrivalRepository arrivalRepository;

    @Autowired
    private DepartureRepository departureRepository;

    public ArrivalEntity createOrUpdate(ArrivalEntity arrivalEntity) {
        ArrivalEntity arrival = arrivalRepository.findById((long) arrivalEntity.getId());
        if (arrival != null) {
            if(!arrivalEntity.equals(arrival)){
                // do update necessary fields
                arrival.setTimeArrSchedule(arrivalEntity.getTimeArrSchedule());
                arrival.setTimeArrExpectCalc(arrivalEntity.getTimeArrExpectCalc());
                arrival.setTimeLandCalc(arrivalEntity.getTimeLandCalc());
                arrival.setTimeStandCalc(arrivalEntity.getTimeStandCalc());
                arrival.setTimeStandFact(arrivalEntity.getTimeStandFact());
                arrivalRepository.save(arrival);
            }
            // nothing to update
            return arrival;
        }
        arrivalRepository.save(arrivalEntity);
        return arrivalEntity;
    }

    public DepartureEntity createOrUpdate(DepartureEntity departureEntity) {
        DepartureEntity departure = departureRepository.findById((long) departureEntity.getId());
        if (departure != null) {
            if(!departureEntity.equals(departure)){
                // do update necessary fields
                departure.setTimeBoard(departureEntity.getTimeBoard());
                departure.setTimeDepartureSchedule(departureEntity.getTimeDepartureSchedule());
                departure.setTimeDepartureExpCalc(departureEntity.getTimeDepartureExpCalc());
                departure.setTimeDepartureFact(departureEntity.getTimeDepartureFact());
                departure.setTimeTakeOffFact(departureEntity.getTimeTakeOffFact());
                departure.setTimeToStand(departureEntity.getTimeToStand());
                departure.setTimeLandFact(departureEntity.getTimeLandFact());
                departureRepository.save(departure);
            }
            // nothing to update
            return departure;
        }
        departureRepository.save(departureEntity);
        return departureEntity;
    }

}
