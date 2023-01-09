package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.database.ILoginDAO;
import com.group5.memegenerator.model.ILogin;
import com.group5.memegenerator.model.Login;
import lombok.extern.java.Log;

public class LoginFactory implements ILoginFactory {

    private static ILoginFactory loginFactory;

    private LoginFactory(){

    }

    public static ILoginFactory instance(){
        if(loginFactory == null){
            return new LoginFactory();
        }

        return loginFactory;
    }


    @Override
    public ILogin makeLogin(ILoginDAO loginDAO) {
        return new Login(loginDAO);
    }


}
