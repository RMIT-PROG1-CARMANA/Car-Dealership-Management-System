package menu.UserMenu.EmployeeRoles;

import menu.UserMenu.EmployeeBaseMenu;
import user.Authenticator;
import utils.*;
import java.util.*;


public class SalespersonMenu extends EmployeeBaseMenu {
    @Override
    public void displayEmployeeMenu() {
        int choice;
        Scanner scanner = new Scanner(System.in);

        do {
            // Clear the screen
            clearScreen();

            System.out.println("Welcome, Salesperson!");

            displayMenuHeader("Salesperson Menu", 53);
            displayOption("0. Record Sales Transaction");
            displayOption("1. View Sales Transactions");
            displayOption("2. Calculate Revenue (Day/Week/Month)");
            displayOption("3. List Number of Services (Day/Week/Month)");
            displayOption("4. View Sales Report");
            displayOption("5. Update Customer Information");
            displayOption("6. Back to Main Menu");
            displayOption("7. Exit");
            Divider.printDivider();

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
                    returnToMainMenu();
                    break;

                case 7:
                    System.out.println("Are you sure you want to exit? (yes/no): ");
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
