package com.ftn.isa23.company.mapper;

import com.ftn.isa23.company.domain.Company;
import com.ftn.isa23.company.dto.CompanyDTO;
import com.ftn.isa23.general.mapper.AddressMapper;

import java.util.ArrayList;
import java.util.List;

public class CompanyMapper {
    private CompanyMapper() {}

    public static CompanyDTO mapCompanyToDTO(Company company) {
        return new CompanyDTO(company.getId(), company.getName(), AddressMapper.mapAddressToDTO(company.getAddress()), company.getDescription(), company.getAverageGrade());
    }

    public static List<CompanyDTO> mapCompaniesToListDTOs(List<Company> companies) {
        List<CompanyDTO> dtos = new ArrayList<CompanyDTO>();

        for (Company c : companies) {
            dtos.add(mapCompanyToDTO(c));
        }

        return  dtos;
    }
}
