package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.database.IFollowDAO;
import com.group5.memegenerator.model.Follow;
import com.group5.memegenerator.model.IFollow;

public class FollowFactory implements IFollowFactory{
    private static IFollowFactory followFactory = null;

    private FollowFactory() {
    }

    public static IFollowFactory instance() {
        if (followFactory == null) {
            followFactory = new FollowFactory();
        }
        return followFactory;
    }

    @Override
    public IFollow makeFollow(IFollowDAO followDAO) {
        return new Follow(followDAO);
    }

}
