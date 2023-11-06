package com.ftn.isa23.general.mapper;

import com.ftn.isa23.general.domain.Address;
import com.ftn.isa23.general.dto.AddressDTO;

public class AddressMapper {
    private AddressMapper() {}

    public static AddressDTO mapAddressToDTO(Address address) {
        return new AddressDTO(address.getId(), address.getStreet(), address.getNumber(), address.getCity(), address.getCountry());
    }

    public static Address mapDTOToAddress(AddressDTO dto) {
        Address address = new Address();
        address.setId(dto.getId());
        address.setStreet(dto.getStreet());
        address.setNumber(dto.getNumber());
        address.setCity(dto.getCity());
        address.setCountry(dto.getCountry());

        return address;
    }
}
