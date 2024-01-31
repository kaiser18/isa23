package com.ftn.isa23.reservation.service.impl;

import com.ftn.isa23.company.domain.Appointment;
import com.ftn.isa23.company.domain.Storage;
import com.ftn.isa23.company.domain.enums.AppointmentStatus;
import com.ftn.isa23.company.repository.AppointmentRepository;
import com.ftn.isa23.company.repository.StorageRepository;
import com.ftn.isa23.reservation.domain.Reservation;
import com.ftn.isa23.reservation.domain.ReservationStorage;
import com.ftn.isa23.reservation.repository.ReservationRepository;
import com.ftn.isa23.reservation.repository.ReservationStorageRepository;
import com.ftn.isa23.reservation.service.IReservationService;
import com.ftn.isa23.users.domain.Customer;
import com.ftn.isa23.users.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService implements IReservationService {
    private final ReservationRepository repository;
    private final ReservationStorageRepository reservationStorageRepository;
    private final CustomerRepository customerRepository;
    private final AppointmentRepository appointmentRepository;
    private final StorageRepository storageRepository;

    @Autowired
    public ReservationService(ReservationRepository repository, ReservationStorageRepository reservationStorageRepository, CustomerRepository customerRepository, AppointmentRepository appointmentRepository, StorageRepository storageRepository) {
        this.repository = repository;
        this.reservationStorageRepository = reservationStorageRepository;
        this.customerRepository = customerRepository;
        this.appointmentRepository = appointmentRepository;
        this.storageRepository = storageRepository;
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
}
