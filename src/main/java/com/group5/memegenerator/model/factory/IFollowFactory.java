package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.database.IFollowDAO;
import com.group5.memegenerator.model.IFollow;

public interface IFollowFactory {
    public IFollow makeFollow(IFollowDAO followDAO);
}
