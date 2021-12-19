package com.home.project.vadym.flightapi.model.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name="departure")
public class DepartureEntity {

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
    private String departureTo;

    private Date timeBoard;
    private Date timeDepartureSchedule;
    private Date timeDepartureExpCalc;
    private Date timeDepartureFact;
    private Date timeTakeOffFact;
    private Date timeToStand;
    private Date timeLandFact;

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

        DepartureEntity that = (DepartureEntity) o;

        if (!id.equals(that.id)) return false;
        if (!flightName.equals(that.flightName)) return false;
        if (airCompanyName != null ? !airCompanyName.equals(that.airCompanyName) : that.airCompanyName != null)
            return false;
        if (airplaneName != null ? !airplaneName.equals(that.airplaneName) : that.airplaneName != null) return false;
        if (terminal != null ? !terminal.equals(that.terminal) : that.terminal != null) return false;
        if (!departureTo.equals(that.departureTo)) return false;
        if (timeBoard != null ? !timeBoard.equals(that.timeBoard) : that.timeBoard != null) return false;
        if (timeDepartureSchedule != null ? !timeDepartureSchedule.equals(that.timeDepartureSchedule) : that.timeDepartureSchedule != null)
            return false;
        if (timeDepartureExpCalc != null ? !timeDepartureExpCalc.equals(that.timeDepartureExpCalc) : that.timeDepartureExpCalc != null)
            return false;
        if (timeDepartureFact != null ? !timeDepartureFact.equals(that.timeDepartureFact) : that.timeDepartureFact != null)
            return false;
        if (timeTakeOffFact != null ? !timeTakeOffFact.equals(that.timeTakeOffFact) : that.timeTakeOffFact != null)
            return false;
        if (timeToStand != null ? !timeToStand.equals(that.timeToStand) : that.timeToStand != null) return false;
        return timeLandFact != null ? timeLandFact.equals(that.timeLandFact) : that.timeLandFact == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + flightName.hashCode();
        result = 31 * result + (airCompanyName != null ? airCompanyName.hashCode() : 0);
        result = 31 * result + (airplaneName != null ? airplaneName.hashCode() : 0);
        result = 31 * result + (terminal != null ? terminal.hashCode() : 0);
        result = 31 * result + departureTo.hashCode();
        result = 31 * result + (timeBoard != null ? timeBoard.hashCode() : 0);
        result = 31 * result + (timeDepartureSchedule != null ? timeDepartureSchedule.hashCode() : 0);
        result = 31 * result + (timeDepartureExpCalc != null ? timeDepartureExpCalc.hashCode() : 0);
        result = 31 * result + (timeDepartureFact != null ? timeDepartureFact.hashCode() : 0);
        result = 31 * result + (timeTakeOffFact != null ? timeTakeOffFact.hashCode() : 0);
        result = 31 * result + (timeToStand != null ? timeToStand.hashCode() : 0);
        result = 31 * result + (timeLandFact != null ? timeLandFact.hashCode() : 0);
        return result;
    }
}
