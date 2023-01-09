package com.group5.memegenerator.database;

import java.util.ArrayList;
import java.util.HashMap;

public interface ICheckUserCredentialsDAO {

    ArrayList<HashMap<String, String>> checkEmail(String userName) throws Exception;

    ArrayList<HashMap<String, String>> checkPassword(String userName) throws Exception;
}
