package com.group5.memegenerator.database;

public interface IChangePasswordDAO {

    void setPassword(String email, String password) throws Exception;
}
