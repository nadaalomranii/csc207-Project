package entity;

/**
 * Factory for creating users.
 */
public interface UserFactory {
    /**
     * Creates a new user.
     * @param name of the user
     * @param username of the user
     * @param password of this user's account
     * @param email to contact this user
     * @return the new user
     */
    User create(String name, String username, String password, String email);
}
