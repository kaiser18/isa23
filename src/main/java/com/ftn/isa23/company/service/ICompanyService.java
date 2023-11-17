package com.ftn.isa23.company.service;

import com.ftn.isa23.company.domain.Company;
import com.ftn.isa23.company.dto.CompanyDTO;

import java.util.List;

public interface ICompanyService {
    List<Company> findAll();
    Company getById(Long id);
    List<CompanyDTO> findByName(String name);
    List<CompanyDTO> findByAddress(String address);
}
