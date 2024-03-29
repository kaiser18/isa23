package com.ftn.isa23.reservation.service;

import com.ftn.isa23.reservation.domain.Reservation;
import com.ftn.isa23.reservation.dto.CreateReservationDTO;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface IReservationService {
    List<Reservation> findCreatedForCustomer(Long customerId);
    boolean cancel(Long reservationId);
    Reservation create(CreateReservationDTO dto) throws Exception;
}
