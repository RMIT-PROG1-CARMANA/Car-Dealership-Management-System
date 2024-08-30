package menu;

import menu.UserMenu.EmployeeBaseMenu;
import menu.UserMenu.EmployeeRoles.MechanicMenu;
import menu.UserMenu.EmployeeRoles.SalespersonMenu;
import menu.UserMenu.ManagerMenu;
import user.Employee;
import user.Mechanic;
import user.Salesperson;
import user.User;
import utils.Divider;
import utils.InputValidation;
import java.io.Serializable;
import java.util.Scanner;

import static user.AccountDatabase.displayInfoUsers;
import static user.AccountDatabase.editProfile;


public class Menu  {

    private static User loggedUser;  // Static variable

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

    protected void displayHorizontalLine(int width) {
        menuStyle.printSeparator(width, '-');
    }

    protected void displayMessage(String message) {
        System.out.println(message);
    }

    protected void displayOption(String option) {
        System.out.printf("| %-53s |\n", option);
    }

    protected int promptForInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }
//
//    protected void backToMenu() {
//        System.out.print("Press enter to continue...");
//        scanner.nextLine(); // To consume the remaining newline
//        scanner.nextLine();
//    }
//
    protected void printCentered(String text, int width) {
        menuStyle.printCentered(text, width);
    }

    protected void printSeparator(int width) {
        menuStyle.printSeparator(width, '-');
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
        while (true) {
            // Display menu options
            System.out.println("Welcome ");
            System.out.println("0. View Profile");
            System.out.println("1. Edit Profile");
            System.out.println("2. Go to " + displayUserRole() + " Main Menu");
            System.out.println("3. Exit");

            choice = getValidatedChoice(0, 3);
            // Handle menu options
            switch (choice) {
                case 0:
                    displayInfoUsers();
                    break;
                case 1:
                    editProfile();
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
                        // Implement ClientMenu
                    }
                    break;
                case 3:
                    boolean confirmExit = InputValidation.validateBoolean("Are you sure you want to exit? (yes/no): ");
                    if (confirmExit) {
                        input.close();
                        System.exit(0);
                    }
                    Divider.printDivider(); // Print a divider for clarity
                    continue;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            Divider.printDivider();
        }
    }
}
