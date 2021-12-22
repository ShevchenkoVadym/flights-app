package com.home.project.vadym.flightapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.home.project.vadym.flightapi.model.frontend.ArrivalDTO;
import com.home.project.vadym.flightapi.model.frontend.FlightDTO;
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
@Table(name="arrival")
public class Arrival extends Flight {

    @Id
    @JsonProperty("ID")
    public long id;

    public String flightName;

    @Transient
    @JsonProperty("fltNo")
    public String flightNumber;

    @Transient
    @JsonProperty("fltTypeID.name")
    public String flightTypeName;

    @Transient
    @JsonProperty("fltCatID.name")
    public String flightCategoryName;

    @Transient
    @JsonProperty("fltTypeID.code")
    public String flightTypeCode;

    public String airCompanyName;

    @Transient
    @JsonProperty("airportFromID.name_en")
    public String airportFromNameEn;

    @JsonProperty("airportFromID.city_ru")
    public String airportFromCityNameRu;

    @Transient
    @JsonProperty("airportFromID.city_en")
    public String airportFromCityNameEn;

    @Transient
    @JsonProperty("airportFromID.code")
    public String airportFromCode;

    @Transient
    @JsonProperty("airportFromID.IATA")
    public String airportFromIDIATA;

    @JsonProperty("timeToStand")
    public Date timeToStand;

    @Transient
    @JsonProperty("timeStandCalc")
    public Date timeStandCalc;

    @JsonProperty("timeStandFact")
    public Date timeStandFact;

    @Transient
    @JsonProperty("timeLandCalc")
    public Date timeLandCalc;

    @JsonProperty("timeLandFact")
    public Date timeLandFact;

    @JsonProperty("planeTypeID.name")
    public String planeName;

    @Transient
    @JsonProperty("planeTypeID.code")
    public String planeTypeCode;

    @JsonProperty("planeNo")
    public String planeNo;

    @Transient
    @JsonProperty("planeTypeID.IATA")
    public String planeTypeIATA;

    @JsonProperty("term")
    public String terminal;

    @JsonProperty("bagWeigth")
    public int bagWeigth;

    @Transient
    @JsonProperty("bagCount")
    public int bagCount;

    @JsonProperty("showOnSite")
    public int showOnSite;

    @Transient
    @JsonProperty("belt")
    public String belt;

    @JsonProperty("note")
    public String note;

    @JsonProperty("psgCount")
    public int passengersCount;

    @JsonProperty("status")
    public String status; // TODO Enum

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
        if(timeLandFact != null){
            return timeLandFact;
        } else if(timeLandCalc != null){
            return timeLandCalc;
        }
        return timeToStand;
    }

    @Override
    public FlightDTO toDTO() {
        ArrivalDTO arrivalDTO = new ArrivalDTO();
        arrivalDTO.setId(this.getId());
        arrivalDTO.setFlightName(this.getFlightName());
        arrivalDTO.setAirportFromCityNameEn(this.getAirportFromCityNameEn());
        arrivalDTO.setAirportFromCityNameRu(this.getAirportFromCityNameRu());
        arrivalDTO.setFlightNumber(this.getFlightNumber());
        arrivalDTO.setFlightTypeName(this.getFlightTypeName());
        arrivalDTO.setFlightCategoryName(this.getFlightCategoryName());
        arrivalDTO.setAirCompanyName(this.getAirCompanyName());
        arrivalDTO.setPlaneName(this.getPlaneName());
        arrivalDTO.setTerminal(this.getTerminal());
        arrivalDTO.setStatus(this.getStatus());
        arrivalDTO.setShowTime(this.getShowTime());
        return arrivalDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Arrival arrival = (Arrival) o;

        if (id != arrival.id) return false;
        if (bagWeigth != arrival.bagWeigth) return false;
        if (bagCount != arrival.bagCount) return false;
        if (showOnSite != arrival.showOnSite) return false;
        if (passengersCount != arrival.passengersCount) return false;
        if (flightName != null ? !flightName.equals(arrival.flightName) : arrival.flightName != null) return false;
        if (airCompanyName != null ? !airCompanyName.equals(arrival.airCompanyName) : arrival.airCompanyName != null)
            return false;
        if (airportFromNameEn != null ? !airportFromNameEn.equals(arrival.airportFromNameEn) : arrival.airportFromNameEn != null)
            return false;
        if (timeToStand != null ? !timeToStand.equals(arrival.timeToStand) : arrival.timeToStand != null) return false;
        if (timeStandCalc != null ? !timeStandCalc.equals(arrival.timeStandCalc) : arrival.timeStandCalc != null)
            return false;
        if (timeStandFact != null ? !timeStandFact.equals(arrival.timeStandFact) : arrival.timeStandFact != null)
            return false;
        if (timeLandCalc != null ? !timeLandCalc.equals(arrival.timeLandCalc) : arrival.timeLandCalc != null)
            return false;
        if (timeLandFact != null ? !timeLandFact.equals(arrival.timeLandFact) : arrival.timeLandFact != null)
            return false;
        if (planeName != null ? !planeName.equals(arrival.planeName) : arrival.planeName != null) return false;
        if (planeTypeCode != null ? !planeTypeCode.equals(arrival.planeTypeCode) : arrival.planeTypeCode != null)
            return false;
        if (planeNo != null ? !planeNo.equals(arrival.planeNo) : arrival.planeNo != null) return false;
        if (planeTypeIATA != null ? !planeTypeIATA.equals(arrival.planeTypeIATA) : arrival.planeTypeIATA != null)
            return false;
        if (terminal != null ? !terminal.equals(arrival.terminal) : arrival.terminal != null) return false;
        if (belt != null ? !belt.equals(arrival.belt) : arrival.belt != null) return false;
        if (note != null ? !note.equals(arrival.note) : arrival.note != null) return false;
        return status != null ? status.equals(arrival.status) : arrival.status == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (flightName != null ? flightName.hashCode() : 0);
        result = 31 * result + (airCompanyName != null ? airCompanyName.hashCode() : 0);
        result = 31 * result + (airportFromNameEn != null ? airportFromNameEn.hashCode() : 0);
        result = 31 * result + (timeToStand != null ? timeToStand.hashCode() : 0);
        result = 31 * result + (timeStandCalc != null ? timeStandCalc.hashCode() : 0);
        result = 31 * result + (timeStandFact != null ? timeStandFact.hashCode() : 0);
        result = 31 * result + (timeLandCalc != null ? timeLandCalc.hashCode() : 0);
        result = 31 * result + (timeLandFact != null ? timeLandFact.hashCode() : 0);
        result = 31 * result + (planeName != null ? planeName.hashCode() : 0);
        result = 31 * result + (planeTypeCode != null ? planeTypeCode.hashCode() : 0);
        result = 31 * result + (planeNo != null ? planeNo.hashCode() : 0);
        result = 31 * result + (planeTypeIATA != null ? planeTypeIATA.hashCode() : 0);
        result = 31 * result + (terminal != null ? terminal.hashCode() : 0);
        result = 31 * result + bagWeigth;
        result = 31 * result + bagCount;
        result = 31 * result + showOnSite;
        result = 31 * result + (belt != null ? belt.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + passengersCount;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
