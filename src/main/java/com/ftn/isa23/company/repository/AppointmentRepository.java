package com.ftn.isa23.company.repository;

import com.ftn.isa23.company.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query(value = "SELECT a FROM Appointment a WHERE a.company.id = ?1 AND a.status = 0 AND a.timePeriod.startTime > current_date")
    List<Appointment> findAvailableForCompany(Long companyId);
}
