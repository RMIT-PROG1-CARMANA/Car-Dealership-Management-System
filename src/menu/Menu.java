package menu;

import interfaces.UserInterfaces;
import menu.UserMenu.ClientMenu;
import menu.UserMenu.EmployeeBaseMenu;
import menu.UserMenu.EmployeeRolesMenu.MechanicMenu;
import menu.UserMenu.EmployeeRolesMenu.SalespersonMenu;
import menu.UserMenu.ManagerMenu;
import operations.UserService;
import user.Mechanic;
import user.Salesperson;
import user.User;
import user.Authenticator;
import utils.Divider;
import utils.InputValidation;

import java.util.Scanner;

import static menu.MenuStyle.*;




public class Menu  {

    private static User loggedUser;  // Static variable

    public Menu() {
    }
    static UserInterfaces userService = new UserService();

    // Method to set loggedUser
    public static void setLoggedUser(User user) {
        loggedUser = user;
    }
    protected Scanner scanner = new Scanner(System.in);
    protected MenuStyle menuStyle = new MenuStyle();

    public void displayMenuHeader(String menuName, int... widths) {
        int totalWidth = 0;
        for (int width : widths) {
            // 3 extra characters account for spaces and the "|"
            totalWidth += width + 3;
        }

        // Delegate menu header printing to MenuStyle
        menuStyle.printMenuHeader(menuName, totalWidth);
    }

    protected void displayOption(String option) {
        System.out.printf("| %-53s |\n", option);
    }

    protected int getValidatedChoice(int min, int max) {
        return InputValidation.validateInt(
                v -> v >= min && v <= max, // Validate that input is within the specified range
                "Enter Selection: ",       // Prompt message
                "Invalid input. Please enter a number between " + min + " and " + max + "." // Error message
        );
    }

    // Method to display the role of the logged-in user
    public String displayUserRole() {
        if (loggedUser.getUserType() == User.UserType.EMPLOYEE) {
            // Check if the loggedUser is an instance of Salesperson or Mechanic
            if (loggedUser instanceof Salesperson) {
                // position for Salesperson
                return ((Salesperson) loggedUser).getPosition().name();
            } else if (loggedUser instanceof Mechanic) {
                //position for Mechanic
                return ((Mechanic) loggedUser).getPosition().name();
            }
        }
        // For other types
        return loggedUser.getUserType().name();
    }

    public boolean run(){
        Scanner input = new Scanner(System.in);
        int choice;
        boolean shouldContinue = true;
        while (shouldContinue) {
            // Display menu options
            System.out.println();
            System.out.println(CYAN_BOLD + "=====================================" + RESET);
            System.out.println(CYAN_BOLD + "         Welcome back, " + loggedUser.getUsername() + "!" + RESET);
            System.out.println(CYAN_BOLD + "=====================================" + RESET);
            System.out.println(YELLOW_BOLD + "Please choose an option:" + RESET);
            System.out.println();
            System.out.println(GREEN_BOLD + "0. " + RESET + "View Profile");
            System.out.println(GREEN_BOLD + "1. " + RESET + "Edit User");
            System.out.println(GREEN_BOLD + "2. " + RESET + "Go to " + displayUserRole() + " Main Menu");
            System.out.println(GREEN_BOLD + "3. " + RESET + "Log Out");
            System.out.println(GREEN_BOLD + "4. " + RESET + "Exit");
            System.out.println(CYAN_BOLD + "=====================================" + RESET);

            choice = getValidatedChoice(0, 4);
            // Handle menu options
            switch (choice) {
                case 0:
                    userService.displayInfoUsers();
                    break;
                case 1:
                    userService.createUser();
                    break;
                case 2:
                    // Navigate to the appropriate menu based on the user's role
                    User.UserType userType = loggedUser.getUserType();
                    if (userType == User.UserType.MANAGER) {
                        ManagerMenu managerMenu = new ManagerMenu();
                        managerMenu.displayManagerMenu();
                    } else if (userType == User.UserType.EMPLOYEE) {
                        if (loggedUser instanceof Salesperson) {
                            //  SalespersonMenu
                            EmployeeBaseMenu salesPersonMenu = new SalespersonMenu();
                            salesPersonMenu.displayEmployeeMenu();
                        } else if (loggedUser instanceof Mechanic) {
                            //  MechanicMenu
                            EmployeeBaseMenu mechanicMenu = new MechanicMenu();
                            mechanicMenu.displayEmployeeMenu();
                        }
                    } else if (userType == User.UserType.CLIENT) {
                        // ClientMenu
                        ClientMenu clientMenu = new ClientMenu();
                        clientMenu.displayClientMenu();
                    }
                    break;
                case 3:
                    boolean confirmLogout = InputValidation.validateBoolean("Are you sure you want to log out? (yes/no): ");
                    if (confirmLogout) {
                        System.out.println("Logging out...");
                        Authenticator.UserLogOut(); // Call the logout function
                        shouldContinue = false;  // Exit the loop to return to the login menu
                    }
                    break;

                case 4:
                    boolean confirmExit = InputValidation.validateBoolean("Are you sure you want to exit? (yes/no): ");
                    if (confirmExit) {
                        input.close();
                        System.out.println("Exiting the application... Goodbye!");
                        System.exit(0);

                    }
                    Divider.printDivider();
                    continue;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            Divider.printDivider();
        }
        return shouldContinue;
    }
}
