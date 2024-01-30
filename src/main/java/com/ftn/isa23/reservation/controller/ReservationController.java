package com.ftn.isa23.reservation.controller;

import com.ftn.isa23.reservation.domain.Reservation;
import com.ftn.isa23.reservation.service.impl.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/reservation")
public class ReservationController {
    private final ReservationService service;

    @Autowired
    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping(value = "/getCreatedForCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Reservation> getCreatedForCustomer(@RequestParam("customerId") Long customerId) {
        return service.findCreatedForCustomer(customerId);
    }
}
