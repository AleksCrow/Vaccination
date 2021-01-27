package com.voronkov.vaccination.service.Impl;

import com.voronkov.vaccination.model.Vaccine;
import com.voronkov.vaccination.repository.VaccineRepository;
import com.voronkov.vaccination.service.VaccineService;
import com.voronkov.vaccination.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VaccineServiceImpl implements VaccineService {

    private final VaccineRepository vaccineRepository;

    public VaccineServiceImpl(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    @Override
    public Vaccine add(Vaccine vaccine) {
        log.info("add {}", vaccine);
        ValidationUtil.checkNew(vaccine);
        vaccine = vaccineRepository.save(vaccine);
        return vaccine;
    }

    @Override
    public List<Vaccine> getAll() {
        return vaccineRepository.findAll();
    }

    @Override
    public Vaccine findByDiseaseName(String diseaseName) {
        return vaccineRepository.findByDiseaseName(diseaseName).orElseThrow(
                () -> new UsernameNotFoundException("Disease or vaccination with name '" + diseaseName + "' was not found"));
    }

    @Override
    public Vaccine findByVaccinationName(String vaccineName) {
        return vaccineRepository.findByVaccineName(vaccineName).orElseThrow(
                () -> new UsernameNotFoundException("Disease or vaccine with name '" + vaccineName + "' was not found"));
    }

    @Override
    public void update(Vaccine vaccine) {
        vaccineRepository.save(vaccine);
        log.info("vaccine info: {} successfully updated", vaccine);
    }

    @Override
    public void delete(Long id) {
        vaccineRepository.deleteById(id);
        log.info("vaccine with id: {} successfully deleted", id);
    }
}
