package com.ftn.isa23.users.domain;

import com.ftn.isa23.users.domain.enums.Role;

import javax.persistence.*;

@Entity
@Table(name = "sysadmins")
@DiscriminatorValue(value = Role.Values.SysAdmin)
public class SysAdmin extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public SysAdmin() {}

    public SysAdmin(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
