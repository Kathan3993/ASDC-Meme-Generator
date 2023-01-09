package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.database.IUserDetailsDAO;
import com.group5.memegenerator.model.IUserDetails;
import com.group5.memegenerator.model.UserDetails;

public class UserDetailsFactory implements IUserDetailsFactory {

    private static IUserDetailsFactory userDetailsFactory;

    private UserDetailsFactory(){

    }

    public static IUserDetailsFactory instance(){
        if(userDetailsFactory==null)
        {
            userDetailsFactory=new UserDetailsFactory();
        }
        return userDetailsFactory;
    }

    @Override
    public IUserDetails makeUserDetails(IUserDetailsDAO userDetailsDAO) {
        return new UserDetails(userDetailsDAO);
    }
}
