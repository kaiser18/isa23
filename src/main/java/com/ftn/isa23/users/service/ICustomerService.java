package com.ftn.isa23.users.service;

import com.ftn.isa23.users.domain.Customer;
import com.ftn.isa23.users.dto.CustomerDTO;

public interface ICustomerService {
    Customer registerCustomer(CustomerDTO customerDTO) throws Exception;
    Customer enableCustomer(String token);
}
