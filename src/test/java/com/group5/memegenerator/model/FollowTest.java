package com.group5.memegenerator.model;

import com.group5.memegenerator.database.DatabaseResponse;
import com.group5.memegenerator.database.IFollowDAO;
import com.group5.memegenerator.database.factory.FollowDAOFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FollowTest {
    @Test
    public void followPositiveTest()
    {
        IFollowDAO followDAO= FollowDAOFactory.instance().makeMockFollowDAO();
        String userId1="1";
        String userId2="6";
        DatabaseResponse databaseResponse=followDAO.follow(userId1,userId2);
        Assertions.assertEquals(DatabaseResponse.SUCCESS,databaseResponse);
    }

    @Test
    public void followNegativeTest()
    {
        IFollowDAO followDAO= FollowDAOFactory.instance().makeMockFollowDAO();
        String userId1=null;
        String userId2=null;
        DatabaseResponse databaseResponse=followDAO.follow(userId1,userId2);
        Assertions.assertEquals(DatabaseResponse.ERROR,databaseResponse);
    }

    @Test
    public void unfollowPositiveTest()
    {
        IFollowDAO followDAO= FollowDAOFactory.instance().makeMockFollowDAO();
        String userId1="1";
        String userId2="6";
        DatabaseResponse databaseResponse=followDAO.unfollow(userId1,userId2);
        Assertions.assertEquals(DatabaseResponse.SUCCESS,databaseResponse);
    }

    @Test
    public void unfollowNegativeTest()
    {
        IFollowDAO followDAO= FollowDAOFactory.instance().makeMockFollowDAO();
        String userId1=null;
        String userId2=null;
        DatabaseResponse databaseResponse=followDAO.unfollow(userId1,userId2);
        Assertions.assertEquals(DatabaseResponse.ERROR,databaseResponse);
    }

    @Test
    public void followerCountPositiveTest()
    {
        IFollowDAO followDAO= FollowDAOFactory.instance().makeMockFollowDAO();
        String userId1="6";
        String result=followDAO.followerCount(userId1);
        Assertions.assertEquals("1",result);
    }

    @Test
    public void followingCountNegativeTest()
    {
        IFollowDAO followDAO= FollowDAOFactory.instance().makeMockFollowDAO();
        String userId1="";
        String result=followDAO.followerCount(userId1);
        Assertions.assertEquals("0",result);
    }
    @Test
    public void followingCountPositiveTest()
    {
        IFollowDAO followDAO= FollowDAOFactory.instance().makeMockFollowDAO();
        String userId1="6";
        String result=followDAO.followerCount(userId1);
        Assertions.assertEquals("1",result);
    }

    @Test
    public void followerCountNegativeTest()
    {
        IFollowDAO followDAO= FollowDAOFactory.instance().makeMockFollowDAO();
        String userId1="";
        String result=followDAO.followerCount(userId1);
        Assertions.assertEquals("0",result);
    }
    @Test
    public void isFollowingPositiveTest()
    {
        IFollowDAO followDAO= FollowDAOFactory.instance().makeMockFollowDAO();
        String userId1="1";
        String userId2="6";
        boolean results=followDAO.isFollowing(userId1,userId2);
        Assertions.assertEquals(true,results);
    }
    @Test
    public void isFollowingNegativeTest()
    {
        IFollowDAO followDAO= FollowDAOFactory.instance().makeMockFollowDAO();
        String userId1="";
        String userId2="";
        boolean results=followDAO.isFollowing(userId1,userId2);
        Assertions.assertEquals(false,results);
    }

}
