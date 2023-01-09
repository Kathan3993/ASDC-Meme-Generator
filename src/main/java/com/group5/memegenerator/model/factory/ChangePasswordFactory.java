package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.database.IChangePasswordDAO;
import com.group5.memegenerator.model.ChangePassword;
import com.group5.memegenerator.model.IChangePassword;

public class ChangePasswordFactory implements IChangePasswordFactory {

    private static IChangePasswordFactory changePasswordFactory = null;

    public ChangePasswordFactory() {
    }

    public static IChangePasswordFactory instance() {

        if (changePasswordFactory == null) {
            changePasswordFactory = new ChangePasswordFactory();
        }
        return changePasswordFactory;
    }

    @Override
    public IChangePassword makeChangePassword(IChangePasswordDAO changePasswordDAO) {
        return new ChangePassword(changePasswordDAO);
    }
}
