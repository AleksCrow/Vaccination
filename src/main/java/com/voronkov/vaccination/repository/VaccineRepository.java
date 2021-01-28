package com.voronkov.vaccination.repository;

import com.voronkov.vaccination.model.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    @Query("SELECT v FROM Vaccine v WHERE v.diseaseName = LOWER(:name)")
    Optional<Vaccine> findByDiseaseName(String name);

    @Query("SELECT v FROM Vaccine v WHERE v.vaccineName = LOWER(:name)")
    Optional<Vaccine> findByVaccineName(String name);

}
