package use_case.signup;

import entity.Course;
import entity.User;

import java.util.List;

/**
 * The DAO for the signup use case.
 */
public interface SignupUserDataAccessInterface {

    /**
     * Checks if the given username exists.
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean userExistsByName(String username);

    /**
     * Saves the user.
     * @param user the user to save
     */
    void save(User user, List<Course> courses);
}
