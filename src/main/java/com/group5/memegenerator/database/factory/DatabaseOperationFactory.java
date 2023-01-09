package com.group5.memegenerator.database.factory;

import com.group5.memegenerator.database.DatabaseOperation;
import com.group5.memegenerator.database.IDatabaseOperation;

import java.sql.Connection;

public class DatabaseOperationFactory implements IDatabaseOperationFactory {

    private static IDatabaseOperationFactory databaseOperationFactory = null;

    private DatabaseOperationFactory() {

    }

    public static IDatabaseOperationFactory databaseOperationFactory() {

        if (databaseOperationFactory == null) {
            databaseOperationFactory = new DatabaseOperationFactory();
        }
        return databaseOperationFactory;
    }

    @Override
    public IDatabaseOperation makeDataBaseOperation() {

        return new DatabaseOperation();
    }

}
