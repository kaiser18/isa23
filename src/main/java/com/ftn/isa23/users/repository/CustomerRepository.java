package com.ftn.isa23.users.repository;

import com.ftn.isa23.users.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT c FROM Customer c WHERE c.verificationCode=?1")
    Customer findByVerificationCode(String token);

    @Query(value = "SELECT c FROM Customer c WHERE c.email=?1")
    Customer findByEmail(String email);
}
