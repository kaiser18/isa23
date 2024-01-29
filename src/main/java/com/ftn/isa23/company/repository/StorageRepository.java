package com.ftn.isa23.company.repository;

import com.ftn.isa23.company.domain.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
    @Query(value = "SELECT s FROM Storage s WHERE s.company.id = ?1")
    List<Storage> getAllStoragesByCompany(Long companyId);

    @Query(value = "SELECT s FROM Storage s WHERE s.company.id = ?1 AND s.equipment.id = ?2")
    Storage getCompanyStorageByCompanyAndEquipment(Long companyId, Long equipmentId);
}
