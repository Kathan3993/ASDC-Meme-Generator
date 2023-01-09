package com.group5.memegenerator.database;

import com.group5.memegenerator.model.Comments;
import com.group5.memegenerator.model.IComments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommentsDAO implements ICommentsDAO {

    private static String tableName = "Comments";
    private IDatabaseOperation databaseOperation;

    public CommentsDAO() {

    }

    public CommentsDAO(IDatabaseOperation dbOperation) {
        databaseOperation = dbOperation;
    }

    @Override
    public List<IComments> getComments(String id) {
        String fields = "comment_Text, commentTimeStamp";
        String endQuery = "where meme_id = " + id;

        try {

            ArrayList<HashMap<String, String>> responses = databaseOperation.select(fields, tableName, endQuery);
            List<IComments> comments = new ArrayList<>();

            for (HashMap<String, String> response : responses) {
                String commentsText = response.get("comment_Text");
                String timeStamp = response.get("commentTimeStamp");

                IComments comment = new Comments(this);
                comment.setCommentText(commentsText);
                comment.setCommentTimeStamp(timeStamp);

                comments.add(comment);
            }
            return comments;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean setComment(String comment, String memeId, String userId) {
        String fieldNames = "meme_Id, id, comment_Text";
        String value = memeId + "," + userId + ",'" + comment + "'";

        databaseOperation.insert(tableName, fieldNames, value);

        return true;
    }
}
