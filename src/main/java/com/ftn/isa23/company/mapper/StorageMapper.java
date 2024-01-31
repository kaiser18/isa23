package com.ftn.isa23.company.mapper;

import com.ftn.isa23.company.domain.Storage;
import com.ftn.isa23.company.dto.StorageDTO;

import java.util.ArrayList;
import java.util.List;

public class StorageMapper {
    private StorageMapper() {}

    public static StorageDTO mapStorageToDTO(Storage storage) {
        return new StorageDTO(storage.getEquipment().getId(), storage.getEquipment().getName(), storage.getQuantity());
    }

    public static List<StorageDTO> mapStoragesToListDTOs(List<Storage> storages) {
        List<StorageDTO> dtos = new ArrayList<StorageDTO>();

        for (Storage s : storages) {
            dtos.add(mapStorageToDTO(s));
        }

        return  dtos;
    }
}
