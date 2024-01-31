package com.ftn.isa23.reservation.service.impl;

import com.ftn.isa23.company.domain.Appointment;
import com.ftn.isa23.company.domain.Company;
import com.ftn.isa23.company.domain.Storage;
import com.ftn.isa23.company.domain.enums.AppointmentStatus;
import com.ftn.isa23.company.repository.AppointmentRepository;
import com.ftn.isa23.company.repository.CompanyRepository;
import com.ftn.isa23.company.repository.EquipmentRepository;
import com.ftn.isa23.company.repository.StorageRepository;
import com.ftn.isa23.reservation.QRCode;
import com.ftn.isa23.reservation.domain.Reservation;
import com.ftn.isa23.reservation.domain.ReservationStorage;
import com.ftn.isa23.reservation.domain.enums.ReservationStatus;
import com.ftn.isa23.reservation.dto.CreateReservationDTO;
import com.ftn.isa23.reservation.dto.ReservationStorageDTO;
import com.ftn.isa23.reservation.repository.ReservationRepository;
import com.ftn.isa23.reservation.repository.ReservationStorageRepository;
import com.ftn.isa23.reservation.service.IReservationService;
import com.ftn.isa23.users.domain.Customer;
import com.ftn.isa23.users.repository.CustomerRepository;
import com.ftn.isa23.users.service.impl.EmailService;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService implements IReservationService {
    private final ReservationRepository repository;
    private final ReservationStorageRepository reservationStorageRepository;
    private final CustomerRepository customerRepository;
    private final AppointmentRepository appointmentRepository;
    private final StorageRepository storageRepository;
    private final CompanyRepository companyRepository;
    private final EquipmentRepository equipmentRepository;
    private final EmailService emailService;

    @Autowired
    public ReservationService(ReservationRepository repository, ReservationStorageRepository reservationStorageRepository, CustomerRepository customerRepository, AppointmentRepository appointmentRepository, StorageRepository storageRepository, CompanyRepository companyRepository, EquipmentRepository equipmentRepository, EmailService emailService) {
        this.repository = repository;
        this.reservationStorageRepository = reservationStorageRepository;
        this.customerRepository = customerRepository;
        this.appointmentRepository = appointmentRepository;
        this.storageRepository = storageRepository;
        this.companyRepository = companyRepository;
        this.equipmentRepository = equipmentRepository;
        this.emailService = emailService;
    }

    @Override
    public List<Reservation> findCreatedForCustomer(Long customerId) {
        return this.repository.findCreatedForCustomer(customerId);
    }

    @Override
    public boolean cancel(Long reservationId) {
        Reservation reservation = repository.findById(reservationId).get();
        int cancellationPeriod = 3600 * 1000 * 24;
        Customer customer = reservation.getCustomer();
        Appointment appointment = reservation.getAppointment();
        List<ReservationStorage> reservationStorages = reservationStorageRepository.getAllStoragesByReservation(reservationId);
        List<Storage> storages = storageRepository.getAllStoragesByCompany(reservation.getCompany().getId());

        if (!reservation.getDoublePenalty(cancellationPeriod)) {
            customer.setPenalties(customer.getPenalties() + 2);
            customerRepository.save(customer);

            appointment.setStatus(AppointmentStatus.CREATED);
            appointmentRepository.save(appointment);

            for (Storage s: storages) {
                for (ReservationStorage rs : reservationStorages ) {
                    if (s.getEquipment().getId() == rs.getEquipment().getId()) {
                        s.setQuantity(s.getQuantity() + rs.getQuantity());
                        storageRepository.save(s);
                        reservationStorageRepository.delete(rs);
                    }
                }
            }

            reservation.setAppointment(null);
            repository.delete(reservation);

            return true;
        }

        customer.setPenalties(customer.getPenalties() + 1);
        customerRepository.save(customer);

        appointment.setStatus(AppointmentStatus.CREATED);
        appointmentRepository.save(appointment);

        for (Storage s: storages) {
            for (ReservationStorage rs : reservationStorages ) {
                if (s.getEquipment().getId() == rs.getEquipment().getId()) {
                    s.setQuantity(s.getQuantity() + rs.getQuantity());
                    storageRepository.save(s);
                    reservationStorageRepository.delete(rs);
                }
            }
        }

        reservation.setAppointment(null);
        repository.delete(reservation);

        return true;
    }

    @Override
    public Reservation create(CreateReservationDTO dto) throws Exception {
        Customer customer = customerRepository.findById(dto.getCustomerId()).get();

        if (customer.getPenalties() > 2) {
            throw new Exception("Customer has 3 or more penalties");
        }

        Reservation reservation = new Reservation();

        Appointment appointment = appointmentRepository.findById(dto.getAppointmentId()).get();
        appointment.setStatus(AppointmentStatus.RESERVED);
        appointmentRepository.save(appointment);
        reservation.setAppointment(appointment);

        reservation.setCustomer(customer);

        Company company = companyRepository.findById(dto.getCompanyId()).get();
        reservation.setCompany(company);

        reservation.setStatus(ReservationStatus.CREATED);

        repository.save(reservation);

        for (ReservationStorageDTO rs : dto.getReservationStorages()) {
                ReservationStorage rStorage = new ReservationStorage();
                rStorage.setReservation(reservation);
                rStorage.setEquipment(equipmentRepository.findById(rs.getEquipmentId()).get());
                rStorage.setQuantity(rs.getQuantity());

                reservationStorageRepository.save(rStorage);
        }

        List<Storage> companyStorages = storageRepository.getAllStoragesByCompany(company.getId());

        for (Storage s : companyStorages) {
            for (ReservationStorageDTO rs : dto.getReservationStorages()) {
                if (s.getEquipment().getId() == rs.getEquipmentId()) {
                    s.setQuantity(s.getQuantity() - rs.getQuantity());

                    storageRepository.save(s);
                }
            }
        }

        String message = "You have made a reservation" + "\r\n"
                + "Company: " + company.getName() + "\r\n"
                + "Company Admin: " + appointment.getCompanyAdmin().getName() + " " + appointment.getCompanyAdmin().getSurname() + "\r\n"
                + "Reservation ID: " + reservation.getId() + "\r\n"
                + "Time: " + appointment.getTimePeriod().getStartTime();

        QRCode generateQrCode = new QRCode();
        generateQrCode.generateQrCodeReservation(message, reservation.getId().toString());

        this.emailService.sendQRCode(reservation.getCustomer(), message, "D:/Faks/isa qr/"+reservation.getId().toString()+".png");

        return reservation;
    }
}
