package com.voronkov.vaccination.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.time.Duration;

@Entity
@Table(name = "vaccine")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class Vaccine extends BaseEntity {

    @Column(name = "disease_name")
    @Size(max = 128)
    private String diseaseName;

    @Column(name = "vaccine_name")
    @Size(max = 128)
    private String vaccineName;

    @Column(name = "age")
    private Duration age;
}
