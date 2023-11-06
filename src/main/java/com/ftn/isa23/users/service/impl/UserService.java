package com.ftn.isa23.users.service.impl;

import com.ftn.isa23.users.domain.User;
import com.ftn.isa23.users.repository.UserRepository;
import com.ftn.isa23.users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
