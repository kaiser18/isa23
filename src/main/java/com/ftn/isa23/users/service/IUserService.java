package com.ftn.isa23.users.service;

import com.ftn.isa23.users.domain.User;

public interface IUserService {
    User findByEmail(String email);
}
