package com.ftn.isa23.users.domain;

import com.ftn.isa23.users.domain.enums.Role;

import javax.persistence.*;

@Entity
@Table(name = "customers")
@DiscriminatorValue(value = Role.Values.Customer)
public class Customer extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String profession;
    private String professionInfo;
    private int penalties;
    private boolean enabled;
    private String verificationCode;

    public Customer() {
    }

    public Customer(Long id, String profession, String professionInfo, int penalties) {
        this.id = id;
        this.profession = profession;
        this.professionInfo = professionInfo;
        this.penalties = penalties;
    }

    public Customer(Long id, boolean enabled, String verificationCode) {
        this.id = id;
        this.enabled = enabled;
        this.verificationCode = verificationCode;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProfessionInfo() {
        return professionInfo;
    }

    public void setProfessionInfo(String professionInfo) {
        this.professionInfo = professionInfo;
    }

    public int getPenalties() {
        return penalties;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verification_code) {
        this.verificationCode = verification_code;
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
