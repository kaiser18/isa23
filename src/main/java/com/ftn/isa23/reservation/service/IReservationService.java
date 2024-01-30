package com.ftn.isa23.reservation.service;

import com.ftn.isa23.reservation.domain.Reservation;

import java.util.List;

public interface IReservationService {
    List<Reservation> findCreatedForCustomer(Long customerId);
    boolean cancel(Long reservationId);
}
