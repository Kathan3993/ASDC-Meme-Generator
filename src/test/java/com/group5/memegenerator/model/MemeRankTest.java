package com.group5.memegenerator.model;

import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.IFollowRankDAO;
import com.group5.memegenerator.database.mock.MockDatabaseOperation;
import com.group5.memegenerator.database.mock.MockFollowRankDAO;
import com.group5.memegenerator.database.mock.MockMemeDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MemeRankTest {
    IDatabaseOperation databaseOperation;
    IFollowRankDAO followRankDao;

    public MemeRankTest() {
        databaseOperation = new MockDatabaseOperation();
        followRankDao = new MockFollowRankDAO();
    }

    @Test
    public void getMemeRankTest() {
        List<IMeme> memes = new ArrayList<>();
        MockMemeDAO memeDaoMock = new MockMemeDAO();
        for (int i = 1; i <= 10; i++) {
            memes.add(memeDaoMock.loadMemeById("" + i));
        }
        String userId = "1";
        MemeRank memeRank = new MemeRank(userId, followRankDao);
        List<IMeme> sortedMemes = memeRank.getMemeRank(memes);
        Assertions.assertEquals(sortedMemes.size(), memes.size());
    }

}
