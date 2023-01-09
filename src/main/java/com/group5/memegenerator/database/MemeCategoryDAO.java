package com.group5.memegenerator.database;

import java.util.ArrayList;
import java.util.HashMap;

public class MemeCategoryDAO implements IMemeCategoryDAO {
    IDatabaseOperation databaseOperation;

    public MemeCategoryDAO(IDatabaseOperation databaseOperation) {
        this.databaseOperation = databaseOperation;

    }

    public HashMap<Integer, String> getMemeCategoryByCategoryId(String categoryId) {
        ArrayList<HashMap<String, String>> results = new ArrayList<>();
        try {
            results = databaseOperation.select("*", "category",
                    " WHERE 'id'=" + categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<Integer, String> category = new HashMap<>();
        for (int i = 0; i < results.size(); i++) {
            HashMap<String, String> result = results.get(i);
            Object keys[] = result.keySet().toArray();
            for (int j = 0; j < keys.length; j++) {
                if (keys[j] == "name") {
                    category.put(Integer.parseInt(categoryId), result.get(keys[j]));
                }
            }
        }
        return category;
    }

}
