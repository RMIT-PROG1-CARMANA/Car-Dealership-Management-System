package FileHandling;

import user.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO {
    private static final String FILE_NAME = "users.dat";

    // Create a new user (adds user to the file)
    public void createUser(User user) {
        List<User> users = readAllUsers();
        users.add(user);
        writeUsersToFile(users);
    }

    // Read all users from the file
    public List<User> readAllUsers() {
        List<User> users = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            users = (List<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File not found, return empty list
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Update an existing user
    public void updateUser(User updatedUser) {
        List<User> users = readAllUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserID().equals(updatedUser.getUserID())) {
                users.set(i, updatedUser);
                break;
            }
        }
        writeUsersToFile(users);
    }

    // Delete a user by userID
    public void deleteUser(String userID) {
        List<User> users = readAllUsers();
        users.removeIf(user -> user.getUserID().equals(userID));
        writeUsersToFile(users);
    }

    // Find a user by username and password (for login)
    public Optional<User> findUserByCredentials(String username, String password) {
        return readAllUsers().stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst();
    }

    // Write the list of users to the file
    private void writeUsersToFile(List<User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
