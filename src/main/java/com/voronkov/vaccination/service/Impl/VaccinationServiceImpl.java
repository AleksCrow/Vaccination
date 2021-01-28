package com.voronkov.vaccination.service.Impl;

import com.voronkov.vaccination.model.User;
import com.voronkov.vaccination.model.Vaccination;
import com.voronkov.vaccination.model.Vaccine;
import com.voronkov.vaccination.repository.VaccinationRepository;
import com.voronkov.vaccination.repository.VaccineRepository;
import com.voronkov.vaccination.service.VaccinationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class VaccinationServiceImpl implements VaccinationService {

    private final VaccinationRepository vaccinationRepository;
    private final VaccineRepository vaccineRepository;

    @Override
    public List<Vaccination> getAllVaccinesByUser(User user) {
        List<Vaccination> result = vaccinationRepository.findAllByUserId(user.getId());
        List<Vaccine> allVaccines = vaccineRepository.findAll();

        if (result.size() < allVaccines.size()) {
            List<Long> vaccinesIdList = result.stream().map(v -> v.getVaccine().getId()).collect(Collectors.toList());
            allVaccines.stream()
                    .filter(v -> !vaccinesIdList.contains(v.getId()))
                    .forEach(v -> vaccinationRepository.save(new Vaccination(
                                user,
                                v,
                                null,
                                user.getBirthDate().plus(Period.ofDays(v.getAge())))));

            result = vaccinationRepository.findAllByUserId(user.getId());
        }

        log.info("Vaccinations for user with id: {} found", user.getId());
        return result;
    }

    @Override
    public List<Vaccination> getAllVaccinatedByUser(User user) {
        log.info("Vaccinated for user with id: {} found", user.getId());
        return vaccinationRepository.findAllVaccinatedByUserId(user.getId());
    }

    @Override
    public List<Vaccination> getAllFutureVaccinatesByUserAndDate(User user, LocalDate date) {
        List<Vaccination> futureVaccinations = vaccinationRepository.findAllFutureVaccinationsByUserId(user.getId());
        List<Vaccination> result;

        if (date == null) {
            result = futureVaccinations;
        } else {
            result = futureVaccinations.stream()
                    .filter(v -> (v.getPlannedVaccinationDate().isAfter(date)
                            || v.getPlannedVaccinationDate().isEqual(date)))
                    .collect(Collectors.toList());
        }
        log.info("Future vaccinates for user with id: {} for: {} found", user.getId(), date);
        return result;
    }

    @Override
    public List<Vaccination> getAllMissedVaccinationsByUserId(User user) {
        log.info("Missed vaccinates for user with id: {} found", user.getId());
        return vaccinationRepository.findAllMissedVaccinationsByUserId(user.getId());
    }
}
