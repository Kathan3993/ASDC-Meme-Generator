package com.group5.memegenerator.database;

import com.group5.memegenerator.model.AdminObserver;
import com.group5.memegenerator.model.IUserDetails;
import com.group5.memegenerator.model.UserDetails;
import com.group5.memegenerator.model.factory.PasswordEncryptionFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public class UserDetailsDAO implements IUserDetailsDAO {
    String tableName = "users";
    private IDatabaseOperation databaseOperation;


    public UserDetailsDAO(IDatabaseOperation databaseOperation) {
        this.databaseOperation = databaseOperation;
    }

    public static List<IUserDetails> getUserList(ArrayList<HashMap<String, String>> results, IUserDetailsDAO userDetailsDAO) {
        List<IUserDetails> userDetails = null;

        try {
            userDetails = new ArrayList<>();
            for (HashMap<String, String> result : results) {

                IUserDetails meme = new UserDetails(userDetailsDAO);

                meme.setFirstname(result.get("first_name"));
                meme.setLastname(result.get("Last_name"));
                meme.setId(Integer.parseInt(result.get("id")));
                meme.setDob(result.get("dob"));
                meme.setEmail(result.get("email"));
                meme.setPassword(result.get("password"));
                meme.setProfilePicture(result.get("profilepicture"));
                meme.setRole(UserDetails.ROLE.valueOf(result.get("role")));

                userDetails.add(meme);
            }
        } catch (Exception e) {
            System.out.println("error");
        }
        return userDetails;
    }

    @Override
    public String storeUserDetails(String firstname, String lastname, String gender, String dob, String email, String password) {
        String fields = "count(*) as count";
        String endQuery = "where email = '" + email + "'";
        String encryptedPassword = PasswordEncryptionFactory.instance().makePasswordEncryption().encryptPassword(password);
        ArrayList<HashMap<String, String>> response = null;
        try {
            response = databaseOperation.select(fields, tableName, endQuery);
        } catch (Exception e) {
            System.out.println("error in store user details");
        }

        int count = Integer.parseInt(response.get(0).get("count"));
        if (count == 0) {
            fields = "first_name,last_name,gender,dob,email,password";
            String values = "'" + firstname + "','" + lastname + "','" + gender + "','" + dob + "','" + email + "','" + encryptedPassword + "'";
            databaseOperation.insert(tableName, fields, values);
            return "login";
        } else {
            System.out.println("User Exists!!!!!!!!!!!");
            return "registration";
        }
    }

    public List<IUserDetails> getUser(String username) {
        String tableName = "users";
        String fields = "*";
        String endQuery = "where id=" + username + "";

        try {
            ArrayList<HashMap<String, String>> results = databaseOperation.select(fields, tableName, endQuery);
            return UserDetailsDAO.getUserList(results, this);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String encodeProfilePicture(MultipartFile image) {
        try {
            return "data:image/*;base64," + Base64.getEncoder().encodeToString(image.getBytes());
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public boolean updateProfilePic(MultipartFile image, String username) {
        String pic = this.encodeProfilePicture(image);
        String setValues = "profilepicture='" + pic + "'";
        String condition = "id=" + username;

        DatabaseResponse databaseResponse = databaseOperation.update(tableName, setValues, condition);
        if (databaseResponse.equals("SUCCESS")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean editUserProfile(String firstName, String lastName, String gender, String dob, String userId) {

        String setValues = "first_name='" + firstName + "',last_name='"+lastName+"',gender='"+gender+"',dob='"+dob+"'";
        String condition = "id=" + userId;

        DatabaseResponse databaseResponse = databaseOperation.update(tableName, setValues, condition);
        if (databaseResponse.equals("SUCCESS")) {
            return true;
        }
        return false;
    }

    @Override
    public String getUserIdByUserName(String username) {
        String userId = "";
        ArrayList<HashMap<String, String>> results = new ArrayList<>();
        try {
            results = databaseOperation.select("*", "users", "WHERE email='" + username + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Iterator<HashMap<String, String>> iter = results.iterator();
        while (iter.hasNext()) {
            HashMap<String, String> result = iter.next();
            userId = result.get("id");
        }

        return userId;
    }

    @Override
    public List<IUserDetails> loadAdminsFromDatabase(){
        String tableName = "users";
        String fields = "*";
        String endQuery = "where role = 'admin'";

        try {
            ArrayList<HashMap<String, String>> results = databaseOperation.select(fields, tableName, endQuery);
            return UserDetailsDAO.getAdminList(results, this);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<IUserDetails> getAdminList(ArrayList<HashMap<String, String>> results, IUserDetailsDAO userDetailsDAO) {
        List<IUserDetails> admins = null;

        try {
            admins = new ArrayList<>();
            for (HashMap<String, String> result : results) {

                IUserDetails admin = new AdminObserver(userDetailsDAO);

                admin.setFirstname(result.get("first_name"));
                admin.setLastname(result.get("Last_name"));
                admin.setId(Integer.parseInt(result.get("id")));
                admin.setDob(result.get("dob"));
                admin.setEmail(result.get("email"));
                admin.setPassword(result.get("password"));
                admin.setProfilePicture(result.get("profilepicture"));
                admin.setRole(UserDetails.ROLE.valueOf(result.get("role")));

                admins.add(admin);
            }
        } catch (Exception e) {
            System.out.println("error");
        }
        return admins;
    }
}
