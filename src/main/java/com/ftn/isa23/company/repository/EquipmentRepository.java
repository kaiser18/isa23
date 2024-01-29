package com.ftn.isa23.company.repository;

import com.ftn.isa23.company.domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    @Query(value = "SELECT e FROM Equipment e WHERE lower(e.name) = ?1")
    List<Equipment> findByName(String name);
}
