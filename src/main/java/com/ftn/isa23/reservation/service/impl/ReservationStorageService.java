package com.ftn.isa23.reservation.service.impl;

import com.ftn.isa23.reservation.domain.ReservationStorage;
import com.ftn.isa23.reservation.repository.ReservationStorageRepository;
import com.ftn.isa23.reservation.service.IReservationStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationStorageService implements IReservationStorageService {
    private final ReservationStorageRepository repository;

    @Autowired
    public ReservationStorageService(ReservationStorageRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ReservationStorage> getEquipmentByReservation(Long reservationId) {
        return this.repository.getAllStoragesByReservation(reservationId);
    }
}
