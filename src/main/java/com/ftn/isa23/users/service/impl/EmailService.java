package com.ftn.isa23.users.service.impl;

import com.ftn.isa23.users.domain.Customer;
import com.ftn.isa23.users.dto.EmailDTO;
import com.ftn.isa23.users.service.IEmailService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


@Service
public class EmailService { //implements IEmailService {
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

   // @Override
    public EmailDTO generateEmailInfo(Customer customer) {
        String url = "http://localhost:4200/login?token=" + customer.getVerificationCode();
        String email = customer.getEmail();
        EmailDTO dto = new EmailDTO();
        dto.setCustomerEmail(email);
        dto.setLink(url);
        dto.setSubject("Confirm registration");

        return dto;
    }

   // @Override
    @Async
    public void sendConfirmationEmail(Customer customer) throws FileNotFoundException, MessagingException, IOException {
        EmailDTO dto = generateEmailInfo(customer);
        String filePath = "./verificationEmailTemplate.html";
        File starting = new File(System.getProperty("user.dir"));
        File file = new File(starting, "src/main/java/com/ftn/isa23/users/service/impl/verificationEmailTemplate.html");

        Document doc = Jsoup.parse(file, "utf-8");

        MimeMessage message = javaMailSender.createMimeMessage();
        Multipart multiPart = new MimeMultipart("alternative");

        MimeBodyPart htmlPart = new MimeBodyPart();
        String body = doc.body().getElementsByTag("body").toString();
        body = body.replace("[link]", dto.getLink());

        htmlPart.setContent(body, "text/html; charset=utf-8");
        multiPart.addBodyPart(htmlPart);

        message.setContent(multiPart);
        message.setRecipients(Message.RecipientType.TO, dto.getCustomerEmail());

        message.setSubject(dto.getSubject());

        javaMailSender.send(message);
    }

    @Async
    public void sendQRCode(Customer customer, String message, String pathToAttachment) throws FileNotFoundException, MessagingException, IOException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom("medequipmentisa23@gmail.com");
        helper.setTo(customer.getEmail());
        helper.setSubject("Reservation");
        helper.setText(message);

        FileSystemResource file
                = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("QR.png", file);

        javaMailSender.send(mimeMessage);
    }
}
