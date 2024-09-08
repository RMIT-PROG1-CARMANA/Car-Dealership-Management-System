package menu.UserMenu.EmployeeRoles;


import menu.UserMenu.EmployeeBaseMenu;
import user.Authenticator;
import utils.Divider;
import utils.*;
import java.util.*;

import static menu.MenuStyle.*;

public class MechanicMenu extends EmployeeBaseMenu {
    @Override
    public void displayEmployeeMenu() {
        int choice;
        Scanner scanner = new Scanner(System.in);

        do {
            // Clear the screen
            ClearScreen.clear();

            System.out.println(CYAN_BOLD + "=====================================" + RESET);
            System.out.println(CYAN_BOLD + "       Welcome, Mechanic!" + RESET);
            System.out.println(CYAN_BOLD + "=====================================" + RESET);

            displayMenuHeader("Mechanic Menu", 42);
            displayOption(GREEN_BOLD + "0. " + RESET + "Record Service Transaction");
            displayOption(GREEN_BOLD + "1. " + RESET + "View Service Transactions");
            displayOption(GREEN_BOLD + "2. " + RESET + "Check Service Schedule");
            displayOption(GREEN_BOLD + "3. " + RESET + "Update Vehicle Information");
            displayOption(GREEN_BOLD + "4. " + RESET + "Generate Service Report");
            displayOption(GREEN_BOLD + "5. " + RESET + "Manage Parts Inventory");
            displayOption(GREEN_BOLD + "6. " + RESET + "Back to Main Menu");
            displayOption(GREEN_BOLD + "7. " + RESET + "Exit");
            Divider.printDivider(47);

            System.out.print("Enter Selection (0-7): ");
            choice = getValidatedChoice(0, 7);

            switch (choice) {
                case 0:
                    break;

                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 6:
                    // Return to main menu
                    boolean confirmBack = InputValidation.validateBoolean("Are you sure you want to back to Main Menu? (yes/no): ");
                    if (confirmBack) {
                        System.out.println("Returning to main menu...");
                    }
                    return; // Exits the current menu loop and returns to the Menu class

                case 7:
                    boolean confirmExit = InputValidation.validateBoolean("Are you sure you want to exit? (yes/no): ");
                    if (confirmExit) {
                        System.out.println("Exiting the application... Goodbye!");
                        Authenticator.UserLogOut(); // Log out the user
                        System.exit(0);
                    }
                    break;

                default:
                    System.err.println("\n**Please, Enter a Valid Input**");
                    break;
            }
        } while (choice != 6 && choice != 7);
    }
}