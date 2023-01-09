package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.database.IUserDetailsDAO;
import com.group5.memegenerator.model.IUserDetails;

public interface IUserDetailsFactory {

    IUserDetails makeUserDetails(IUserDetailsDAO userDetailsDAO);
}