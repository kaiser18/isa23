package com.ftn.isa23.users.service;

import com.ftn.isa23.users.domain.Customer;
import com.ftn.isa23.users.dto.EmailDTO;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IEmailService {
    EmailDTO generateEmailInfo(Customer customer);
    void sendConfirmationEmail(Customer customer) throws FileNotFoundException, MessagingException, IOException;
}
