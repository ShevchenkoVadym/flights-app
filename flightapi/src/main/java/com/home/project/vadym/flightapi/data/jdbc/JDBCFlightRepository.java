package com.home.project.vadym.flightapi.data.jdbc;

import com.home.project.vadym.flightapi.model.entity.ArrivalEntity;
import com.home.project.vadym.flightapi.model.entity.DepartureEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;

@Slf4j
@Repository
public class JDBCFlightRepository implements FlightRepository {

    private JdbcTemplate jdbc;

    public JDBCFlightRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ArrivalEntity save(ArrivalEntity arrival) {
        if(!exist(arrival)){
            saveArrivalInfo(arrival);
        }
        return arrival;
    }

    @Override
    public DepartureEntity save(DepartureEntity departure) {
        if(!exist(departure)){
            saveDepartureInfo(departure);
        }
        return departure;
    }

    private boolean exist(DepartureEntity departure) {
        int result = jdbc.queryForObject(
                "SELECT count(*) from Departure where flightName=? and showTime=?",
                new Object[]{departure.getFlightName(), departure.getShowTime()},
                Integer.class);
        return result > 0;
    }

    private boolean exist(ArrivalEntity arrival) {
        int result = jdbc.queryForObject(
                "SELECT count(*) from Arrival where flightName=? and showTime=?",
                new Object[]{arrival.getFlightName(), arrival.getShowTime()},
                Integer.class);
        return result > 0;
    }

    private ArrivalEntity saveArrivalInfo(ArrivalEntity arrival) {
        arrival.setCreatedAt(new Date());
        jdbc.update(
                "INSERT INTO Arrival " +
                        "(createdAt, flightName, airCompanyName, airplaneName, terminal, arrivalFrom, showTime) " +
                        "values (?, ?, ?, ?, ?, ?, ?)",
                new Timestamp(arrival.getCreatedAt().getTime()),
                arrival.getFlightName(),
                arrival.getAirCompanyName(),
                arrival.getAirplaneName(),
                arrival.getTerminal(),
                arrival.getArrivalFrom(),
                arrival.getShowTime());
        return arrival;
    }

    private DepartureEntity saveDepartureInfo(DepartureEntity departure) {
        departure.setCreatedAt(new Date());
        jdbc.update(
                "insert into Departure " +
                        "(createdAt, flightName, airCompanyName, airplaneName, terminal, arrivalFrom, showTime) " +
                        "values (?, ?, ?, ?, ?, ?, ?)",
                new Timestamp(departure.getCreatedAt().getTime()),
                departure.getFlightName(),
                departure.getAirCompanyName(),
                departure.getAirplaneName(),
                departure.getTerminal(),
                departure.getDepartureTo(),
                departure.getShowTime());
        return departure;
    }



}
