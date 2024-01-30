package com.ftn.isa23.reservation.repository;

import com.ftn.isa23.reservation.domain.ReservationStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationStorageRepository extends JpaRepository<ReservationStorage, Long> {
    @Query(value = "SELECT rs FROM ReservationStorage rs WHERE rs.reservation.id = ?1")
    List<ReservationStorage> getAllStoragesByReservation(Long reservationId);
}
