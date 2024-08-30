package user;

import utils.Divider;
import utils.InputValidation;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static user.Authenticator.loggedUser;


//log out function
public class AccountDatabase {
    private static Map<String, User> usersInstance;


//    public static Map<String, User> getInstance() {
//        if (usersInstance == null) {
//            File file = new File("src/data/accounts.txt");
//            if (file.exists()) {
//                usersInstance = loadData();
//            } else usersInstance = new HashMap<>();
//        }
//        return usersInstance;
//    }

    // hard code to test function
    public static Map<String, User> getInstance() {
        if (usersInstance == null) {
            usersInstance = new HashMap<>();

            // Hardcode user instances here
            User user1 = new User( "U00001",
                    "John Doe",
                    new Date(92, 7, 15),
                    "123 Main Street",
                    1234567890L,
                    "john.doe@example.com",
                    User.UserType.MANAGER,
                    true,
                    "password123",
                    "john_doe"              );
            User user2 = new User( "U00002",
                    "kuasd Dis",
                    new Date(82, 6, 5),
                    "153 Last Street",
                    12863267890L,
                    "huy.test@example.com",
                    User.UserType.EMPLOYEE,
                    true,
                    "giahuy2004",
                    "huypham"              );

            Employee employee1 = new Mechanic("U00003",
                    "Hai Nguyen",
                    new Date(02, 4, 25),
                    "RMIT Main Street",
                    1234567890L,
                    "Hai.doe@example.com",
                    true,
                    "hai123",
            "hainguyen");

            Employee employee2 = new Salesperson("U00004",
                    "Dzi Thach",
                    new Date(2004, 1, 5),
                    "TDT Main Street",
                    1234567890L,
                    "Dzi.doe@example.com",
                    true,
                    "dzi123",
            "dzinguyen");

            // Add users to the usersInstance map
            usersInstance.put(user1.getUsername(), user1);
            usersInstance.put(user2.getUsername(), user2);
            usersInstance.put(employee1.getUsername(), employee1);
            usersInstance.put(employee2.getUsername(), employee2);

            // You can add more users as needed
        }
        return usersInstance;
    }


    public static void addUser(User user) {
        usersInstance.put(user.getUsername(), user);
    }

    public static User getUser(String username) {
        return usersInstance.get(username);
    }

    public static void removeUser(String username) {
        usersInstance.remove(username);
    }


    public static void displayInfoUsers() {
        Divider.printDivider();
        System.out.println("User Information:");
        // Get the currently logged-in user
        User loggedUser = Authenticator.loggedUser;
        // Print the logged user's information
        System.out.println(loggedUser);
        Divider.printDivider();
    }

    //the manager can view all user
//    public static void displayAllUsers() {
//        Divider.printDivider();
//        System.out.println("All users:");
//        for (Map.Entry<String, User> entry : usersInstance.entrySet()) {
//            System.out.println(entry.getValue());
//            System.out.println();
//        }

    public static void editProfile() {
        // Ask for the new password
        String newUserPassword = InputValidation.validateString("Please enter your new password: ");

        // Retrieve the logged-in user by username
        User user = usersInstance.get(loggedUser.getUsername());
        // Set the new password
        user.setPassword(newUserPassword);
        System.out.println("Your information has changed successfully!");

    }

    @SuppressWarnings("unchecked")
    private static Map<String, User> loadData() {
        String filePath = "src/data/accounts.txt";
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {

            // Access the data in the loaded Map
            //System.out.println("Accounts loaded successfully:");

            return (Map<String, User>) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}

//    private static Map<String, User> loadData() {
//        String filePath = "src/data/accounts.txt";
//        try (BufferedReader in = new BufferedReader(new FileReader(filePath))) {
//
//            String line;
//            while ((line = in.readLine()) != null) {
//                String[] data = line.split(",");
//                String username = data[0];
//                String password = data[1];
//                String role = data[2];
//
//
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

//    public static void saveToFile() throws IOException {
//        String filename = "src/data/accounts.txt";
//        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
//            out.writeObject(usersInstance);
//        }
//    }
//}