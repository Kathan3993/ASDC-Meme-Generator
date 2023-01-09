package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.database.ICheckUserCredentialsDAO;
import com.group5.memegenerator.model.CheckUserCredentials;
import com.group5.memegenerator.model.ICheckUserCredentials;

public class CheckUserCredentialFactory implements ICheckUserCredentialFactory {

    public static ICheckUserCredentialFactory checkCredentialFactory = null;

    public CheckUserCredentialFactory() {
    }

    public static ICheckUserCredentialFactory instance(){
        if (checkCredentialFactory == null){
            checkCredentialFactory = new CheckUserCredentialFactory();
        }

        return checkCredentialFactory;
    }


    @Override
    public ICheckUserCredentials makeCheckCredentials(ICheckUserCredentialsDAO checkUserCredentialsDAO) {
        return new CheckUserCredentials(checkUserCredentialsDAO);
    }
}
