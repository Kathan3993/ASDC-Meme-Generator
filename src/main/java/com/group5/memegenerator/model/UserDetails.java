package com.group5.memegenerator.model;

import com.group5.memegenerator.database.IUserDetailsDAO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class UserDetails implements IUserDetails, ITemplateObserver{

	@Override
	public void templateUploaded() {
		return;
	}

    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String firstname;
    @Getter
    @Setter
    private String lastname;
    @Getter
    @Setter
    private String dob;
    @Getter
    @Setter
    private String gender;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String profilePicture;
    @Getter
    @Setter
    private ROLE role;
    private IUserDetailsDAO userDetailsDAO;

    public UserDetails(IUserDetailsDAO userDetailsDAO) {
        this.userDetailsDAO = userDetailsDAO;
    }

    @Override
    public IUserDetails loadUser(String userId) {

        List<IUserDetails> users = userDetailsDAO.getUser(userId);

        if (users == null) {

            return null;
        } else {

            IUserDetails user = users.get(0);
            return user;
        }
    }

    public IUserDetails loadUser(int userId) {
        return loadUser(userId+"");
    }

    @Override
    public boolean updateProfilePicture(MultipartFile image, String userId) {
        return userDetailsDAO.updateProfilePic(image, userId);
    }

    @Override
    public boolean editProfile(String firstName, String lastName,String gender, String dob,String userId)
    {
        return userDetailsDAO.editUserProfile(firstName,lastName,gender,dob,userId);
    }

    @Override
    public String getUserIdByUserName(String username) {
        return this.userDetailsDAO.getUserIdByUserName(username);
    }

    public enum ROLE {
        user,
        admin
    }
}