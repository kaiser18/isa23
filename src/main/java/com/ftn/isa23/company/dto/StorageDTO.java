package com.ftn.isa23.company.dto;

import com.ftn.isa23.company.domain.Company;

public class StorageDTO {
    private Long equipmentId;
    private String equipmentName;
    private int quantity;

    public StorageDTO(Long equipmentId, String equipmentName, int quantity) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.quantity = quantity;
    }

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
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
}
