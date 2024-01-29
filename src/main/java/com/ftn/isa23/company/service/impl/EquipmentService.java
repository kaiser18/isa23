package com.ftn.isa23.company.service.impl;

import com.ftn.isa23.company.domain.Equipment;
import com.ftn.isa23.company.repository.EquipmentRepository;
import com.ftn.isa23.company.service.IEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService implements IEquipmentService {
    private final EquipmentRepository repository;

    @Autowired
    public EquipmentService(EquipmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Equipment> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Equipment findById(Long id) {
        return this.repository.findById(id).orElse(null);
    }
}
