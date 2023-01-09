package com.group5.memegenerator.model;

import com.group5.memegenerator.database.IUserDetailsDAO;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private final List<ITemplateObserver> observers;

    private IUserDetailsDAO userDetailsDAO;

    public Subject(IUserDetailsDAO userDetailsDAO) {

        this.userDetailsDAO = userDetailsDAO;
        observers = loadObservers(userDetailsDAO);

    }

    private static List<ITemplateObserver> loadObservers(IUserDetailsDAO userDetailsDAO) {
        List<IUserDetails> obs = userDetailsDAO.loadAdminsFromDatabase();

        List<ITemplateObserver> templateObservers = new ArrayList<>();

        for (IUserDetails userDetails : obs) {
            AdminObserver u = (AdminObserver) userDetails;

            templateObservers.add(u);
        }

        return templateObservers;
    }

    public void attach() {
    }

    public void detach() {
    }

    public void notifySubjects() {
        for (ITemplateObserver observer : observers) {
            observer.templateUploaded();
        }
    }
}
