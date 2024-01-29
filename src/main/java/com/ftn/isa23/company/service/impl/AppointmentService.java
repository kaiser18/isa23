package com.ftn.isa23.company.service.impl;

import com.ftn.isa23.company.domain.Appointment;
import com.ftn.isa23.company.repository.AppointmentRepository;
import com.ftn.isa23.company.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService implements IAppointmentService {
    private final AppointmentRepository repository;

    @Autowired
    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Appointment> findAvailableForCompany(Long companyId) {
        return repository.findAvailableForCompany(companyId);
    }
}
