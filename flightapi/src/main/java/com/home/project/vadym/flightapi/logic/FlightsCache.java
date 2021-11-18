package com.home.project.vadym.flightapi.logic;

import com.home.project.vadym.flightapi.model.externalapi.flights.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlightsCache {

    private final Logger log = LoggerFactory.getLogger(FlightsCache.class);
    private static final FlightsCache FLIGHTS_CACHE = new FlightsCache();
    private final byte DELTA_RETRIEVAL_TIME = 30; // minutes
    private Timestamp lastRetrievalTime;

    private List<Flight> flightsCache;
    private SikorskyAPI sikorskyAPI;


    private FlightsCache() {
        this.sikorskyAPI = new SikorskyAPI();
    }

    public static FlightsCache getInstance() {
        return FLIGHTS_CACHE;
    }

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
