package com.ftn.isa23.company.mapper;

import com.ftn.isa23.company.domain.Storage;
import com.ftn.isa23.company.dto.StorageDTO;

import java.util.ArrayList;
import java.util.List;

public class StorageMapper {
    private StorageMapper() {}

    public static StorageDTO mapStorageToDTO(Storage storage) {
        return new StorageDTO(storage.getEquipment().getId(), storage.getEquipment().getName(), storage.getQuantity(), storage.getCompany().getId());
    }

    public static List<StorageDTO> mapStoragesToListDTOs(List<Storage> companies) {
        List<StorageDTO> dtos = new ArrayList<StorageDTO>();

        for (Storage c : companies) {
            dtos.add(mapStorageToDTO(c));
        }

        return  dtos;
    }
}
