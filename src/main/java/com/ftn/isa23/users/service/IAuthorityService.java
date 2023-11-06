package com.ftn.isa23.users.service;

import com.ftn.isa23.users.domain.Authority;

import java.util.List;

public interface IAuthorityService {
    List<Authority> getAllRoleAuthorities(String role);
}
