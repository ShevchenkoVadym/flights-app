package com.home.project.vadym.flightapi.model.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name="arrival")
public class ArrivalEntity {

    @Id
    private Long id;

    private Date createdAt;
    private Date updatedAt;

    @NotNull
    private String flightName;
    private String airCompanyName;
    private String airplaneName;
    private String terminal;

    @NotNull
    private String arrivalFrom;

    private Date timeArrSchedule;
    private Date timeArrExpectCalc;
    private Date timeLandCalc;
    private Date timeStandCalc;
    private Date timeStandFact;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrivalEntity that = (ArrivalEntity) o;

        if (!id.equals(that.id)) return false;
        if (!flightName.equals(that.flightName)) return false;
        if (airCompanyName != null ? !airCompanyName.equals(that.airCompanyName) : that.airCompanyName != null)
            return false;
        if (airplaneName != null ? !airplaneName.equals(that.airplaneName) : that.airplaneName != null) return false;
        if (terminal != null ? !terminal.equals(that.terminal) : that.terminal != null) return false;
        if (!arrivalFrom.equals(that.arrivalFrom)) return false;
        if (timeArrSchedule != null ? !timeArrSchedule.equals(that.timeArrSchedule) : that.timeArrSchedule != null)
            return false;
        if (timeArrExpectCalc != null ? !timeArrExpectCalc.equals(that.timeArrExpectCalc) : that.timeArrExpectCalc != null)
            return false;
        if (timeLandCalc != null ? !timeLandCalc.equals(that.timeLandCalc) : that.timeLandCalc != null) return false;
        if (timeStandCalc != null ? !timeStandCalc.equals(that.timeStandCalc) : that.timeStandCalc != null)
            return false;
        return timeStandFact != null ? timeStandFact.equals(that.timeStandFact) : that.timeStandFact == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + flightName.hashCode();
        result = 31 * result + (airCompanyName != null ? airCompanyName.hashCode() : 0);
        result = 31 * result + (airplaneName != null ? airplaneName.hashCode() : 0);
        result = 31 * result + (terminal != null ? terminal.hashCode() : 0);
        result = 31 * result + arrivalFrom.hashCode();
        result = 31 * result + (timeArrSchedule != null ? timeArrSchedule.hashCode() : 0);
        result = 31 * result + (timeArrExpectCalc != null ? timeArrExpectCalc.hashCode() : 0);
        result = 31 * result + (timeLandCalc != null ? timeLandCalc.hashCode() : 0);
        result = 31 * result + (timeStandCalc != null ? timeStandCalc.hashCode() : 0);
        result = 31 * result + (timeStandFact != null ? timeStandFact.hashCode() : 0);
        return result;
    }
}
