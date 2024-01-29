package com.ftn.isa23.users.mapper;

import com.ftn.isa23.users.domain.CompanyAdmin;
import com.ftn.isa23.users.dto.CompanyAdminDTO;

public class CompanyAdminMapper {
    private CompanyAdminMapper() {}

    public static CompanyAdminDTO mapCompanyAdminToDTO(CompanyAdmin cadmin) {
        return new CompanyAdminDTO(cadmin.getId(), cadmin.getName(), cadmin.getSurname());
    }
}
