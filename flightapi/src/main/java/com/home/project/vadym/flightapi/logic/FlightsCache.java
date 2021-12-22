package com.home.project.vadym.flightapi.logic;

import com.home.project.vadym.flightapi.model.Flight;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@ConfigurationProperties(prefix="flight.cache.property")
public class FlightsCache {

    @Autowired
    private SikorskyAPI sikorskyAPI;

    private byte deltaRetrievalTime = 10; // minutes

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

    public void setDeltaRetrievalTime(byte deltaRetrievalTime) {
        this.deltaRetrievalTime = deltaRetrievalTime;
    }

    private boolean isTimeToRetrieveTheActualList(Timestamp currentTimestamp) {
        Timestamp lastRetrievalWithDelta = new Timestamp(lastRetrievalTime.getTime() +
                        TimeUnit.MINUTES.toMillis(deltaRetrievalTime));

        return currentTimestamp.after(lastRetrievalWithDelta);
    }
}
