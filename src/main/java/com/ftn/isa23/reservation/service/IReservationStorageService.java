package com.ftn.isa23.reservation.service;

import com.ftn.isa23.reservation.domain.ReservationStorage;

import java.util.List;

public interface IReservationStorageService {
    List<ReservationStorage> getEquipmentByReservation(Long reservationId);
}
