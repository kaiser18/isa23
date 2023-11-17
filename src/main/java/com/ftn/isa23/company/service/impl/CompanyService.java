package com.ftn.isa23.company.service.impl;

import com.ftn.isa23.company.domain.Company;
import com.ftn.isa23.company.dto.CompanyDTO;
import com.ftn.isa23.company.mapper.CompanyMapper;
import com.ftn.isa23.company.repository.CompanyRepository;
import com.ftn.isa23.company.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService implements ICompanyService {
    private final CompanyRepository repository;

    @Autowired
    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Company> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Company getById(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public List<CompanyDTO> findByName(String name) {
        List<Company> companies = (List<Company>) repository.findAll();
        List<CompanyDTO> dtos = new ArrayList<>();
        if(companies.size()!=0){
            for(Company c : companies){
                dtos.add(CompanyMapper.mapCompanyToDTO(c));
            }
        }
        if(name.trim().equals("")){
            return dtos;
        }
        return  dtos.stream()
                .filter(c -> c.getName().toLowerCase().contains(name.toLowerCase().trim()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CompanyDTO> findByAddress(String address) {
        List<Company> companies = (List<Company>) repository.findAll();
        List<CompanyDTO> dtos = new ArrayList<>();
        if(companies.size()!=0){
            for(Company c : companies){
                dtos.add(CompanyMapper.mapCompanyToDTO(c));
            }
        }
        if(address.trim().equals("")){
            return dtos;
        }

        return dtos.stream()
                .filter(c -> c.getAddress().getStreet().toLowerCase().contains(address.toLowerCase().trim())
                        || c.getAddress().getCity().getName().toLowerCase().contains(address.toLowerCase().trim())
                        || c.getAddress().getCountry().getName().toLowerCase().contains(address.toLowerCase().trim()))
                .collect(Collectors.toList());
    }
}
