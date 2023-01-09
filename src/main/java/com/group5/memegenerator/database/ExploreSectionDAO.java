package com.group5.memegenerator.database;

import java.util.ArrayList;
import java.util.HashMap;

public class ExploreSectionDAO implements IExploreSectionDAO {

    private IDatabaseOperation databaseOperation;

    public ExploreSectionDAO(IDatabaseOperation databaseOperation) {
        this.databaseOperation = databaseOperation;
    }

    @Override
    public ArrayList<HashMap<String, String>> getMostLikedMemesDatabase() {
        String tableName = "memes";
        String fields = "*";
        String endQuery = "where meme_Id in " +
                "(SELECT \n" +
                "meme_Id\n" +
                "FROM Likes\n" +
                "GROUP BY meme_Id " +
                "ORDER BY likeTimeStamp ASC, count(*)) LIMIT 10";

        try {
            return this.databaseOperation.select(fields, tableName, endQuery);
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public ArrayList<HashMap<String, String>> getLikedRecentMemesDatabase() {
        String tableName = "memes";
        String fields = "*";
        String endQuery = "where meme_Id in (SELECT \n" +
                "meme_Id\n" +
                "FROM Likes\n" +
                "where likeTimeStamp BETWEEN CURDATE()-7 AND CURDATE()\n" +
                "GROUP BY meme_Id ORDER BY likeTimeStamp ASC, count(*)) LIMIT 10";

        try {
            return this.databaseOperation.select(fields, tableName, endQuery);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ArrayList<HashMap<String, String>> getTopRankedMemesDatabase() {
        String tableName = "memes";
        String fields = "*";
        String endQuery = "ORDER BY meme_Rank ASC";

        try {
            return this.databaseOperation.select(fields, tableName, endQuery);
        } catch (Exception e) {
            return null;
        }
    }
}
