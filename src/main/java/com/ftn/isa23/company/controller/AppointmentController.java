package com.ftn.isa23.company.controller;

import com.ftn.isa23.company.domain.Appointment;
import com.ftn.isa23.company.service.impl.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/appointment")
public class AppointmentController {
    private final AppointmentService service;

    @Autowired
    AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @GetMapping(value = "/getAvailableByCompany", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Appointment> getAvailableForCompany(@RequestParam("companyId") Long companyId) throws Exception {
        return service.findAvailableForCompany(companyId);
    }
}
