package FileHandling;

import user.User;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UserFileOperations {

    private static final String USER_DATABASE_PATH = "src/DataBase/UserDatabase.txt";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    // Method to save a User object to the file
    public static void saveUser(User user) {
        try {
            FileHandler.writeToFile(USER_DATABASE_PATH, userToString(user), true);
            System.out.println("User saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }

    // Method to fetch a User object by userID from the file
    public static User fetchUser(String userID) {
        try (BufferedReader reader = FileHandler.readFromFile(USER_DATABASE_PATH)) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = stringToUser(line);
                if (user != null && user.getUserID().equals(userID)) {
                    return user;
                }
            }
        } catch (IOException e) {
            System.err.println("Error fetching user: " + e.getMessage());
        }
        return null; // If the user is not found
    }

    // Method to remove a User object by userID from the file
    public static boolean removeUserById(String userID) {
        File inputFile = new File(USER_DATABASE_PATH);
        File tempFile = new File("src/DataBase/tempUserDatabase.txt");

        boolean removed = false;

        try (BufferedReader reader = FileHandler.readFromFile(USER_DATABASE_PATH);
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                User user = stringToUser(line);
                if (user != null && user.getUserID().equals(userID)) {
                    removed = true;
                    continue; // Skip writing this user to the temp file
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error removing user: " + e.getMessage());
            return false;
        }

        // Replace original file with the temp file
        if (!FileHandler.deleteFile(inputFile)) {
            System.err.println("Error deleting the original file.");
            return false;
        }
        if (!FileHandler.renameFile(tempFile, inputFile)) {
            System.err.println("Error renaming the temp file.");
            return false;
        }

        if (removed) {
            System.out.println("User removed successfully.");
        } else {
            System.out.println("User not found.");
        }

        return removed;
    }

    // Method to get all Users from the file and return them as an ArrayList
    public static ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();

        try (BufferedReader reader = FileHandler.readFromFile(USER_DATABASE_PATH)) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = stringToUser(line);
                if (user != null) {
                    users.add(user);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading users: " + e.getMessage());
        }

        return users;
    }

    // Helper method to convert a User object to a String for saving
    private static String userToString(User user) {
        return String.join(";",
                user.getUserID(),
                user.getFullName(),
                user.getDateOfBirth() != null ? DATE_FORMAT.format(user.getDateOfBirth()) : "",
                user.getAddress(),
                String.valueOf(user.getPhoneNumber()),
                user.getEmail(),
                user.getUserType().name(),
                Boolean.toString(user.isStatus()),
                user.getPassword(),
                user.getUsername()
        );
    }

    // Helper method to convert a String from the file back to a User object
    private static User stringToUser(String userString) {
        String[] fields = userString.split(";");
        if (fields.length != 10) {
            return null; // Invalid user record
        }
        User user = new User();
        user.setUserID(fields[0]);
        user.setFullName(fields[1]);
        try {
            user.setDateOfBirth(fields[2].isEmpty() ? null : DATE_FORMAT.parse(fields[2]));
        } catch (Exception e) {
            user.setDateOfBirth(null);
        }
        user.setAddress(fields[3]);
        long phoneNumber = 0;
        try {
            phoneNumber = Long.parseLong(fields[4]); // Convert String to long
        } catch (NumberFormatException e) {
            e.printStackTrace(); // Handle parsing exception
        }
        user.setEmail(fields[5]);
        user.setUserType(User.UserType.valueOf(fields[6]));
        user.setStatus(Boolean.parseBoolean(fields[7]));
        user.setPassword(fields[8]);
        user.setUsername(fields[9]);
        return user;
    }
}
