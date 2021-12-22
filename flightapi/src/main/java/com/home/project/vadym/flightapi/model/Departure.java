package com.home.project.vadym.flightapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.home.project.vadym.flightapi.model.frontend.DepartureDTO;
import com.home.project.vadym.flightapi.model.frontend.FlightDTO;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name="departure")
public class Departure extends Flight {

    @Id
    @JsonProperty("ID")
    public long id;

    public String flightName;

    public String airCompanyName;

    @Transient
    @JsonProperty("fltNo")
    public String flightNumber;

    @Transient
    @JsonProperty("fltTypeID.code")
    public String flightTypeCode;

    @Transient
    @JsonProperty("fltTypeID.name")
    public String flightTypeName;

    @Transient
    @JsonProperty("fltCatID.name")
    public String flightCategoryName;

    @Transient
    @JsonProperty("airportToID.name_en")
    public String airportToNameEn;

    @Transient
    @JsonProperty("airportToID.city_en")
    public String airportToCityNameEn;

    @JsonProperty("airportToID.city_ru")
    public String airportToCityNameRu;

    @Transient
    @JsonProperty("airportToID.code")
    public String airportToCode;

    @Transient
    @JsonProperty("airportToID.IATA")
    public String airportToIDIATA;

    @Transient
    @JsonProperty("timeBoard")
    public Date timeBoard;

    @JsonProperty("timeDepShedule")
    public Date timeDepartureSchedule;

    @Transient
    @JsonProperty("timeDepExpectCalc")
    public Date timeDepExpectCalc;

    @JsonProperty("timeDepFact")
    public Date timeDepartureFact;

    @JsonProperty("timeTakeofFact")
    public Date timeTakeOfFact;

    @JsonProperty("planeTypeID.name")
    public String planeName;

    @JsonProperty("planeNo")
    public String planeNo;

    @Transient
    @JsonProperty("planeTypeID.code")
    public String planeTypeCode;

    @Transient
    @JsonProperty("planeTypeID.IATA")
    public String planeTypeIATA;

    @JsonProperty("term")
    public String terminal;

    @JsonProperty("gateNo")
    public String gateNumber;

    @Transient
    @JsonProperty("checkinNo")
    public String checkInNo;

    @Transient
    @JsonProperty("delayReasonID.code")
    public String delayReasonCode;

    @JsonProperty("delayReasonID.name")
    public String delayReasonName;

    @JsonProperty("psgCount")
    public int passengersCount;

    @Transient
    @JsonProperty("bagCount")
    public int bagCount;

    @JsonProperty("bagWeigth")
    public int bagWeigth;

    @JsonProperty("status")
    public String status; // TODO Enum

    @JsonProperty("showOnSite")
    public int showOnSite;

    @JsonProperty("delay")
    public boolean delay;

    private Date createdAt;
    private Date updatedAt;

    @JsonProperty("codeShareData")
    private void parseFlightName(List<Map<String, Object>> codeShareData) {
        if(codeShareData != null && codeShareData.size() > 0) {
            this.flightName = (String) codeShareData.get(0).get("codeShare");
        }
    }

    @JsonProperty("airline")
    private void parseAirCompanyName(Map<String, Map<String, String>> airline) {
        if(airline != null) {
            Map<String, String> en = airline.get("en");
            if(en != null){
                this.airCompanyName = en.get("name");
            }
        }
    }

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = new Date();
    }

    @Override
    public Date getShowTime() {
        if(timeTakeOfFact != null){
            return timeTakeOfFact;
        } else if(timeDepartureFact != null) {
            return timeDepartureFact;
        } else if(timeDepExpectCalc != null){
            return timeDepExpectCalc;
        }
        return timeDepartureSchedule;
    }

    @Override
    public FlightDTO toDTO() {
        DepartureDTO departureDTO = new DepartureDTO();
        departureDTO.setId(this.getId());
        departureDTO.setFlightName(this.getFlightName());
        departureDTO.setAirportToCityNameEn(this.getAirportToCityNameEn());
        departureDTO.setAirportToCityNameRu(this.getAirportToCityNameRu());
        departureDTO.setFlightNumber(this.getFlightNumber());
        departureDTO.setFlightTypeName(this.getFlightTypeName());
        departureDTO.setFlightCategoryName(this.getFlightCategoryName());
        departureDTO.setAirCompanyName(this.getAirCompanyName());
        departureDTO.setPlaneName(this.getPlaneName());
        departureDTO.setTerminal(this.getTerminal());
        departureDTO.setStatus(this.getStatus());
        departureDTO.setShowTime(this.getShowTime());
        return departureDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Departure departure = (Departure) o;

        if (id != departure.id) return false;
        if (passengersCount != departure.passengersCount) return false;
        if (bagCount != departure.bagCount) return false;
        if (bagWeigth != departure.bagWeigth) return false;
        if (showOnSite != departure.showOnSite) return false;
        if (delay != departure.delay) return false;
        if (flightName != null ? !flightName.equals(departure.flightName) : departure.flightName != null) return false;
        if (airCompanyName != null ? !airCompanyName.equals(departure.airCompanyName) : departure.airCompanyName != null)
            return false;
        if (airportToNameEn != null ? !airportToNameEn.equals(departure.airportToNameEn) : departure.airportToNameEn != null)
            return false;
        if (timeBoard != null ? !timeBoard.equals(departure.timeBoard) : departure.timeBoard != null) return false;
        if (timeDepartureSchedule != null ? !timeDepartureSchedule.equals(departure.timeDepartureSchedule) : departure.timeDepartureSchedule != null)
            return false;
        if (timeDepExpectCalc != null ? !timeDepExpectCalc.equals(departure.timeDepExpectCalc) : departure.timeDepExpectCalc != null)
            return false;
        if (timeDepartureFact != null ? !timeDepartureFact.equals(departure.timeDepartureFact) : departure.timeDepartureFact != null)
            return false;
        if (timeTakeOfFact != null ? !timeTakeOfFact.equals(departure.timeTakeOfFact) : departure.timeTakeOfFact != null)
            return false;
        if (planeName != null ? !planeName.equals(departure.planeName) : departure.planeName != null) return false;
        if (planeNo != null ? !planeNo.equals(departure.planeNo) : departure.planeNo != null) return false;
        if (planeTypeCode != null ? !planeTypeCode.equals(departure.planeTypeCode) : departure.planeTypeCode != null)
            return false;
        if (planeTypeIATA != null ? !planeTypeIATA.equals(departure.planeTypeIATA) : departure.planeTypeIATA != null)
            return false;
        if (terminal != null ? !terminal.equals(departure.terminal) : departure.terminal != null) return false;
        if (gateNumber != null ? !gateNumber.equals(departure.gateNumber) : departure.gateNumber != null) return false;
        if (checkInNo != null ? !checkInNo.equals(departure.checkInNo) : departure.checkInNo != null) return false;
        if (delayReasonCode != null ? !delayReasonCode.equals(departure.delayReasonCode) : departure.delayReasonCode != null)
            return false;
        if (delayReasonName != null ? !delayReasonName.equals(departure.delayReasonName) : departure.delayReasonName != null)
            return false;
        return status != null ? status.equals(departure.status) : departure.status == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (flightName != null ? flightName.hashCode() : 0);
        result = 31 * result + (airCompanyName != null ? airCompanyName.hashCode() : 0);
        result = 31 * result + (airportToNameEn != null ? airportToNameEn.hashCode() : 0);
        result = 31 * result + (timeBoard != null ? timeBoard.hashCode() : 0);
        result = 31 * result + (timeDepartureSchedule != null ? timeDepartureSchedule.hashCode() : 0);
        result = 31 * result + (timeDepExpectCalc != null ? timeDepExpectCalc.hashCode() : 0);
        result = 31 * result + (timeDepartureFact != null ? timeDepartureFact.hashCode() : 0);
        result = 31 * result + (timeTakeOfFact != null ? timeTakeOfFact.hashCode() : 0);
        result = 31 * result + (planeName != null ? planeName.hashCode() : 0);
        result = 31 * result + (planeNo != null ? planeNo.hashCode() : 0);
        result = 31 * result + (planeTypeCode != null ? planeTypeCode.hashCode() : 0);
        result = 31 * result + (planeTypeIATA != null ? planeTypeIATA.hashCode() : 0);
        result = 31 * result + (terminal != null ? terminal.hashCode() : 0);
        result = 31 * result + (gateNumber != null ? gateNumber.hashCode() : 0);
        result = 31 * result + (checkInNo != null ? checkInNo.hashCode() : 0);
        result = 31 * result + (delayReasonCode != null ? delayReasonCode.hashCode() : 0);
        result = 31 * result + (delayReasonName != null ? delayReasonName.hashCode() : 0);
        result = 31 * result + passengersCount;
        result = 31 * result + bagCount;
        result = 31 * result + bagWeigth;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + showOnSite;
        result = 31 * result + (delay ? 1 : 0);
        return result;
    }

}
