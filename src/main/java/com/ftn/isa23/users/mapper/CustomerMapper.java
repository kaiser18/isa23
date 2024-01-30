package com.ftn.isa23.users.mapper;

import com.ftn.isa23.general.mapper.AddressMapper;
import com.ftn.isa23.users.domain.Customer;
import com.ftn.isa23.users.dto.CustomerDTO;

public class CustomerMapper {
    private CustomerMapper() {}

    public static CustomerDTO mapCustomerToDTO(Customer customer) {
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getSurname(), AddressMapper.mapAddressToDTO(customer.getAddress()), customer.getPhoneNumber(), customer.getEmail(), customer.getPassword(), customer.getProfession(), customer.getProfessionInfo(), customer.getPenalties(), customer.getRole(), AuthorityMapper.mapAuthoritiesToListDTOs(customer.getAuthorities()));
    }

    public static Customer mapDTOToCustomer(CustomerDTO dto) {
        Customer customer = new Customer();

        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setSurname(dto.getSurname());
        customer.setAddress(AddressMapper.mapDTOToAddress(dto.getAddress()));
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setEmail(dto.getEmail());
        customer.setPassword(dto.getPassword());
        customer.setProfession(dto.getProfession());
        customer.setProfessionInfo(dto.getProfessionInfo());
        customer.setPenalties(dto.getPenalties());
        customer.setAuthorities(AuthorityMapper.mapDTOsToListAuthorities(dto.getAuthorities()));

        return customer;
    }
}
