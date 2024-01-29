package com.ftn.isa23.company.service;

import com.ftn.isa23.company.domain.Appointment;

import java.util.List;

public interface IAppointmentService {
    List<Appointment> findAvailableForCompany(Long companyId);
}
