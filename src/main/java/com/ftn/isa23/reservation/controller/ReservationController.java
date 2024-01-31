package com.ftn.isa23.reservation.controller;

import com.ftn.isa23.reservation.domain.Reservation;
import com.ftn.isa23.reservation.dto.CreateReservationDTO;
import com.ftn.isa23.reservation.service.impl.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping(value = "/cancel", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<?> cancel(@RequestBody Long reservationId) throws Exception {
        if (service.cancel(reservationId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Reservation create(@RequestBody CreateReservationDTO dto) throws Exception{
        Reservation reservation = new Reservation();
        try {
            reservation = service.create(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservation;
    }
}
