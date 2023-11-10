package com.ftn.isa23.users.controller;

import com.ftn.isa23.users.domain.Customer;
import com.ftn.isa23.users.dto.CustomerDTO;
import com.ftn.isa23.users.mapper.CustomerMapper;
import com.ftn.isa23.users.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin
@RequestMapping(value = "/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerCustomer(@RequestBody CustomerDTO dto ) throws ParseException {
        dto.setRole("Customer");

        try {
            Customer c = customerService.registerCustomer(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/activation", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> activation(@RequestBody String token) {
        Customer c = customerService.enableCustomer(token);

        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    @GetMapping(value="/getLoggedCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    CustomerDTO getLoggedInUser() {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
        String email = ((Customer)user).getEmail();
        return CustomerMapper.mapCustomerToDTO(customerService.findByEmail(email));
    }

}
