package com.group5.memegenerator.database;

import com.group5.memegenerator.model.IMeme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemeHomeDAO implements IMemeHomeDAO {

    private IDatabaseOperation databaseOperation;
    private ILikeDAO likeDAO;

    public MemeHomeDAO(IDatabaseOperation databaseOperation, ILikeDAO likeDAO) {
        this.databaseOperation = databaseOperation;
        this.likeDAO = likeDAO;
    }

    @Override
    public List<IMeme> getMemeForHomePage(String UserID) {
        String tableName = "memes";
        String fields = "*";
        String endQuery = "order by meme_Rank DESC";

        try {
            ArrayList<HashMap<String, String>> results = databaseOperation.select(fields, tableName, endQuery);
            return MemeDAO.loadMemeListFromDatabase(results, this.likeDAO);
        } catch (Exception e) {
            return null;
        }
    }
}
