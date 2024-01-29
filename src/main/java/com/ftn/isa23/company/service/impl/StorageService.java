package com.ftn.isa23.company.service.impl;

import com.ftn.isa23.company.domain.Storage;
import com.ftn.isa23.company.repository.CompanyRepository;
import com.ftn.isa23.company.repository.StorageRepository;
import com.ftn.isa23.company.service.IEquipmentService;
import com.ftn.isa23.company.service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StorageService implements IStorageService {
    private final StorageRepository repository;
    private final IEquipmentService equipmentService;
    private final CompanyRepository companyRepository;

    @Autowired
    public StorageService(StorageRepository repository, IEquipmentService equipmentService, CompanyRepository companyRepository) {
        this.repository = repository;
        this.equipmentService = equipmentService;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Storage> getEquipmentByCompany(Long companyId) {
        return this.repository.getAllStoragesByCompany(companyId);
    }

    @Override
    public List<Storage> getAvailableEquipmentByCompany(Long companyId) {
        List<Storage> all = this.repository.getAllStoragesByCompany(companyId);

        List<Storage> available = new ArrayList<Storage>();

        for (Storage s : all) {
            if (s.getQuantity() > 0) {
                available.add(s);
            }
        }

        return available;
    }

    @Override
    public Storage getEquipmentInCompany(Long companyId, Long equipmentId) {
        Storage storage = repository.getCompanyStorageByCompanyAndEquipment(companyId, equipmentId);

        if (storage != null) {
            return storage;
        }

        return null;
    }
}
