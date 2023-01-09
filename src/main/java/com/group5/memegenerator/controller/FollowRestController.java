package com.group5.memegenerator.controller;

import com.group5.memegenerator.database.DatabaseResponse;
import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.IFollowDAO;
import com.group5.memegenerator.database.factory.DatabaseOperationFactory;
import com.group5.memegenerator.database.factory.FollowDAOFactory;
import com.group5.memegenerator.model.IFollow;
import com.group5.memegenerator.model.factory.FollowFactory;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowRestController {
    @PostMapping("/api/follow")
    public DatabaseResponse followUser(@CookieValue(name = "id") String userId1, String userId2) throws Exception {
        IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory()
                .makeDataBaseOperation();
        IFollowDAO followDAO = FollowDAOFactory.instance().makeFollowDAO(databaseOperation);
        IFollow followUnfollow = FollowFactory.instance().makeFollow(followDAO);
        return followUnfollow.follow(userId1, userId2);
    }

    @PostMapping("/api/unfollow")
    public DatabaseResponse unfollowUser(@CookieValue(name = "id") String userId1, String userId2) throws Exception {
        IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory()
                .makeDataBaseOperation();
        IFollowDAO followDAO = FollowDAOFactory.instance().makeFollowDAO(databaseOperation);
        IFollow followUnfollow = FollowFactory.instance().makeFollow(followDAO);
        return followUnfollow.unfollow(userId1, userId2);
    }

    @PostMapping("/api/isFollowing")
    public boolean isFollowingUser(String userId1, String userId2) throws Exception {
        IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory()
                .makeDataBaseOperation();
        IFollowDAO followDAO = FollowDAOFactory.instance().makeFollowDAO(databaseOperation);
        IFollow followUnfollow = FollowFactory.instance().makeFollow(followDAO);
        return followUnfollow.isFollowing(userId1, userId2);
    }

}
