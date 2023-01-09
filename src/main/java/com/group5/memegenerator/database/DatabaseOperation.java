package com.group5.memegenerator.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseOperation implements IDatabaseOperation {
    private Connection connection;

    public DatabaseOperation() {
        this.connection = Database.instance().connectToDB();
    }

    public ArrayList<HashMap<String, String>> select(String fields, String tableName,
                                                     String endQuery) {
        try {
            String sqlSelectAllUsers = "SELECT " + fields + " FROM " + tableName + " " + endQuery;
            PreparedStatement ps = connection.prepareStatement(sqlSelectAllUsers);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            ArrayList<String> fieldList = new ArrayList<>();
            ArrayList<HashMap<String, String>> values = new ArrayList<>();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String columnName = rsmd.getColumnName(i);
                fieldList.add(columnName);
            }
            while (rs.next()) {
                HashMap<String, String> row = new HashMap<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String columnName = rsmd.getColumnName(i);
                    String columnValue = rs.getString(i);
                    row.put(columnName, columnValue);
                }
                values.add(row);
            }
            return values;
        } catch (SQLException sqlException) {
            return null;
        }
    }

    public DatabaseResponse insert(String tableName, String fieldNames, String values) {
        String sqlQuery = "INSERT INTO " + tableName + "(" + fieldNames + ") VALUES(" + values + ")";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            return DatabaseResponse.ERROR;
        }
        return DatabaseResponse.SUCCESS;
    }

    public DatabaseResponse delete(String tableName, String condition) {
        String sqlQuery = "DELETE FROM " + tableName + " WHERE " + condition;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
        } catch (Exception e) {
            e.printStackTrace();
            return DatabaseResponse.ERROR;
        }
        return DatabaseResponse.SUCCESS;
    }

    public DatabaseResponse update(String tableName, String setValues, String condition) {
        String sqlQuery = "UPDATE " + tableName + " SET " + setValues + " WHERE " + condition;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
        } catch (Exception e) {
            e.printStackTrace();
            return DatabaseResponse.ERROR;
        }
        return DatabaseResponse.SUCCESS;
    }

}
