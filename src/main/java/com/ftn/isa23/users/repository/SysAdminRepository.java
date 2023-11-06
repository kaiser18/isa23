package com.ftn.isa23.users.repository;

import com.ftn.isa23.users.domain.SysAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysAdminRepository extends JpaRepository<SysAdmin, Long> {
}
