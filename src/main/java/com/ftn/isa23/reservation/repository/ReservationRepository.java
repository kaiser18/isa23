package com.ftn.isa23.reservation.repository;

import com.ftn.isa23.reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(value = "SELECT r FROM Reservation r WHERE r.customer.id = ?1 AND r.status = 0 AND r.appointment.timePeriod.startTime > current_date")
    List<Reservation> findCreatedForCustomer(Long customerId);
}
