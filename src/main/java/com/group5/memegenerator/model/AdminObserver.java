package com.group5.memegenerator.model;

import com.group5.memegenerator.database.IUserDetailsDAO;

public class AdminObserver extends UserDetails {

    public AdminObserver(IUserDetailsDAO userDetailsDAO) {
        super(userDetailsDAO);
    }

    public void templateUploaded() {

        IEmail email = new Email();
        String subject = "New Meme Template Uploaded";
        String content = "Please approve template uploaded by user";
        email.sendEmail(super.getEmail(), subject, content);

    }
}
