package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.database.IMemeHomeDAO;
import com.group5.memegenerator.database.IUserProfileDAO;
import com.group5.memegenerator.model.IMemeHome;
import com.group5.memegenerator.model.IMemeUser;

public interface IMemeHomeFactory {

    IMemeHome makeMemeHome(IMemeHomeDAO memeHomeDAO);

    IMemeUser makeMemeUser(IUserProfileDAO userProfileDAO);
}
