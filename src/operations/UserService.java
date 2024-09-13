package operations;

import filehandling.UserDataHandler;
import interfaces.UserInterfaces;
import logsystem.*;

import sales.SalesTransaction;
import user.*;
import utils.Divider;
import utils.InputValidation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static menu.MenuStyle.*;
import static user.Authenticator.loggedUser;



public class UserService implements UserInterfaces {

    public static void addUser(User newUserManager) {
        List<User> managersList = userDAO.fetchManagerFromDatabase();
        managersList.add(newUserManager);
        userDAO.writeUsersToFile(managersList.toArray(new User[0]));
    }

    private static final UserDataHandler userDAO = new UserDataHandler();

    // manager can add user
    @Override
    public void createUser() {
        String userID = InputValidation.validateUserID("Enter User ID (uXXXX format): ");
        String fullName = InputValidation.validateString("Enter Full Name: ");
        Date dateOfBirth = InputValidation.validateDate("Enter Date of Birth (dd/MM/yyyy): ");
        String address = InputValidation.validateString("Enter Address: ");
        long phoneNumber = InputValidation.validateLong("Enter Phone Number: ");
        String email = InputValidation.validateString("Enter Email: ");
        String username = InputValidation.validateUsername("Enter Username: ");
        String password = InputValidation.validateString("Enter Password: ");
        Boolean status = InputValidation.validateBoolean("Enter Status (true/false): ");
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
        User user;
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
            user = new Manager(userID, fullName, dateOfBirth, address, phoneNumber, email, status, password, username);
        } else {
            List<SalesTransaction> transactions = new ArrayList<>();  // Use empty list if no transactions
            user = new Client(userID, fullName, dateOfBirth, address, phoneNumber, email, status, password, username,transactions ); // Adjust Client constructor
        }


        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Created User with ID: " + userID + " Username :" + username
        );
        addUser(user);
        System.out.println("User created successfully.");
    }

    // Adds a new manager to the list and saves it to the file
    @Override
    public void displayAllUsers() {
        User[] users = userDAO.readAllUsers();
        for (User user : users) {
            System.out.println(user);
            System.out.println("--------------");
        }
    }
    @Override
    public void displayInfoUsers() {
        Divider.printDivider();
        System.out.println("User Information:");
        if (loggedUser != null) {
            // Print user profile details in a formatted way
            System.out.println(CYAN_BOLD + "User Profile Details" + RESET);
            System.out.println(CYAN_BOLD + "===================================" + RESET);
            System.out.printf(GREEN_BOLD + "%-20s" + RESET + ": %s%n", "Full Name", loggedUser.getFullName());
            System.out.printf(GREEN_BOLD + "%-20s" + RESET + ": %s%n", "Username", loggedUser.getUsername());
            System.out.printf(GREEN_BOLD + "%-20s" + RESET + ": %s%n", "User ID", loggedUser.getUserID());
            System.out.printf(GREEN_BOLD + "%-20s" + RESET + ": %s%n", "Email", loggedUser.getEmail());
            System.out.printf(GREEN_BOLD + "%-20s" + RESET + ": %s%n", "Phone Number", loggedUser.getPhoneNumber());
            System.out.printf(GREEN_BOLD + "%-20s" + RESET + ": %s%n", "Address", loggedUser.getAddress());
            System.out.printf(GREEN_BOLD + "%-20s" + RESET + ": %s%n", "Date of Birth", loggedUser.getDateOfBirth());
            System.out.printf(GREEN_BOLD + "%-20s" + RESET + ": %s%n", "Status", (loggedUser.isStatus() ? "Active" : "Inactive"));
            System.out.printf(GREEN_BOLD + "%-20s" + RESET + ": %s%n", "User Type", loggedUser.getUserType());
            System.out.println(CYAN_BOLD + "===================================" + RESET);

        } else {
            System.out.println("No user is currently logged in.");
        }
        Divider.printDivider();
    }

    @Override
    public void editProfile(User loggedUser) {
        if (loggedUser == null) {
            System.out.println("You need to be logged in to edit your profile.");
            return;
        }

        // Update User ID
        String newUserID = InputValidation.validateUserID("Enter new user ID: ");
        loggedUser.setUserID(newUserID);

        // Update full name
        String newFullName = InputValidation.validateString("Enter new full name: ");
        loggedUser.setFullName(newFullName);

        // Update date of birth
        Date newDateOfBirth = InputValidation.validateDate("Enter new date of birth (dd/mm/yyyy): ");
        loggedUser.setDateOfBirth(newDateOfBirth);

        // Update address
        String newAddress = InputValidation.validateString("Enter new address: ");
        loggedUser.setAddress(newAddress);

        // Update phone number
        long newPhoneNumber = InputValidation.validateLong("Enter new phone number: ");
        loggedUser.setPhoneNumber(newPhoneNumber);

        // Update email
        String newEmail = InputValidation.validateString("Enter new email: ");
        loggedUser.setEmail(newEmail);

// Update user type
        String userTypeInput = InputValidation.validateString("Enter new user type (MANAGER, EMPLOYEE, CLIENT): ");
        try {
            User.UserType newUserType = User.UserType.valueOf(userTypeInput.toUpperCase());
            loggedUser.setUserType(newUserType);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid user type. Please enter one of the following: MANAGER, EMPLOYEE, CLIENT.");
        }


        // Update status
        boolean newStatus = InputValidation.validateBoolean("Enter new status (true for active, false for inactive): ");
        loggedUser.setStatus(newStatus);

        // Update password
        String newPassword = InputValidation.validateString("Enter new password: ");
        loggedUser.setPassword(newPassword);

        // Update username
        String newUsername = InputValidation.validateUsername("Enter new username: ");
        loggedUser.setUsername(newUsername);

        System.out.println("Your information has been updated successfully!");
    }
    @Override
    public void deleteUser() {
        System.out.println();

        // Fetch users as an array
        User[] userList = userDAO.readAllUsers();
        if (userList == null) {
            System.out.println("Error fetching user data. Please try again later.");
            return; // Exit if there's an issue fetching users
        }
        // Print user information in a single line
        System.out.println("User Information:");
        for (User user : userList) {
            if (user != null) {
                System.out.printf("Role: %-10s | UserID: %-10s | Username: %-15s%n",
                        user.getUserType(), user.getUserID(), user.getUsername());
                System.out.println("--------------");
            }
        }
        // Validate and retrieve the username to delete
        String usernameToDelete = InputValidation.validateExistingUserID("Enter username to delete: ");
        System.out.println();



        List<User> usersList = new ArrayList<>(Arrays.asList(userList));
        boolean isDeleted = false;

        // Remove the user from the list
        usersList.removeIf(user -> user != null && user.getUsername().equals(usernameToDelete));

        if (usersList.size() < userList.length) {  // Check if a user was removed
            userDAO.writeUsersToFile(usersList.toArray(new User[0]));  // Write the updated list back to the file
            System.out.println("User with username " + usernameToDelete + " deleted successfully.");
            isDeleted = true;
            // Log the deletion
            String logID = ActivityLog.generateLogID();
            ActivityLogService.logActivity(
                    logID,
                    new Date(),
                    loggedUser.getUsername(),
                    loggedUser.getUserID(),
                    "Deleted User with username: " + usernameToDelete
            );

        }

        if (!isDeleted) {
            System.out.println("No user found with the given username.");
        }

            }

}
