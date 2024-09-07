package menu.UserMenu;

import menu.Menu;
import utils.InputValidation;
import utils.*;
import java.util.*;

import static menu.MenuStyle.*;

public class ClientMenu extends Menu {
    private final InputValidation inputValidation = new InputValidation();


    public void displayClientMenu() {
        int choice;
        Scanner scanner = new Scanner(System.in);

        do {
            // Clear the screen
            ClearScreen.clear();

            System.out.println(CYAN_BOLD + "=====================================" + RESET);
            System.out.println(CYAN_BOLD + "           Client Menu" + RESET);
            System.out.println(CYAN_BOLD + "=====================================" + RESET);

            displayMenuHeader("CLIENT MENU", 53);
            System.out.println(GREEN_BOLD + "0. " + RESET + "View Transaction History");
            System.out.println(GREEN_BOLD + "1. " + RESET + "View Membership Details");
            System.out.println(GREEN_BOLD + "2. " + RESET + "Schedule Service Appointment");
            System.out.println(GREEN_BOLD + "3. " + RESET + "View Service History");
            System.out.println(GREEN_BOLD + "4. " + RESET + "Back to Main Menu");
            System.out.println(GREEN_BOLD + "5. " + RESET + "Exit");
            System.out.println(CYAN_BOLD + "=====================================" + RESET);

            System.out.print("Enter Selection (0-5) ");
            choice = getValidatedChoice(0, 5);

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
                    boolean confirmBack = InputValidation.validateBoolean("Are you sure you want to back to Main Menu? (yes/no): ");
                    if (confirmBack) {
                        System.out.println("Returning to main menu...");
                    }
                    return;
                case 5:
                    boolean confirmExit = InputValidation.validateBoolean("Are you sure you want to exit? (yes/no): ");
                    if (confirmExit) {
                        System.out.println("Exiting the application... Goodbye!");
                        System.exit(0);
                    }
                    break;

                default:
                    System.err.println("\n**Please, Enter a Valid Input**");
                    break;
            }

            System.out.println(CYAN_BOLD + "=====================================" + RESET);
        } while (choice != 6 && choice != 7);
    }
}
