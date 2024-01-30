package com.ftn.isa23.reservation.dto;

public class ReservationStorageDTO {
    private String equipmentName;
    private int quantity;
    private Long reservationId;

    public ReservationStorageDTO(String equipmentName, int quantity, Long reservationId) {
        this.equipmentName = equipmentName;
        this.quantity = quantity;
        this.reservationId = reservationId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }
}
