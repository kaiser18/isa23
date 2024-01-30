package com.ftn.isa23.reservation.service.impl;

import com.ftn.isa23.reservation.domain.Reservation;
import com.ftn.isa23.reservation.repository.ReservationRepository;
import com.ftn.isa23.reservation.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService implements IReservationService {
    private final ReservationRepository repository;

    @Autowired
    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Reservation> findCreatedForCustomer(Long customerId) {
        return this.repository.findCreatedForCustomer(customerId);
    }
}
