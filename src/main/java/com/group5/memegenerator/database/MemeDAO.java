package com.group5.memegenerator.database;

import com.group5.memegenerator.database.factory.MemeDAOFactory;
import com.group5.memegenerator.model.IMeme;
import com.group5.memegenerator.model.Meme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class MemeDAO implements IMemeDAO {
    private IDatabaseOperation databaseOperation;

    public MemeDAO(IDatabaseOperation databaseOperation) {
        this.databaseOperation = databaseOperation;
    }

    public static List<IMeme> loadMemeListFromDatabase(ArrayList<HashMap<String, String>> results, ILikeDAO likeDAO) {
        List<IMeme> memes = null;

        try {
            memes = new ArrayList<>();
            for (HashMap<String, String> result : results) {
                IMeme meme = new Meme();

                meme.setMemeId(result.get("meme_Id"));
                meme.setMemePicture(result.get("meme_Image"));
                int likesCount = likeDAO.getLikes(meme);
                meme.setMemeLikes(likesCount);
                meme.setUserId(result.get("Id"));
                meme.setMemeRank(Double.parseDouble(result.get("meme_Rank")));
                meme.setCategoryId(result.get("meme_Category_Id"));

                memes.add(meme);
            }
        } catch (Exception e) {
            System.out.println("error");
        }
        return memes;
    }

    @Override
    public IMeme getMemeById(String memeId) {
        IMeme meme = new Meme();

        List<HashMap<String, String>> results = new ArrayList<>();
        try {
            results = databaseOperation.select("*", "memes", " WHERE 'meme_id'='" + memeId + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Iterator<HashMap<String, String>> iter = results.iterator();
        while (iter.hasNext()) {
            HashMap<String, String> result = iter.next();
            meme.setMemeId(result.get("meme_Id"));
            meme.setUserId(result.get("Id"));
            meme.setCategoryId(result.get("meme_Category_Id"));
            meme.setMemePicture(result.get("meme_Image"));
        }
        return meme;
    }

    public DatabaseResponse saveMeme(String username, String memeCategory, String memeId, String memeImage) {
        DatabaseResponse result;
        String fields = "Id,meme_Category_Id,template_Id,meme_Image";
        String values = "'" + username + "'," + "'" + memeCategory + "'," + "'" + memeId + "'," + "'" + memeImage
                + "'";
        try {
            result = databaseOperation.insert("memes", fields, values);
        } catch (Exception exception) {
            result = DatabaseResponse.ERROR;
        }

        return result;
    }

    @Override
    public IMeme loadMemeById(String id, ILikeDAO likeDAO) throws Exception {
        String table = "memes";
        String fields = "*";
        String endQuery = "where meme_Id = " + id;

        ArrayList<HashMap<String, String>> results = databaseOperation.select(fields, table, endQuery);

        return loadMemeListFromDatabase(results, MemeDAOFactory.instance().makeLikeDAO(this.databaseOperation)).get(0);

    }
}
