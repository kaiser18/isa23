package com.ftn.isa23.users.repository;

import com.ftn.isa23.users.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
