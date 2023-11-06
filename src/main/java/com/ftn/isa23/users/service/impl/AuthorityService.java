package com.ftn.isa23.users.service.impl;

import com.ftn.isa23.users.domain.Authority;
import com.ftn.isa23.users.repository.AuthorityRepository;
import com.ftn.isa23.users.service.IAuthorityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorityService implements IAuthorityService {
    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<Authority> getAllRoleAuthorities(String role) {
        List<Authority> allAuthorities = authorityRepository.findAll();
        List<Authority> roleAuthorities = new ArrayList<>();

        for (Authority a : allAuthorities) {
            if(a.getName().equals(role)) {
                roleAuthorities.add(a);
            }
        }

        return roleAuthorities;
    }
}
