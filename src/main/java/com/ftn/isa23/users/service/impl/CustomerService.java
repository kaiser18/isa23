package com.ftn.isa23.users.service.impl;

import com.ftn.isa23.general.domain.Address;
import com.ftn.isa23.general.mapper.AddressMapper;
import com.ftn.isa23.general.repository.AddressRepository;
import com.ftn.isa23.users.domain.Customer;
import com.ftn.isa23.users.dto.CustomerDTO;
import com.ftn.isa23.users.mapper.CustomerMapper;
import com.ftn.isa23.users.repository.CustomerRepository;
import com.ftn.isa23.users.repository.UserRepository;
import com.ftn.isa23.users.service.ICustomerService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    private final AuthorityService authorityService;
    private final AddressRepository addressRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final EmailService emailService;

    @Autowired
    public CustomerService(AuthorityService authorityService, AddressRepository addressRepository, BCryptPasswordEncoder passwordEncoder, UserRepository userRepository, CustomerRepository customerRepository, EmailService emailService) {
        this.authorityService = authorityService;
        this.addressRepository = addressRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.emailService = emailService;
    }

    @Transactional
    public Customer save(Customer c) {
        return userRepository.save(c);
    }

    @Override
    public Customer registerCustomer(CustomerDTO customerDTO) throws Exception {
        if(userRepository.findByEmail(customerDTO.getEmail()) == null) {
            String role = "ROLE_CUSTOMER";

            String password = passwordEncoder.encode(customerDTO.getPassword());
            customerDTO.setPassword(password);

            Address a = AddressMapper.mapDTOToAddress(customerDTO.getAddress());
            addressRepository.save(a);

            Customer customer = CustomerMapper.mapDTOToCustomer(customerDTO);
            customer.setAuthorities(authorityService.getAllRoleAuthorities(role));
            customer.setAddress(a);
            customer.setVerificationCode(RandomString.make(64));
            customer.setEnabled(false);

            Optional<Customer> saved = Optional.of(save(customer));

            saved.ifPresent(u -> {
                try {
                    emailService.sendConfirmationEmail(saved.get());
                } catch (MessagingException | IOException e) {
                    e.printStackTrace();
                }
            });

            return saved.get();
        }

        throw new Exception();
    }

    @Override
    public Customer enableCustomer(String token) {
        Customer customer = findByVerificationCode(token);

        if (!customer.getEnabled()) {
            customer.setEnabled(true);
            customer.setVerificationCode(null);
            userRepository.save(customer);
            return customer;
        }

        return null;
    }

    public Customer findByVerificationCode(String token) {
        return customerRepository.findByVerificationCode(token);
    }

    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Customer findById(Long id) {
        return (Customer) userRepository.findById(id).get();
    }
}
