package user;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Authenticator {
    public static User loggedUser;

//    public static boolean authenticate(String username, String password) {
//        Map<String, User> userMap = Database.accountDatabase;
//        if (userMap.containsKey(username) && userMap.get(username).getPassword().equals(password)) {
//            loggedUser = userMap.get(username);
//            return true;
//        } else return false;
//    }

    public static boolean authenticate(String username, String password) {
        // Get the userMap instance from AccountDatabase
        Map<String, User> userMap = AccountDatabase.getInstance();

        // Check if the username exists and the password matches
        if (userMap.containsKey(username) && userMap.get(username).getPassword().equals(password)) {
            // Set the loggedUser to the authenticated user
            loggedUser = userMap.get(username);
            return true;
        } else {
            return false;
        }
    }
}