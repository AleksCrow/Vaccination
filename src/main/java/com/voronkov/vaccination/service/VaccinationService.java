package com.voronkov.vaccination.service;

import com.voronkov.vaccination.model.User;
import com.voronkov.vaccination.model.Vaccination;

import java.time.LocalDate;
import java.util.List;

public interface VaccinationService {

    List<Vaccination> getAllVaccinesByUser(User user);

    List<Vaccination> getAllVaccinatedByUser(User user);

    List<Vaccination> getAllFutureVaccinatesByUserAndDate(User user, LocalDate date);

    List<Vaccination> getAllMissedVaccinationsByUserId(User user);
}
