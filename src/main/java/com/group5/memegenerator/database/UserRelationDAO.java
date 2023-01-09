package com.group5.memegenerator.database;

import com.group5.memegenerator.model.UserRelationField;

public class UserRelationDAO implements IUserRelationDAO {

    private IDatabaseOperation databaseOperation;

    public UserRelationDAO(IDatabaseOperation databaseOperation) {
        this.databaseOperation = databaseOperation;
    }

    public DatabaseResponse incrementCount(String userId1, String userId2, UserRelationField field) {
        String targetField = field.toString().toLowerCase();
        String fields = targetField + "+=" + targetField + "+1";
        String conditions = "userId1='" + userId1 + "' AND userId2='" + userId2 + "'";
        DatabaseResponse result;
        try {
            result = databaseOperation.update("user_relation", fields, conditions);
        } catch (Exception exception) {
            return DatabaseResponse.ERROR;
        }
        return result;
    }

}
