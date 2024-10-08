package operations;

import crudhandlers.SalesTransactionCRUD;
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
import static user.Authenticator.loggedClient;
import static user.Authenticator.loggedUser;



public class UserService implements UserInterfaces {
    public static void createUserToFile(User newUserManager) {
        List<User> managersList = userDAO.fetchManagerFromDatabase();
        managersList.add(newUserManager);
        userDAO.writeUsersToFile(managersList.toArray(new User[0]));
    }

    private static final UserDataHandler userDAO = new UserDataHandler();
    private static final SalesTransactionCRUD salseDAO = new SalesTransactionCRUD();

    // manager can add user
    @Override
    public void addUser() {
        String userID = InputValidation.validateUserID("Enter User ID (u-XXXX format): ");
        String fullName = InputValidation.validateString("Enter Full Name: ");
        Date dateOfBirth = InputValidation.validateDate("Enter Date of Birth (dd/MM/yyyy): ");
        String address = InputValidation.validateString("Enter Address: ");
        long phoneNumber = InputValidation.validateLong("Enter Phone Number: ");
        String email = InputValidation.validateString("Enter Email: ");
        String username = InputValidation.validateUsername("Enter Username: ");
        String password = InputValidation.validateString("Enter Password: ");
        Boolean status = InputValidation.validateBoolean("Enter Status (true/false): ");
        int userTypeChoice = InputValidation.validateInt("Select User Type (1 for Manager, 2 for Employee, 3 for Client): ");
        User.UserType userType = switch (userTypeChoice) {
            case 1 -> User.UserType.MANAGER;
            case 2 -> User.UserType.EMPLOYEE;
            case 3 -> User.UserType.CLIENT;
            default -> throw new IllegalArgumentException("Invalid user type choice");
        };
        User user;
        if (userType == User.UserType.EMPLOYEE) {
            int positionChoice = InputValidation.validateInt("Select Position (1 for Salesperson, 2 for Mechanic): ");
            Employee.Position position;
            user = switch (positionChoice) {
                case 1 -> {
                    position = Employee.Position.SALESPERSON;
                    yield new Salesperson(userID, fullName, dateOfBirth, address, phoneNumber, email, status, password, username);
                }
                case 2 -> {
                    position = Employee.Position.MECHANIC;
                    yield new Mechanic(userID, fullName, dateOfBirth, address, phoneNumber, email, status, password, username);
                }
                default -> throw new IllegalArgumentException("Invalid position choice");
            };
        } else if (userType == User.UserType.MANAGER) {
            user = new Manager(userID, fullName, dateOfBirth, address, phoneNumber, email, status, password, username);
        } else {
            double totalSpending = 0;
            user = new Client(userID, fullName, dateOfBirth, address, phoneNumber, email, status, password, username,totalSpending ); // Adjust Client constructor
        }


        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Created User with ID: " + userID + " Username :" + username
        );
        createUserToFile(user);
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
    public void displayInfoUsersMembership() {
        Divider.printDivider();
        System.out.println("User Information:");
        // Print user profile details in a formatted way
        System.out.println(CYAN_BOLD + "User Membership Details" + RESET);
        System.out.println(CYAN_BOLD + "===================================" + RESET);
        System.out.printf(GREEN_BOLD + "%-20s" + RESET + ": %s%n", "User Type", loggedClient.getMembership());
        System.out.println(CYAN_BOLD + "===================================" + RESET);
        Divider.printDivider();
    }
    @Override
    public void updateProfile(User loggedUser) {
        if (loggedUser == null) {
            System.out.println("You need to be logged in to edit your profile.");
            return;
        }


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

        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Update profile : " + loggedUser.getUserID()
        );

        System.out.println("Your information has been updated successfully!");
    }
    @Override
    public void updateUser() {
        // Read all users
        User[] users = userDAO.readAllUsers();

        // Get the user ID to update
        String userID = InputValidation.validateExistingUserID("Enter User ID of the user to update (uXXXX format): ");

        // Find the user to update
        User userToUpdate = null;
        for (User user : users) {
            if (user != null && user.getUserID().equals(userID)) {
                userToUpdate = user;
                break;
            }
        }

        if (userToUpdate == null) {
            System.out.println("User with ID " + userID + " not found.");
            return;
        }

        // Update user details
        String fullName = InputValidation.validateString("Enter new Full Name (leave blank to keep current): ");
        if (!fullName.isEmpty()) {
            userToUpdate.setFullName(fullName);
        }

        Date dateOfBirth = InputValidation.validateDate("Enter new Date of Birth (dd/MM/yyyy, leave blank to keep current): ");
        if (dateOfBirth != null) {
            userToUpdate.setDateOfBirth(dateOfBirth);
        }

        String address = InputValidation.validateString("Enter new Address (leave blank to keep current): ");
        if (!address.isEmpty()) {
            userToUpdate.setAddress(address);
        }

        Long phoneNumber = InputValidation.validateLong("Enter new Phone Number (leave blank to keep current): ");
        if (phoneNumber != null) {
            userToUpdate.setPhoneNumber(phoneNumber);
        }

        String email = InputValidation.validateString("Enter new Email (leave blank to keep current): ");
        if (!email.isEmpty()) {
            userToUpdate.setEmail(email);
        }

        String username = InputValidation.validateUsername("Enter new Username (leave blank to keep current): ");
        if (!username.isEmpty()) {
            userToUpdate.setUsername(username);
        }

        String password = InputValidation.validateString("Enter new Password (leave blank to keep current): ");
        if (!password.isEmpty()) {
            userToUpdate.setPassword(password);
        }

        Boolean status = InputValidation.validateBoolean("Enter new Status (true/false, leave blank to keep current): ");
        if (status != null) {
            userToUpdate.setStatus(status);
        }

        // Save updated users back to file
        userDAO.writeUsersToFile(users);

        // Log the update action
        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Updated User with ID: " + userID
        );

        System.out.println("User updated successfully.");
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
    // search user function
    @Override
    public void searchUserByID() {

        String userID = InputValidation.validateExistingUserID("Enter the UserID to search: ");
        System.out.println();
        User[] users = userDAO.readAllUsers();
        boolean found = false;

        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                System.out.println("User found:");
                System.out.println(user); // Assuming User class has a meaningful toString() method
                found = true;
                break;
            }
        }
        // Log the activity
        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Displayed user details for ID: " + userID
        );
        if (!found) {
            System.out.println("User with ID " + userID + " not found.");
        }
    }

    public void updateClient(Client updatedClient) {
        // Read all users from the file
        User[] usersArray = userDAO.readAllUsers();
        if (usersArray == null || usersArray.length == 0) {
            System.err.println("No users to update. The user data is empty or null.");
            return;
        }

        // Convert the array to a list for easier manipulation
        List<User> usersList = new ArrayList<>(Arrays.asList(usersArray));

        // Find and update the client in the list
        boolean clientUpdated = false;
        for (int i = 0; i < usersList.size(); i++) {
            User user = usersList.get(i);
            if (user instanceof Client && user.getUserID().equals(updatedClient.getUserID())) {
                usersList.set(i, updatedClient);  // Replace the old client with the updated one
                clientUpdated = true;
                break;
            }
        }

        if (!clientUpdated) {
            System.err.println("Client with ID " + updatedClient.getUserID() + " not found. No update performed.");
            return;
        }

        // Convert the list back to an array
        User[] updatedUsersArray = usersList.toArray(new User[0]);

        // Write the updated array of users back to the file
        userDAO.writeUsersToFile(updatedUsersArray);
    }



}
