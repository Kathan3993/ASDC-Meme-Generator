package com.group5.memegenerator.model;

import java.util.List;

public interface IExploreSection {

    List<IMeme> loadMotLikedMemesFromDatabase();

    List<IMeme> loadMostLikedRecentMemesFromDatabase();

    List<IMeme> loadTopRankedMemesFromDatabase();

}
