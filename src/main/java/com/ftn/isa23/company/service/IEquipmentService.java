package com.ftn.isa23.company.service;

import com.ftn.isa23.company.domain.Equipment;

import java.util.List;

public interface IEquipmentService {
    List<Equipment> findAll();
    Equipment findById(Long id);
}
