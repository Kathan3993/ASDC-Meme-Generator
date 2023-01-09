package com.group5.memegenerator.model;

import com.group5.memegenerator.database.IFollowDAO;

import java.util.List;

public interface IMemeHome {

    List<IMeme> loadMemesInHomePage(String userId, IFollowDAO followDAO);
}
