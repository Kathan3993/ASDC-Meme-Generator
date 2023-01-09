package com.group5.memegenerator.database.factory;

import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.ILikeDAO;
import com.group5.memegenerator.database.IUserDetailsDAO;
import com.group5.memegenerator.database.UserDetailsDAO;
import com.group5.memegenerator.database.mock.MockUserDetailsDAO;

public class UserDetailsDAOFactory implements IUserDetailsDAOFactory{

    private static IUserDetailsDAOFactory userDetailsDAOFactory = null;

    private UserDetailsDAOFactory (){

    }

    public static IUserDetailsDAOFactory instance (){
        if(userDetailsDAOFactory == null){
            userDetailsDAOFactory = new UserDetailsDAOFactory();
        }
        return new UserDetailsDAOFactory();
    }

    @Override
    public IUserDetailsDAO makeUserDetailsDAO(IDatabaseOperation databaseOperation) {
        return new UserDetailsDAO(databaseOperation);
    }

    @Override
    public IUserDetailsDAO makeMockUserDetailsDAOFactory(){
        return new MockUserDetailsDAO();
    }
}
