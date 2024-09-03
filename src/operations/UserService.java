package operations;

import FileHandling.UserDataHandler;
import user.*;
import utils.Divider;
import utils.InputValidation;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static user.Authenticator.loggedUser;


public class UserService {

    public static void addUser(User newUserManager) {
        List<User> managersList = userDAO.fetchManagersFromDatabase();
        managersList.add(newUserManager);
        userDAO.writeUsersToFile(managersList.toArray(new User[0]));
    }
    private static final UserDataHandler userDAO = new UserDataHandler();

    // manager can add user
    public static void createUser(){
        String userID = InputValidation.validateString("Enter User ID (u-number format): ");
        String fullName = InputValidation.validateString("Enter Full Name: ");
        Date dateOfBirth = InputValidation.validateDate("Enter Date of Birth: ");
        String address = InputValidation.validateString("Enter Address: ");
        long phoneNumber = InputValidation.validateLong("Enter Phone Number: ");
        String email = InputValidation.validateString("Enter email: ");
        String username = InputValidation.validateString("Enter username: ");
        String password = InputValidation.validateString("Enter password: ");
        Boolean status = InputValidation.validateBoolean("Enter status (true/false): ");
        int userTypeChoice = InputValidation.validateInt("Select User Type (1 for Manager, 2 for Employee, 3 for Client): ");
        User.UserType userType;
        switch (userTypeChoice) {
            case 1:
                userType = User.UserType.MANAGER;
                break;
            case 2:
                userType = User.UserType.EMPLOYEE;
                break;
            case 3:
                userType = User.UserType.CLIENT;
                break;
            default:
                throw new IllegalArgumentException("Invalid user type choice");
        }
        User user = null;
        if (userType == User.UserType.EMPLOYEE) {
            int positionChoice = InputValidation.validateInt("Select Position (1 for Salesperson, 2 for Mechanic): ");
            Employee.Position position;
            switch (positionChoice) {
                case 1:
                    position = Employee.Position.SALESPERSON;
                    user = new Salesperson(userID, fullName, dateOfBirth, address, phoneNumber, email, status, password, username);
                    break;
                case 2:
                    position = Employee.Position.MECHANIC;
                    user = new Mechanic(userID, fullName, dateOfBirth, address, phoneNumber, email, status, password, username);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid position choice");
            }
        } else if (userType == User.UserType.MANAGER) {
            user = new Manager(userID, fullName, dateOfBirth, address, phoneNumber, email, status,password, username);
        } else {
            user = new Client(userID, fullName, dateOfBirth, address, phoneNumber, email, status, password, username, null, 1.0); // Adjust Client constructor
        }

        addUser(user);
        System.out.println("User created successfully.");
    }

    // Adds a new manager to the list and saves it to the file
    public static void displayAllUsers() {
        User[] users = userDAO.readAllUsers();
        for (User user : users) {
            System.out.println(user);
            System.out.println("--------------");
        }
    }

    public static void displayInfoUsers() {
        Divider.printDivider();
        System.out.println("User Information:");
        if (loggedUser != null) {
            System.out.println(loggedUser);
        } else {
            System.out.println("No user is currently logged in.");
        }
        Divider.printDivider();
    }


//    public static void editProfile() {
//        if (loggedUser == null) {
//            System.out.println("You need to be logged in to edit your profile.");
//            return;
//        }
//
//        // Ask for the new password
//        String newUserPassword = InputValidation.validateString("Please enter your new password: ");
//
//        // Set the new password
//        loggedUser.setPassword(newUserPassword);
//
//        // Update in the database
//        usersInstance.put(loggedUser.getUsername(), loggedUser);
//        saveData();  // Save data after updating the user profile
//
//        System.out.println("Your information has been updated successfully!");
//    }

    public static void deleteUser() {
        System.out.println();

        String usernameToDelete = InputValidation.validateString("Enter username to delete: ");
        System.out.println();

        User[] userList = userDAO.readAllUsers();  // Fetch users as an array

        boolean isDeleted = false;
        User[] remainingUsers = new User[userList.length - 1];
        int index = 0;

        for (User user : userList) {
            if (!user.getUsername().equals(usernameToDelete)) {
                if (index < remainingUsers.length) {
                    remainingUsers[index++] = user;
                }
            } else {
                isDeleted = true;
            }
        }
        if (isDeleted) {
            userDAO.writeUsersToFile(remainingUsers);  // Write the updated array back to the file
            System.out.println("User with username " + usernameToDelete + " deleted successfully.");
        } else {
            System.out.println("No user found with the given username.");
        }
    }

}
