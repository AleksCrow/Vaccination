package com.voronkov.vaccination.service.Impl;

import com.voronkov.vaccination.model.User;
import com.voronkov.vaccination.model.Vaccination;
import com.voronkov.vaccination.model.Vaccine;
import com.voronkov.vaccination.repository.VaccinationRepository;
import com.voronkov.vaccination.repository.VaccineRepository;
import com.voronkov.vaccination.service.VaccinationService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

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
    public Vaccination getById(Long id) {
        return vaccinationRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("Vaccination with id: " + id + " not found"));
    }

    @Override
    public void update(Vaccination vaccination) {
        vaccinationRepository.save(vaccination);
        log.info("vaccinate with id: {} successfully updated", vaccination.getId());
    }

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
                                v.getVaccineName(),
                                v.getDiseaseName(),
                                null,
                                user.getBirthDate().plus(Period.ofDays(v.getAge())))));

            result = vaccinationRepository.findAllByUserId(user.getId());
        }

        log.info("Vaccinations for user with id: {} successfully found", user.getId());
        return result;
    }

    @Override
    public List<Vaccination> getAllVaccinatedByUser(Long userId) {
        log.info("Vaccinated for user with id: {} successfully found", userId);
        return vaccinationRepository.findAllVaccinatedByUserId(userId);
    }

    @Override
    public List<Vaccination> getAllFutureVaccinatesByUserAndDate(LocalDate date, Long userId) {
        List<Vaccination> futureVaccinations = vaccinationRepository.findAllFutureVaccinationsByUserId(userId);
        List<Vaccination> result;

        if (date == null) {
            result = futureVaccinations;
        } else {
            result = futureVaccinations.stream()
                    .filter(v -> (v.getPlannedVaccinationDate().isBefore(date) ||
                            v.getPlannedVaccinationDate().isEqual(date)))
                    .collect(Collectors.toList());
        }
        log.info("Future vaccinates for user with id: {} for: {} successfully found", userId, date);
        return result;
    }

    @Override
    public List<Vaccination> getAllMissedVaccinationsByUserId(Long userId) {
        log.info("missed vaccinates for user with id: {} successfully found", userId);
        return vaccinationRepository.findAllMissedVaccinationsByUserId(userId);
    }

    @Override
    public Vaccination save(Vaccine vaccine, Long userId) {
        List<Vaccination> resultList = vaccinationRepository.findAllByUserId(userId);
        Vaccination result = resultList.stream()
                            .filter(v -> (v.getPlannedVaccinationDate() != null &&
                                    v.getDiseaseName().equals(vaccine.getDiseaseName())))
                            .findFirst()
                            .orElseThrow(() -> new UsernameNotFoundException("Vaccine with name: " + vaccine.getDiseaseName() + " not found"));
        result.setVaccinationDate(LocalDate.now());
        result.setPlannedVaccinationDate(null);

        vaccinationRepository.save(result);

        log.info("vaccination from: {} successfully made", result.getDiseaseName());

        return result;
    }

    @Override
    public void delete(Long id) {
        vaccinationRepository.deleteById(id);
        log.info("vaccination with id: {} successfully deleted", id);
    }

    @Override
    public List<Vaccination> getAllByDiseaseName(String name, Long userId) {
        log.info("vaccinations with name: {} successfully find", name);
        return vaccinationRepository.findAllByUserId(userId)
                .stream()
                .filter(v -> v.getDiseaseName().contains(name))
                .collect(Collectors.toList());
    }
}
