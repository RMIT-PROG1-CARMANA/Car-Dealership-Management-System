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
        for (User user : readAllUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    // Fetches managers from the database
    public List<User> fetchManagersFromDatabase() {
        try {
            User[] managersArray = readAllUsers();
            return new ArrayList<>(Arrays.asList(managersArray));
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }



}
