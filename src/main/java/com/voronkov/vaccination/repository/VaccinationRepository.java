package com.voronkov.vaccination.repository;

import com.voronkov.vaccination.model.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {

    @Query("SELECT v FROM Vaccination v WHERE v.user.id = :userId ORDER BY v.vaccinationDate, " +
            "v.plannedVaccinationDate, v.vaccineName")
    List<Vaccination> findAllByUserId(long userId);

    @Query("SELECT v FROM Vaccination v WHERE v.user.id = :userId and v.plannedVaccinationDate is null " +
            "ORDER BY v.vaccinationDate, v.vaccineName")
    List<Vaccination> findAllVaccinatedByUserId(long userId);

    @Query("SELECT v FROM Vaccination v WHERE v.user.id = :userId " +
            "and v.vaccinationDate is null and v.plannedVaccinationDate > CURRENT_DATE " +
            "ORDER BY v.plannedVaccinationDate, v.vaccineName")
    List<Vaccination> findAllFutureVaccinationsByUserId(long userId);

    @Query("SELECT v FROM Vaccination v WHERE v.user.id = :userId " +
            "and v.vaccinationDate is null and v.plannedVaccinationDate < CURRENT_DATE " +
            "ORDER BY v.plannedVaccinationDate, v.vaccineName")
    List<Vaccination> findAllMissedVaccinationsByUserId(long userId);
}
