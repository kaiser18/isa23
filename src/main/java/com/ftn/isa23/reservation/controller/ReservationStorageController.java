package com.ftn.isa23.reservation.controller;

import com.ftn.isa23.reservation.dto.ReservationStorageDTO;
import com.ftn.isa23.reservation.mapper.ReservationStorageMapper;
import com.ftn.isa23.reservation.service.impl.ReservationStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/reservationStorage", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationStorageController {
    private final ReservationStorageService service;

    @Autowired
    public ReservationStorageController(ReservationStorageService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping(value = "/getEquipmentByReservation", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<ReservationStorageDTO>  getEquipmentByReservation(@RequestParam("r") Long reservationId) {
        return ReservationStorageMapper.mapReservationStoragesToListDTOs(service.getEquipmentByReservation(reservationId));
    }
}
