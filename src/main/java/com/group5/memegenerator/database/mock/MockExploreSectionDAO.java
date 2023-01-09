package com.group5.memegenerator.database.mock;

import com.group5.memegenerator.database.IExploreSectionDAO;

import java.util.ArrayList;
import java.util.HashMap;

public class MockExploreSectionDAO implements IExploreSectionDAO {
    @Override
    public ArrayList<HashMap<String, String>> getMostLikedMemesDatabase() {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<HashMap<String, String>> getLikedRecentMemesDatabase() {

        return new ArrayList<>();
    }

    @Override
    public ArrayList<HashMap<String, String>> getTopRankedMemesDatabase() {

        return new ArrayList<>();
    }
}
