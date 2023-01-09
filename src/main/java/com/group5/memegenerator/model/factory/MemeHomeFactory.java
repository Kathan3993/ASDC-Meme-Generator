package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.database.IMemeHomeDAO;
import com.group5.memegenerator.database.IUserProfileDAO;
import com.group5.memegenerator.model.*;

public class MemeHomeFactory implements IMemeHomeFactory{
    private static IMemeHomeFactory memeHomeFactory;
    private MemeHomeFactory() {

    }

    public static IMemeHomeFactory instance() {
        if (memeHomeFactory == null) {
            memeHomeFactory = new MemeHomeFactory();
        }
        return memeHomeFactory;
    }

    @Override
    public IMemeHome makeMemeHome(IMemeHomeDAO memeHomeDAO) {
        return new MemeHome(memeHomeDAO);
    }

    @Override
    public IMemeUser makeMemeUser(IUserProfileDAO userProfileDAO) {
        return new MemeUser(userProfileDAO);
    }

}
