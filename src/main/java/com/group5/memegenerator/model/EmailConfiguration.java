package com.group5.memegenerator.model;

import lombok.Getter;
import lombok.Setter;

public class EmailConfiguration {

    @Getter
    @Setter
    String fromEmail;
    @Getter
    @Setter
    String mailHost;
    @Getter
    @Setter
    String mailPort;
    @Getter
    @Setter
    String mailUserName;
    @Getter
    @Setter
    String mailPassword;
    @Getter
    @Setter
    Boolean mailSmtpAuth;
    @Getter
    @Setter
    Boolean mailStarttls;
    @Getter
    @Setter
    String mailSubject;
    @Getter
    @Setter
    String mailContent;

    String baseUrl;

    // public String getFromEmail() {
    // return fromEmail;
    // }

    // public void setFromEmail(String fromEmail) {
    // this.fromEmail = fromEmail;
    // }

    // public String getMailHost() {
    // return mailHost;
    // }

    // public void setMailHost(String mailHost) {
    // this.mailHost = mailHost;
    // }

    // public String getMailPort() {
    // return mailPort;
    // }

    // public void setMailPort(String mailPort) {
    // this.mailPort = mailPort;
    // }

    // public String getMailUserName() {
    // return mailUserName;
    // }

    // public void setMailUserName(String mailUserName) {
    // this.mailUserName = mailUserName;
    // }

    // public String getMailPassword() {
    // return mailPassword;
    // }

    // public void setMailPassword(String mailPassword) {
    // this.mailPassword = mailPassword;
    // }

    // public Boolean getMailSmtpAuth() {
    // return mailSmtpAuth;
    // }

    // public void setMailSmtpAuth(Boolean mailSmtpAuth) {
    // this.mailSmtpAuth = mailSmtpAuth;
    // }

    // public Boolean getMailStarttls() {
    // return mailStarttls;
    // }

    // public void setMailStarttls(Boolean mailStarttls) {
    // this.mailStarttls = mailStarttls;
    // }

    // public String getMailSubject() {
    // return mailSubject;
    // }

    // public void setMailSubject(String mailSubject) {
    // this.mailSubject = mailSubject;
    // }

    // public String getMailContent() {
    // return mailContent;
    // }

    // public void setMailContent(String mailContent) {
    // this.mailContent = mailContent;
    // }

    // public String getBaseUrl() {
    // return baseUrl;
    // }

    // public void setBaseUrl(String baseUrl) {
    // this.baseUrl = baseUrl;
    // }
}
