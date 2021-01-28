package com.voronkov.vaccination.web.controller;

import com.voronkov.vaccination.model.Vaccination;
import com.voronkov.vaccination.model.Vaccine;
import com.voronkov.vaccination.service.VaccinationService;
import com.voronkov.vaccination.web.AuthUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = "/api/vaccinations")
public class VaccinationController {

    private final VaccinationService vaccinationService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vaccination> getAllVaccinationsByUser(@AuthenticationPrincipal AuthUser authUser) {
        return vaccinationService.getAllVaccinesByUser(authUser.getUser());
    }

    @GetMapping(value = "/vaccinated", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vaccination> getAllVaccinatedByUser(@AuthenticationPrincipal AuthUser authUser) {
        return vaccinationService.getAllVaccinatedByUser(authUser.id());
    }

    @GetMapping(value = "/future", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vaccination> getAllFutureVaccinationByUserAndDate(@AuthenticationPrincipal AuthUser authUser,
                                                                  @RequestParam(value = "date", required = false)
                                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                          LocalDate date) {
        return vaccinationService.getAllFutureVaccinatesByUserAndDate(date, authUser.id());
    }

    @GetMapping(value = "/missed", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vaccination> getAllMissedVaccinationsByUser(@AuthenticationPrincipal AuthUser authUser) {
        return vaccinationService.getAllMissedVaccinationsByUserId(authUser.id());
    }

    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vaccination> getAllByDiseaseNameAndByUser(@RequestParam(value = "name") String diseaseName, @AuthenticationPrincipal AuthUser authUser) {
        return vaccinationService.getAllByDiseaseName(diseaseName, authUser.id());
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Vaccination> addVaccination(@Valid @RequestBody Vaccine vaccine, @AuthenticationPrincipal AuthUser authUser) {
        Vaccination created = vaccinationService.save(vaccine, authUser.id());

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Vaccination vaccination, @PathVariable("id") long id) {
        Vaccination oldVaccination = vaccinationService.getById(id);
        vaccination.setId(id);
        if (vaccination.getVaccineName() == null) {
            vaccination.setVaccineName(oldVaccination.getVaccineName());
        }
        if (vaccination.getDiseaseName() == null) {
            vaccination.setDiseaseName(oldVaccination.getDiseaseName());
        }
        if (vaccination.getVaccinationDate() == null) {
            vaccination.setVaccinationDate(oldVaccination.getVaccinationDate());
        }
        vaccination.setPlannedVaccinationDate(oldVaccination.getPlannedVaccinationDate());
        vaccination.setVaccine(oldVaccination.getVaccine());
        vaccination.setUser(oldVaccination.getUser());

        vaccinationService.update(vaccination);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        vaccinationService.delete(id);
    }
}
