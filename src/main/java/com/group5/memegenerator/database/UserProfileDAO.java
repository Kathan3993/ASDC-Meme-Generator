package com.group5.memegenerator.database;

import com.group5.memegenerator.model.IMeme;
import com.group5.memegenerator.model.Like;
import com.group5.memegenerator.model.Meme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserProfileDAO implements IUserProfileDAO{
    private IDatabaseOperation databaseOperation;

    private ILikeDAO likeDAO;

    public UserProfileDAO(IDatabaseOperation databaseOperation, ILikeDAO likeDAO)
    {
        this.databaseOperation = databaseOperation;
        this.likeDAO = likeDAO;
    }

    @Override
    public List<IMeme> getMemeForUserProfile(String username) {
        String tableName = "memes";
        String fields = "*";
        String endQuery = "where Id='"+username+"'";

        try{
            ArrayList<HashMap<String, String>> results = databaseOperation.select(fields, tableName, endQuery);
            return MemeDAO.loadMemeListFromDatabase(results, this.likeDAO);
        }
        catch (Exception e){
            return null;
        }
    }
}
