package com.group5.memegenerator.model;

public interface IPasswordEncryption {

    public String encryptPassword(String password);

    public boolean isPasswordAuthenticated(String userProvided, String passwordInDatabase);

}

