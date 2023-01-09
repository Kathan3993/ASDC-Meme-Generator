package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.database.IChangePasswordDAO;
import com.group5.memegenerator.model.IChangePassword;

public interface IChangePasswordFactory {
    IChangePassword makeChangePassword(IChangePasswordDAO changePasswordDAO);
}
