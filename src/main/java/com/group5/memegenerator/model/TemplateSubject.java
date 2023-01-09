package com.group5.memegenerator.model;

import com.group5.memegenerator.database.IUserDetailsDAO;

public class TemplateSubject extends Subject {
    private static TemplateSubject templateSubject = null;

    private TemplateSubject(IUserDetailsDAO userDetailsDAO) {
        super(userDetailsDAO);

    }

    public static TemplateSubject instance(IUserDetailsDAO userDetailsDAO) {
        if (templateSubject == null) {
            templateSubject = new TemplateSubject(userDetailsDAO);
        }
        return templateSubject;
    }
}
