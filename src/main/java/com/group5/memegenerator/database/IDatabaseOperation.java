package com.group5.memegenerator.database;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDatabaseOperation {

    public ArrayList<HashMap<String, String>> select(String fields, String tableName, String endQuery);

    public DatabaseResponse insert(String tableName, String fieldNames, String values);

    public DatabaseResponse delete(String tableName, String condition);

    public DatabaseResponse update(String tableName, String setValues, String condition);

}
