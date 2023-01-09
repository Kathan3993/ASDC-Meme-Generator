package com.group5.memegenerator.database;

import java.util.ArrayList;
import java.util.HashMap;

public interface ILoginDAO {

    ArrayList<HashMap<String, String>> login(String userName, String password);
}
