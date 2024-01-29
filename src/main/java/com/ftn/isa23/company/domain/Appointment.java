package com.ftn.isa23.company.domain;

import com.ftn.isa23.company.domain.enums.AppointmentStatus;
import com.ftn.isa23.users.domain.CompanyAdmin;
import com.ftn.isa23.users.domain.Customer;

import javax.persistence.*;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "startTime", column = @Column(name = "appointmentStartTime")),
            @AttributeOverride(name = "endTime", column = @Column(name = "appointmentEndTime"))
    })
    private TimePeriod timePeriod;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private CompanyAdmin companyAdmin;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "company_id")
    private Company company;
    private AppointmentStatus status;

    public Appointment() {
    }

    public Appointment(Long id, TimePeriod timePeriod, Company company, AppointmentStatus status) {
        this.id = id;
        this.timePeriod = timePeriod;
        this.company = company;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public CompanyAdmin getCompanyAdmin() {
        return companyAdmin;
    }

    public void setCompanyAdmin(CompanyAdmin companyAdmin) {
        this.companyAdmin = companyAdmin;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
