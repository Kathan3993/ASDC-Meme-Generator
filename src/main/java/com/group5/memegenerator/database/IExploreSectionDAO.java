package com.group5.memegenerator.database;

import java.util.ArrayList;
import java.util.HashMap;

public interface IExploreSectionDAO {

    ArrayList<HashMap<String, String>> getMostLikedMemesDatabase();

    ArrayList<HashMap<String, String>> getLikedRecentMemesDatabase();

    ArrayList<HashMap<String, String>> getTopRankedMemesDatabase();
}
