package com.voronkov.vaccination.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vaccines")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(exclude = {"vaccinations"})
public class Vaccine extends BaseEntity {

    @Column(name = "disease_name")
    @Size(max = 128)
    private String diseaseName;

    @Column(name = "vaccine_name")
    @Size(max = 128)
    private String vaccineName;

    @Column(name = "age")
    private int age;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vaccine")
    private List<Vaccination> vaccinations;
}
