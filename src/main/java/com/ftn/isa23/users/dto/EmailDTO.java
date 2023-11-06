package com.ftn.isa23.users.dto;

public class EmailDTO {
    private String customerEmail;
    private String subject;
    private String body;
    private String link;

    public EmailDTO() {}

    public EmailDTO(String customerEmail, String subject, String body, String link) {
        this.customerEmail = customerEmail;
        this.subject = subject;
        this.body = body;
        this.link = link;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
