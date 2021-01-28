package com.voronkov.vaccination.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vaccinations")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"user"})
public class Vaccination extends BaseEntity {

    @Column(name = "disease_name")
    private String diseaseName;

    @Column(name = "vaccine_name")
    private String vaccineName;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "vaccination_date", columnDefinition = "DATE")
    private LocalDate vaccinationDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "planned_vaccination_date")
    private LocalDate plannedVaccinationDate;

    public Vaccination(User user, Vaccine vaccine, String vaccineName, String diseaseName, LocalDate vaccinationDate, LocalDate plannedVaccinationDate) {
        this.user = user;
        this.vaccine = vaccine;
        this.vaccineName = vaccineName;
        this.diseaseName = diseaseName;
        this.vaccinationDate = vaccinationDate;
        this.plannedVaccinationDate = plannedVaccinationDate;
    }
}
