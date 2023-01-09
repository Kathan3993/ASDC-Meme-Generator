package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.database.ICheckUserCredentialsDAO;
import com.group5.memegenerator.model.ICheckUserCredentials;

public interface ICheckUserCredentialFactory {

    ICheckUserCredentials makeCheckCredentials(ICheckUserCredentialsDAO checkUserCredentialsDAO);

}
