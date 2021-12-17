package com.home.project.vadym.flightapi.logic;

import com.home.project.vadym.flightapi.model.externalapi.flights.Flight;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class FlightsCache {

    @Autowired
    private SikorskyAPI sikorskyAPI;

    private final byte DELTA_RETRIEVAL_TIME = 30; // minutes
    private Timestamp lastRetrievalTime;

    private List<Flight> flightsCache;

    public List<Flight> getCachedFlightsList() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        if (lastRetrievalTime == null || isTimeToRetrieveTheActualList(currentTimestamp)){
            log.info("Cached list of flights is outdated. Trying to retrieval the actual list.");
            flightsCache = sikorskyAPI.getFlights(LocalDate.now());
            lastRetrievalTime = currentTimestamp;
        }
        log.info(String.format("Return cached list of %d flights", flightsCache.size()));
        return flightsCache;
    }

    private boolean isTimeToRetrieveTheActualList(Timestamp currentTimestamp) {
        Timestamp lastRetrievalWithDelta = new Timestamp(lastRetrievalTime.getTime() +
                        TimeUnit.MINUTES.toMillis(DELTA_RETRIEVAL_TIME));

        return currentTimestamp.after(lastRetrievalWithDelta);
    }
}
