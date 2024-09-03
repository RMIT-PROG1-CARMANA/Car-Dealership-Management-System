
package user;
import FileHandling.UserDataHandler;
import java.util.Optional;

public class Authenticator {

    // Static field to hold the currently logged-in user
    public static User loggedUser;
    private static UserDataHandler userDAO = new UserDataHandler();
    public Authenticator() {
        this.userDAO = new UserDataHandler();
    }

    /**
     * Authenticates a user based on the provided username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The authenticated User object if authentication is successful, null otherwise.
     */
    public static boolean authenticate(String username, String password) {
        // Retrieve the user from the database
        Optional<User> userOpt = userDAO.findUserByCredentials(username, password);

        // If user is present and credentials match, return the user
        if (userOpt.isPresent()) {
            User user = userOpt.get();

            // Check if the user's status is active
            if (user.isStatus()) {
                loggedUser = user;  // Set the logged-in user
                System.out.println("User " + username + " authenticated successfully.");
                return user.isStatus();
            } else {
                System.out.println("User " + username + " is inactive. Authentication failed.");
            }
        } else {
            System.out.println("Invalid username or password.");
        }

        // Authentication failed, clear loggedUser
        loggedUser = null;
        return Boolean.parseBoolean(null);
    }


//     Logs out the currently logged-in user.

    public static void UserLogOut() {
        if (loggedUser != null) {
            System.out.println("User " + loggedUser.getUsername() + " has been logged out.");
            loggedUser = null;  // Clear the logged-in user
        } else {
            System.out.println("No user is currently logged in.");
        }
    }
    /**
     Checks if a user is currently logged in.

     @return true if a user is logged in, false otherwise.
     */
    public boolean isUserLoggedIn() {
        return loggedUser != null;
    }
}