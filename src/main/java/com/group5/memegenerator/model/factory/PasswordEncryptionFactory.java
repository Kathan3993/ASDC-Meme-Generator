package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.model.IPasswordEncryption;
import com.group5.memegenerator.model.PasswordEncryption;

public class PasswordEncryptionFactory implements IPasswordEncryptionFactory {


    private static IPasswordEncryptionFactory passwordEncryptionFactory = null;

    public PasswordEncryptionFactory() {
    }

    public static IPasswordEncryptionFactory instance() {

        if (passwordEncryptionFactory == null) {
            passwordEncryptionFactory = new PasswordEncryptionFactory();
        }
        return passwordEncryptionFactory;
    }

    @Override
    public IPasswordEncryption makePasswordEncryption() {
        return new PasswordEncryption();
    }
}
