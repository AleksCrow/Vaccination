package com.voronkov.vaccination.web.controller;

import com.voronkov.vaccination.model.User;
import com.voronkov.vaccination.model.Vaccination;
import com.voronkov.vaccination.service.VaccinationService;
import com.voronkov.vaccination.web.AuthUser;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/vaccinations")
public class VaccinationController {

    private final VaccinationService vaccinationService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vaccination> getAllVaccinationsByUser(@AuthenticationPrincipal AuthUser authUser) {
        return vaccinationService.getAllVaccinesByUser(authUser.getUser());
    }

    @GetMapping(value = "/vaccinated", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vaccination> getAllVaccinatedByUser(@AuthenticationPrincipal AuthUser authUser) {
        return vaccinationService.getAllVaccinatedByUser(authUser.getUser());
    }

    @GetMapping(value = "/future", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vaccination> getAllFutureVaccinationByUserAndDate(@AuthenticationPrincipal AuthUser authUser,
                                                                  @RequestParam(value = "date", required = false)
                                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                          LocalDate date) {
        return vaccinationService.getAllFutureVaccinatesByUserAndDate(authUser.getUser(), date);
    }

    @GetMapping(value = "/missed", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vaccination> getAllMissedVaccinationsByUser(@AuthenticationPrincipal AuthUser authUser) {
        return vaccinationService.getAllMissedVaccinationsByUserId(authUser.getUser());
    }
}
