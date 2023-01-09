package com.group5.memegenerator.model;

import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.IFollowRankDAO;
import com.group5.memegenerator.database.mock.MockDatabaseOperation;
import com.group5.memegenerator.database.mock.MockFollowRankDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FollowRankTest {
    @Test
    public void getFollowRankTest() {
        IDatabaseOperation databaseOperation = new MockDatabaseOperation();
        IFollowRankDAO followRankDao = new MockFollowRankDAO();
        IFollowRank followRankTest = new FollowRank(followRankDao);
        double followRank1 = followRankTest.getFollowRank("userId1", "notUserId2");
        Assertions.assertEquals(followRank1, 0);

        double followRank2 = followRankTest.getFollowRank("userId1", "userId2");
        Assertions.assertTrue(followRank2 > 0);

    }
}
