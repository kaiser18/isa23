package com.ftn.isa23.users.domain;

import com.ftn.isa23.company.domain.Company;
import com.ftn.isa23.users.domain.enums.Role;

import javax.persistence.*;

@Entity
@Table(name = "companyadmins")
@DiscriminatorValue(value = Role.Values.CompanyAdmin)
public class CompanyAdmin extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Company company;

    public CompanyAdmin() {}

    public CompanyAdmin(Long id, Company company) {
        this.id = id;
        this.company = company;
    }

    public CompanyAdmin(Long id, Company company, boolean enabled, String verificationCode) {
        this.id = id;
        this.company = company;
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
