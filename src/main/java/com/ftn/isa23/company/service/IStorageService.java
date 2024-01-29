package com.ftn.isa23.company.service;

import com.ftn.isa23.company.domain.Storage;

import java.util.List;

public interface IStorageService {
    List<Storage> getEquipmentByCompany(Long companyId);
    List<Storage> getAvailableEquipmentByCompany(Long companyId);
    Storage getEquipmentInCompany(Long companyId, Long equipmentId);
}
