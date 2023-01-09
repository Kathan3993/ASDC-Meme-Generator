package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.database.ILoginDAO;
import com.group5.memegenerator.model.ILogin;

public interface ILoginFactory {

    ILogin makeLogin(ILoginDAO loginDAO);

}
