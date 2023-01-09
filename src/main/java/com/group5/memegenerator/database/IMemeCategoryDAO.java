package com.group5.memegenerator.database;

import java.util.HashMap;

public interface IMemeCategoryDAO {
    public HashMap<Integer, String> getMemeCategoryByCategoryId(String categoryId);
}
