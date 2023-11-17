package com.ftn.isa23.company.dto;

import com.ftn.isa23.general.dto.AddressDTO;

public class CompanyDTO {
    private Long id;
    private String name;
    private AddressDTO address;
    private String description;
    private double averageGrade;

    public CompanyDTO() {
    }

    public CompanyDTO(Long id, String name, AddressDTO address, String description, double averageGrade) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.averageGrade = averageGrade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }
}
