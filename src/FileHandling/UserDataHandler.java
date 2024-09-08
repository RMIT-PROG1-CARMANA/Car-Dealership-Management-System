package FileHandling;

import user.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserDataHandler {
    private static final String FILE_USER_NAME = "src/Database/users.txt";

    // Read all users from the file
    public User[] readAllUsers() {
        User[] users = new User[0];  // Default to empty array
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_USER_NAME))) {
            users = (User[]) ois.readObject();
        } catch (FileNotFoundException e) {
            // File not found, return empty array
            System.err.println("No user data found. Returning an empty array.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Writes the array of users to the file
    public void writeUsersToFile(User[] users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_USER_NAME))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.err.println("Failed to write users to file: " + FILE_USER_NAME);
            e.printStackTrace();  // Consider logging this in production
        }
    }

    // Find a user by username and password (for login)
    public Optional<User> findUserByCredentials(String username, String password) {
        try {
            // Read all users from the file
            User[] usersArray = readAllUsers();  // No need for FILE_USER_NAME as a parameter here
            List<User> usersList = new ArrayList<>(Arrays.asList(usersArray));

            // Check for matching credentials in the list of users
            for (User user : usersList) {
                if (user != null && username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    return Optional.of(user);  // Return the matched user wrapped in an Optional
                }
            }
        } catch (Exception e) {
            // Handle exceptions such as file not found or deserialization errors
            System.err.println("Error reading users or no users exist: " + e.getMessage());
        }

        // If no matching user is found or an error occurs, return Optional.empty()
        return Optional.empty();
    }


        // Fetches manager from the database
        public List<User> fetchManagerFromDatabase () {
            try {
                User[] managersArray = readAllUsers();
                return new ArrayList<>(Arrays.asList(managersArray));
            } catch (Exception e) {
                return new ArrayList<>();
            }
        }


    }

