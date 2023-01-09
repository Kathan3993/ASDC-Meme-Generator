package com.group5.memegenerator.model;

import com.group5.memegenerator.database.IFollowDAO;
import com.group5.memegenerator.database.IMemeHomeDAO;
import com.group5.memegenerator.database.factory.FollowDAOFactory;
import com.group5.memegenerator.database.factory.MemeHomeDAOFactory;
import com.group5.memegenerator.model.factory.MemeHomeFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class MemeHomeTest {
    @Test
    public void loadMemesInHomePagePositiveTest() {

        IMemeHomeDAO memeHomeDAO = MemeHomeDAOFactory.instance().makeMockMemeHomeDAO();
        IFollowDAO followDAO = FollowDAOFactory.instance().makeMockFollowDAO();
        IMemeHome memeHome = MemeHomeFactory.instance().makeMemeHome(memeHomeDAO);

        String userId = "1";

        List<IMeme> memes = memeHome.loadMemesInHomePage(userId, followDAO);

        Assertions.assertNotEquals(null, memes);

    }

    @Test
    public void loadMemesInHomePageNegativeTest() {

        IMemeHomeDAO memeHomeDAO = MemeHomeDAOFactory.instance().makeMockMemeHomeDAO();
        IFollowDAO followDAO = FollowDAOFactory.instance().makeMockFollowDAO();

        IMemeHome memeHome = MemeHomeFactory.instance().makeMemeHome(memeHomeDAO);

        String userId = "";

        List<IMeme> memes = memeHome.loadMemesInHomePage(userId, followDAO);

        Assertions.assertNotEquals(null, memes);

    }
}
