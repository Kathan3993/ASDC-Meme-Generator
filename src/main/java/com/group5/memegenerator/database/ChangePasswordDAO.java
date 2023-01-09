package com.group5.memegenerator.database;

public class ChangePasswordDAO implements IChangePasswordDAO{

    IDatabaseOperation databaseOperation;
    public ChangePasswordDAO(IDatabaseOperation databaseOperation){
        this.databaseOperation=databaseOperation;
    }

    @Override
    public void setPassword(String email, String password) throws Exception {

        databaseOperation.update("users","password='"+ password +"'"," email='"+ email +"'");
    }
}
