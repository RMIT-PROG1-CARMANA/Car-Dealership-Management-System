import FileHandling.UserDAO;
import menu.Menu;
import user.*;
import utils.Divider;

import java.util.Date;
import java.util.Scanner;

public class Main {
    private static Menu appMenu;
    private static User loggedUser = null;

    public static void main(String[] args) {
        // Run the program
        // While loop is used here because when user logout it returns false, which is inverted and becomes true, so the loop continues
        do {
            loggedUser = displayHomePage();
            if (loggedUser != null) {
                appMenu = new Menu();
                Menu.setLoggedUser(loggedUser);  // Set the loggedUser for Menu
            }
        } while (loggedUser != null && appMenu.run());
    }

    //Login logic
    private static User displayHomePage() {
        // Login
        Divider.printDivider();
        System.out.println("Please login to an account");
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Username: ");
            String username = input.nextLine().trim();

            System.out.print("Password: ");
            String password = input.nextLine().trim();

            if (username.equals("-1") || password.equals("-1")) {
                return null;  // Exit and return null if user enters -1
            }

            if (Authenticator.authenticate(username, password)) {
                System.out.println("Login successful!");
                Divider.printDivider();
                System.out.println("Welcome back, " + username + "!");
                return Authenticator.loggedUser;  // Return the authenticated user
            } else {
                System.out.println("Login failed! Please try again.");
            }
        }

    }


    public class UserInputHandler {
        private static UserDAO userDAO = new UserDAO();

        public static void createUser() {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter User ID (u-number format): ");
            String userID = scanner.nextLine();
            System.out.print("Enter Full Name: ");
            String fullName = scanner.nextLine();
            System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
            Date dateOfBirth = new Date(scanner.nextLine()); // Simple date parsing, improve as needed
            System.out.print("Enter Address: ");
            String address = scanner.nextLine();
            System.out.print("Enter Phone Number: ");
            long phoneNumber = Long.parseLong(scanner.nextLine());
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            System.out.print("Enter Username: ");
            String username = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();
            System.out.print("Enter Status (true/false): ");
            boolean status = Boolean.parseBoolean(scanner.nextLine());

            System.out.println("Select User Type (1 for Manager, 2 for Employee, 3 for Client): ");
            int userTypeChoice = Integer.parseInt(scanner.nextLine());
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
                System.out.println("Select Position (1 for Salesperson, 2 for Mechanic): ");
                int positionChoice = Integer.parseInt(scanner.nextLine());
                Employee.Position position;
                switch (positionChoice) {
                    case 1:
                        position = Employee.Position.SALESPERSON;
                        user = new Salesperson(userID, fullName, dateOfBirth, address, phoneNumber, email, status,username, password);
                        break;
                    case 2:
                        position = Employee.Position.MECHANIC;
                        user = new Mechanic(userID, fullName, dateOfBirth, address, phoneNumber, email, status,username, password);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid position choice");
                }
            } else {
                user = (userType == User.UserType.MANAGER) ?
                        new Manager(userID, fullName, dateOfBirth, address, phoneNumber, email, username, status, password) :
                        new Client(userID, fullName, dateOfBirth, address, phoneNumber, email, username, status, password);
            }

            userDAO.createUser(user);
            System.out.println("User created successfully.");
        }

        public static void main(String[] args) {
            createUser();
        }
    }

}
