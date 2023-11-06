package com.ftn.isa23.users.repository;

import com.ftn.isa23.users.domain.CompanyAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyAdminRepository extends JpaRepository<CompanyAdmin, Long> {
}
