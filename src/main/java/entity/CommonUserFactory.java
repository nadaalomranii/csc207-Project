package entity;

/**
 * Factory for creating CommonUser objects.
 */
public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String name, String username, String password, String email) {
        return new User(name, username, password, email);
    }
}
