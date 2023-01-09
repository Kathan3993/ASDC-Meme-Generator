package com.group5.memegenerator.model;

import com.group5.memegenerator.database.ILikeDAO;
import com.group5.memegenerator.database.factory.MemeDAOFactory;
import com.group5.memegenerator.model.factory.MemeFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LikeTest {

    @Test
    public void loadLikesForMemePositiveTest() {
        ILikeDAO likeDAO = MemeDAOFactory.instance().makeMockLikeDAO();
        ILike like = MemeFactory.instance().makeLike(likeDAO);
        IMeme meme = MemeFactory.instance().makeMeme();

        meme.setMemeId("321");

        int result = like.loadLikesForMeme(meme);

        Assertions.assertEquals(10, result);
    }

    @Test
    public void loadLikesForMemeNegativeTest() {
        ILikeDAO likeDAO = MemeDAOFactory.instance().makeMockLikeDAO();
        ILike like = MemeFactory.instance().makeLike(likeDAO);
        IMeme meme = MemeFactory.instance().makeMeme();

        int result = like.loadLikesForMeme(meme);

        Assertions.assertEquals(0, result);
    }

    @Test
    public void addLikeToMemePositiveTest() {
        ILikeDAO likeDAO = MemeDAOFactory.instance().makeMockLikeDAO();
        ILike like = MemeFactory.instance().makeLike(likeDAO);
        IMeme meme = MemeFactory.instance().makeMeme();

        meme.setMemeId("123");
        meme.setUserId("321");

        boolean result = like.addLikeToMeme(meme.getMemeId(), meme.getUserId());

        Assertions.assertEquals(true, result);
    }

    @Test
    public void addLikeToMemeNegativeTest() {
        ILikeDAO likeDAO = MemeDAOFactory.instance().makeMockLikeDAO();
        ILike like = MemeFactory.instance().makeLike(likeDAO);
        IMeme meme = MemeFactory.instance().makeMeme();

        boolean result = like.addLikeToMeme(meme.getMemeId(), meme.getUserId());

        Assertions.assertEquals(false, result);
    }
}
