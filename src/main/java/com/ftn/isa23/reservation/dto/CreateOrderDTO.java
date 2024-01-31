package com.ftn.isa23.reservation.dto;

import java.util.List;

public class CreateOrderDTO {
    private Long appointmentId;
    private Long customerId;
    private Long companyId;
    private List<ReservationStorageDTO> reservationStorages;

    public CreateOrderDTO() {
    }

    public CreateOrderDTO(Long appointmentId, Long customerId, Long companyId, List<ReservationStorageDTO> reservationStorages) {
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.companyId = companyId;
        this.reservationStorages = reservationStorages;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public List<ReservationStorageDTO> getReservationStorages() {
        return reservationStorages;
    }

    public void setReservationStorages(List<ReservationStorageDTO> reservationStorages) {
        this.reservationStorages = reservationStorages;
    }
}
