package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.model.Email;
import com.group5.memegenerator.model.IEmail;

public class EmailFactory implements IEmailFactory {

    private static IEmailFactory emailFactory = null;

    public EmailFactory() {
    }

    public static IEmailFactory instance() {

        if (emailFactory == null) {
            emailFactory = new EmailFactory();
        }
        return emailFactory;
    }


    @Override
    public IEmail makeEmail() {
        return new Email();
    }
}