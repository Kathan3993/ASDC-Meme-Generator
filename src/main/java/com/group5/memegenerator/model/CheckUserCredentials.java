package com.group5.memegenerator.model;

import com.group5.memegenerator.database.ICheckUserCredentialsDAO;
import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.factory.CheckUserCredentialsDAOFactory;
import com.group5.memegenerator.database.factory.DatabaseOperationFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckUserCredentials implements ICheckUserCredentials {

    ICheckUserCredentialsDAO checkUserCredentialsDAO;
    public CheckUserCredentials(ICheckUserCredentialsDAO checkUserCredentialsDAO) {
        this.checkUserCredentialsDAO = checkUserCredentialsDAO;
    }

    @Override
    public boolean checkEmail(String userName){

        IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory().makeDataBaseOperation();
        ICheckUserCredentialsDAO checkCredentialsDAO = CheckUserCredentialsDAOFactory.instance().makeCheckUserCredentialsDAO(databaseOperation);

        ArrayList<HashMap<String, String>> results = null;

        try {
            results = checkCredentialsDAO.checkEmail(userName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        for (HashMap<String, String> result : results) {
            Object[] keys = result.keySet().toArray();

            for (Object key : keys) {
                System.out.println("FieldName:" + key);
                System.out.println("Value:" + result.get(key));
            }
        }

        return results.size() > 0;
    }
}
