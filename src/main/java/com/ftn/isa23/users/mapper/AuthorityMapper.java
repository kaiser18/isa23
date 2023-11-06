package com.ftn.isa23.users.mapper;

import com.ftn.isa23.users.domain.Authority;
import com.ftn.isa23.users.dto.AuthorityDTO;

import java.util.ArrayList;
import java.util.List;

public class AuthorityMapper {
    private AuthorityMapper() {}

    public static AuthorityDTO mapAuthorityToDTO(Authority authority) {
        return new AuthorityDTO(authority.getId(), authority.getName());
    }

    public static Authority mapDTOToAuthority(AuthorityDTO dto) {
        Authority authority = new Authority();
        authority.setId(dto.getId());
        authority.setName(dto.getName());

        return authority;
    }

    public static List<AuthorityDTO> mapAuthoritiesToListDTOs(List<Authority> authorities) {
        List<AuthorityDTO> dtos = new ArrayList<>();

        for(Authority a : authorities) {
            dtos.add(mapAuthorityToDTO(a));
        }

        return dtos;
    }

    public static List<Authority> mapDTOsToListAuthorities(List<AuthorityDTO> dtos) {
        List<Authority> authorities = new ArrayList<>();

        for(AuthorityDTO d : dtos) {
            authorities.add(mapDTOToAuthority(d));
        }

        return authorities;
    }
}
