package com.voronkov.vaccination.service;

import com.voronkov.vaccination.model.User;
import com.voronkov.vaccination.model.Vaccination;
import com.voronkov.vaccination.model.Vaccine;

import java.time.LocalDate;
import java.util.List;

public interface VaccinationService {

    Vaccination getById(Long id);

    void update(Vaccination vaccination);

    List<Vaccination> getAllByDiseaseName(String name, Long userId);

    List<Vaccination> getAllVaccinesByUser(User user);

    List<Vaccination> getAllVaccinatedByUser(Long userId);

    List<Vaccination> getAllFutureVaccinatesByUserAndDate(LocalDate date, Long userId);

    List<Vaccination> getAllMissedVaccinationsByUserId(Long userId);

    Vaccination save(Vaccine vaccine, Long userId);

    void delete(Long id);
}
