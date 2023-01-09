package com.group5.memegenerator.database;

import com.group5.memegenerator.model.IMeme;

import java.util.ArrayList;
import java.util.HashMap;

public class LikeDAO implements ILikeDAO {

    private static String tableName = "Likes";

    private IDatabaseOperation databaseOperation;

    public LikeDAO(IDatabaseOperation databaseOperation) {
        this.databaseOperation = databaseOperation;
    }


    @Override
    public int getLikes(IMeme meme) {

        String fields = "count(*) as count";
        String endQuery = "where meme_id = " + meme.getMemeId();

        try {

            ArrayList<HashMap<String, String>> response = databaseOperation.select(fields, tableName, endQuery);
            int count = Integer.parseInt(response.get(0).get("count"));

            return count;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public boolean setLikes(String memeId, String userId) {
        String fieldNames = "meme_Id, user_id, liked";
        String value = memeId + "," + userId + "," + 1;
        databaseOperation.insert(tableName, fieldNames, value);

        return true;
    }

}
