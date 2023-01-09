package com.group5.memegenerator.model;

public interface IEmail {

    void setEmailConfig(String mailSubject, String mailContent);

    void sendEmail(String email, String mailSubject, String mailContent);
}