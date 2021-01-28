package com.voronkov.vaccination.service;

import com.voronkov.vaccination.model.Vaccine;

import java.util.List;

public interface VaccineService {

    Vaccine add(Vaccine vaccine);

    List<Vaccine> getAll();

    Vaccine findByDiseaseName(String diseaseName);

    Vaccine findByVaccinationName(String diseaseName);

    void update(Vaccine vaccine);

    void delete(Long id);
}
