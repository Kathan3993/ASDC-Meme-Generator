package com.group5.memegenerator.database;

public interface IUserService {
   // void forgetPassword(String email);

    void changePassword(String email,String oldPassword, String newPassword);

    void setPassword(String email,String password) throws Exception;
}
