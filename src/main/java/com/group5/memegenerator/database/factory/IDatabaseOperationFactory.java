package com.group5.memegenerator.database.factory;

import com.group5.memegenerator.database.IDatabaseOperation;

import java.sql.Connection;

public interface IDatabaseOperationFactory {
    IDatabaseOperation makeDataBaseOperation();

}
