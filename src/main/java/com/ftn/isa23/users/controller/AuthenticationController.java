package com.ftn.isa23.users.controller;

import com.ftn.isa23.security.auth.JwtAuthenticationRequest;
import com.ftn.isa23.security.auth.TokenUtils;
import com.ftn.isa23.security.domain.UserTokenState;
import com.ftn.isa23.users.domain.User;
import com.ftn.isa23.users.dto.AuthenticatedUserDTO;
import com.ftn.isa23.users.service.impl.CustomerService;
import com.ftn.isa23.users.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        // Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
        // kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token za tog korisnika
        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();
        AuthenticatedUserDTO dto = new AuthenticatedUserDTO(user.getId(), user.getRole(), user.getUsername(), new UserTokenState(jwt, expiresIn));

        // Vrati token kao odgovor na uspesnu autentifikaciju
        return ResponseEntity.ok(dto);
    }
}
