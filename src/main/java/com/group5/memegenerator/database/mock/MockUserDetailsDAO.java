package com.group5.memegenerator.database.mock;

import com.group5.memegenerator.database.IUserDetailsDAO;
import com.group5.memegenerator.database.factory.UserDetailsDAOFactory;
import com.group5.memegenerator.model.IUserDetails;
import com.group5.memegenerator.model.factory.UserDetailsFactory;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class MockUserDetailsDAO implements IUserDetailsDAO {
    @Override
    public String storeUserDetails(String firstname, String lastname, String gender, String dob, String email, String password) {
        if (firstname == null || lastname == null || gender == null || dob == null || email == null || password == null) {
            return null;
        }
        return "notnull";
    }

    @Override
    public List<IUserDetails> getUser(String username) {

        List<IUserDetails> user = new ArrayList<>();

        IUserDetailsDAO userDetailsDAO = UserDetailsDAOFactory.instance().makeMockUserDetailsDAOFactory();
        IUserDetails userDetails = UserDetailsFactory.instance().makeUserDetails(userDetailsDAO);

        userDetails.setPassword("12345678");
        userDetails.setEmail("kathan@dal.ca");
        userDetails.setFirstname("kathan");
        userDetails.setLastname("patel");
        userDetails.setDob("2000-07-28");
        userDetails.setId(6);

        user.add(userDetails);

        if (userDetails.getId() == Integer.parseInt(username)) {

            return user;
        }
        return null;
    }

    @Override
    public String encodeProfilePicture(MultipartFile memeTemplate) {
        return null;
    }

    @Override
    public boolean editUserProfile(String firstName, String lastName, String gender, String dob, String userId) {
        if(firstName==""||lastName==""||gender==""||dob=="")
        {
            return false;
        }
        return true;
    }

    @Override
    public String getUserIdByUserName(String username) {
        if (username == "") {
            return null;
        }
        return "kathan@dal.ca";

    }

    @Override
    public boolean updateProfilePic(MultipartFile image, String username) {
        if(image == null||username==null)
        {
            return false;
        }
        return true;
    }

    @Override
    public List<IUserDetails> loadAdminsFromDatabase() {
        return null;
    }
}
