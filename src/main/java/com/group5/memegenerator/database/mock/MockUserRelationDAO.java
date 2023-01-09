package com.group5.memegenerator.database.mock;

import com.group5.memegenerator.database.DatabaseResponse;
import com.group5.memegenerator.database.IUserRelationDAO;
import com.group5.memegenerator.model.UserRelationField;

public class MockUserRelationDAO implements IUserRelationDAO {

    @Override
    public DatabaseResponse incrementCount(String userId1, String userId2, UserRelationField field) {

        if(("PROFILE_VISITS").equals(field.toString()) ||("LIKES").equals(field.toString())||("COMMENTS").equals(field.toString()))
        {
            return DatabaseResponse.SUCCESS;

        }
        return DatabaseResponse.ERROR;
    }

}
