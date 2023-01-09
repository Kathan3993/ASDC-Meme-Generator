package com.group5.memegenerator.database.mock;

import com.group5.memegenerator.database.DatabaseResponse;
import com.group5.memegenerator.database.IDatabaseOperation;

import java.util.ArrayList;
import java.util.HashMap;

public class MockDatabaseOperation implements IDatabaseOperation {

    public ArrayList<HashMap<String, String>> select(String fields, String tableName, String endQuery) {

        return null;
    }

    public DatabaseResponse insert(String tableName, String fieldNames, String values) {

        return null;
    }

    public DatabaseResponse delete(String tableName, String condition) {

        return null;
    }

    public DatabaseResponse update(String tableName, String setValues, String condition) {

        return null;
    }

}
