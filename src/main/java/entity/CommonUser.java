package entity;

/**
 * The representation of a user in this program.
 */
public interface CommonUser {

    /**
     * Returns the name of the user.
     * @return the name of the user.
     */
    String getName();

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getUsername();

    /**
     * Returns the email of the user.
     * @return the email of the user.
     */
    String getEmail();

    /**
     * Returns the password of the user.
     * @return the user's password.
     */
    String getPassword();
}
