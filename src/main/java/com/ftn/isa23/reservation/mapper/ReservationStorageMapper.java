package com.ftn.isa23.reservation.mapper;

import com.ftn.isa23.reservation.domain.ReservationStorage;
import com.ftn.isa23.reservation.dto.ReservationStorageDTO;

import java.util.ArrayList;
import java.util.List;

public class ReservationStorageMapper {
    public ReservationStorageMapper() {
    }

    public static ReservationStorageDTO mapReservationStorageToDTO(ReservationStorage reservationStorage) {
        return new ReservationStorageDTO(reservationStorage.getEquipment().getId(), reservationStorage.getEquipment().getName(), reservationStorage.getQuantity());
    }

    public static List<ReservationStorageDTO> mapReservationStoragesToListDTOs (List<ReservationStorage> reservationStorages) {
        List<ReservationStorageDTO> dtos = new ArrayList<ReservationStorageDTO>();

        for (ReservationStorage rs : reservationStorages) {
            dtos.add(mapReservationStorageToDTO(rs));
        }

        return dtos;
    }
}
