package com.group5.memegenerator.model;

import com.group5.memegenerator.database.IExploreSectionDAO;
import com.group5.memegenerator.database.ILikeDAO;
import com.group5.memegenerator.database.MemeDAO;
import com.group5.memegenerator.database.MemeHomeDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExploreSection implements IExploreSection {

    private IExploreSectionDAO exploreSectionDAO;

    private ILike like;

    public ExploreSection(IExploreSectionDAO exploreSectionDAO, ILike like) {
        this.exploreSectionDAO = exploreSectionDAO;
        this.like = like;
    }


    public List<IMeme> loadMotLikedMemesFromDatabase() {
        ArrayList<HashMap<String, String>> result = this.exploreSectionDAO.getMostLikedMemesDatabase();
        List<IMeme> memes = MemeDAO.loadMemeListFromDatabase(result, like.getLikeDAO());

        return memes;
    }

    public List<IMeme> loadMostLikedRecentMemesFromDatabase() {
        ArrayList<HashMap<String, String>> result = this.exploreSectionDAO.getLikedRecentMemesDatabase();
        List<IMeme> memes = MemeDAO.loadMemeListFromDatabase(result, like.getLikeDAO());
        return memes;

    }

    public List<IMeme> loadTopRankedMemesFromDatabase() {
        ArrayList<HashMap<String, String>> result = this.exploreSectionDAO.getTopRankedMemesDatabase();
        List<IMeme> memes =  MemeDAO.loadMemeListFromDatabase(result, like.getLikeDAO());
        return memes;
    }
}
