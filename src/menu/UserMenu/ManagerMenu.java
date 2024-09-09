package menu.UserMenu;


import menu.Menu;
import utils.*;
import java.util.*;

import static menu.MenuStyle.*;


public class ManagerMenu extends Menu {
    int choice;
    Scanner input = new Scanner(System.in);
    private operations.UserService UserService;

    public void displayManagerMenu(){
        ClearScreen.clear(); // Assuming ClearScreen is a utility class to clear console

        System.out.println(CYAN_BOLD + "=====================================" + RESET);
        System.out.println(CYAN_BOLD + "         Manager Main Menu" + RESET);
        System.out.println(CYAN_BOLD + "=====================================" + RESET);

        displayMenuHeader("MANAGER MENU", 53);
        displayOption(GREEN_BOLD + "0. " + RESET + "Manage Cars");
        displayOption(GREEN_BOLD + "1. " + RESET + "Manage Auto Parts");
        displayOption(GREEN_BOLD + "2. " + RESET + "Manage Sale Transactions");
        displayOption(GREEN_BOLD + "3. " + RESET + "Manage Users");
        displayOption(GREEN_BOLD + "4. " + RESET + "Manage Services");
        displayOption(GREEN_BOLD + "5. " + RESET + "Statistics");
        displayOption(GREEN_BOLD + "6. " + RESET + "Go Main Menu");
        displayOption(GREEN_BOLD + "7. " + RESET + "Exit");
        Divider.printDivider();

        System.out.print("Enter Selection (0-7): ");
        choice = getValidatedChoice(0, 7);

        switch (choice) {
            case 0:
                displayManagerCarMenu();
                break;

            case 1:
                displayManagerAutoPartMenu();
                break;
            case 2:
                displayManagerSaleTransactionsMenu();
                break;
            case 3:
                displayManagerUsersMenu();
                break;
            case 4:
                displayManagerServicesMenu();
                break;
            case 5:
                displayManagerStatisticsMenu();
                break;
            case 6:
                boolean confirmBack = InputValidation.validateBoolean("Are you sure you want to return to the Main Menu? (yes/no): ");
                if (confirmBack) {
                    System.out.println("Returning to Main Menu...");
                    return; // Exits the current menu loop and returns to the Menu class
                }
                break;
            case 7:
                boolean confirmExit = InputValidation.validateBoolean("Are you sure you want to exit? (yes/no): ");
                if (confirmExit) {
                    input.close();
                    System.exit(0);
                }
                Divider.printDivider(); // Print a divider for clarity
                break;
            default:
                System.err.println("\n**Please, Enter a Valid Input**");
                System.out.println();
        }
    }

    public void displayManagerCarMenu(){
        ClearScreen.clear();
        System.out.println(CYAN_BOLD + "=====================================" + RESET);
        System.out.println(CYAN_BOLD + "       Manager Car Menu" + RESET);
        System.out.println(CYAN_BOLD + "=====================================" + RESET);

        displayMenuHeader("MANAGER CAR MENU", 53);
        displayOption(GREEN_BOLD + "0. " + RESET + "Add Cars");
        displayOption(GREEN_BOLD + "1. " + RESET + "Update Cars");
        displayOption(GREEN_BOLD + "2. " + RESET + "Delete Cars");
        displayOption(GREEN_BOLD + "3. " + RESET + "Search Cars");
        displayOption(GREEN_BOLD + "4. " + RESET + "View All Cars");
        displayOption(GREEN_BOLD + "5. " + RESET + "Back");
        Divider.printDivider();

        System.out.print("Enter Selection (0-5): ");
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

                break;
            case 5:
                displayManagerMenu(); // Go back to the main menu
                break;
            default:
                System.err.println("\n**Please, Enter a Valid Input**");
                System.out.println();
        }
    }
    public void displayManagerAutoPartMenu(){
        ClearScreen.clear();
        System.out.println(CYAN_BOLD + "=====================================" + RESET);
        System.out.println(CYAN_BOLD + "    Manager Auto Parts Menu" + RESET);
        System.out.println(CYAN_BOLD + "=====================================" + RESET);

        displayMenuHeader("MANAGER AUTO PARTS MENU", 53);
        displayOption(GREEN_BOLD + "0. " + RESET + "Add Auto Parts");
        displayOption(GREEN_BOLD + "1. " + RESET + "Update Auto Parts");
        displayOption(GREEN_BOLD + "2. " + RESET + "Delete Auto Parts");
        displayOption(GREEN_BOLD + "3. " + RESET + "Search Auto Parts");
        displayOption(GREEN_BOLD + "4. " + RESET + "View All Auto Parts");
        displayOption(GREEN_BOLD + "5. " + RESET + "Back");
        Divider.printDivider();

        System.out.print("Enter Selection (0-5): ");
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

                break;
            case 5:
                displayManagerMenu(); // Go back to the main menu
                break;
            default:
                System.err.println("\n**Please, Enter a Valid Input**");
                System.out.println();
        }
    }
    public void displayManagerSaleTransactionsMenu(){
        ClearScreen.clear();
        System.out.println(CYAN_BOLD + "=====================================" + RESET);
        System.out.println(CYAN_BOLD + "Manager Sale Transactions Menu" + RESET);
        System.out.println(CYAN_BOLD + "=====================================" + RESET);

        displayMenuHeader("MANAGER SALE TRANSACTION MENU", 53);
        displayOption(GREEN_BOLD + "0. " + RESET + "Add Sale Transactions");
        displayOption(GREEN_BOLD + "1. " + RESET + "Update Sale Transactions");
        displayOption(GREEN_BOLD + "2. " + RESET + "Delete Sale Transactions");
        displayOption(GREEN_BOLD + "3. " + RESET + "Search Sale Transactions");
        displayOption(GREEN_BOLD + "4. " + RESET + "View All Sale Transactions");
        displayOption(GREEN_BOLD + "5. " + RESET + "Back");
        Divider.printDivider();

        System.out.print("Enter Selection (0-5): ");
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

                break;
            case 5:
                displayManagerMenu(); // Go back to the main menu
                break;
            default:
                System.err.println("\n**Please, Enter a Valid Input**");
                System.out.println();
        }

    }
    public void displayManagerUsersMenu(){
        ClearScreen.clear();
        System.out.println(CYAN_BOLD + "=====================================" + RESET);
        System.out.println(CYAN_BOLD + "       Manager Users Menu" + RESET);
        System.out.println(CYAN_BOLD + "=====================================" + RESET);

        displayMenuHeader("MANAGER USERS MENU", 53);
        displayOption(GREEN_BOLD + "0. " + RESET + "Add User");
        displayOption(GREEN_BOLD + "1. " + RESET + "Update User");
        displayOption(GREEN_BOLD + "2. " + RESET + "Delete User");
        displayOption(GREEN_BOLD + "3. " + RESET + "Search User");
        displayOption(GREEN_BOLD + "4. " + RESET + "View All Users");
        displayOption(GREEN_BOLD + "5. " + RESET + "Back");
        Divider.printDivider();

        System.out.print("Enter Selection (0-5): ");
        choice = getValidatedChoice(0, 5);

        switch (choice) {
            case 0:
                UserService.createUser();
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
                displayManagerMenu(); // Go back to the main menu
                break;
            default:
                System.err.println("\n**Please, Enter a Valid Input**");
                System.out.println();
        }
    }
    public void displayManagerServicesMenu(){
        ClearScreen.clear();
        System.out.println(CYAN_BOLD + "=====================================" + RESET);
        System.out.println(CYAN_BOLD + "      Manager Services Menu" + RESET);
        System.out.println(CYAN_BOLD + "=====================================" + RESET);

        displayMenuHeader("MANAGER SERVICE MENU", 53);
        displayOption(GREEN_BOLD + "0. " + RESET + "Add Services");
        displayOption(GREEN_BOLD + "1. " + RESET + "Update Services");
        displayOption(GREEN_BOLD + "2. " + RESET + "Delete Services");
        displayOption(GREEN_BOLD + "3. " + RESET + "Search Services");
        displayOption(GREEN_BOLD + "4. " + RESET + "View All Services");
        displayOption(GREEN_BOLD + "5. " + RESET + "Back");
        Divider.printDivider();

        System.out.print("Enter Selection (0-5): ");
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

                break;
            case 5:
                displayManagerMenu(); // Go back to the main menu
                break;
            default:
                System.err.println("\n**Please, Enter a Valid Input**");
                System.out.println();
        }


    }
    public void displayManagerStatisticsMenu(){
        ClearScreen.clear();
        System.out.println(CYAN_BOLD + "=====================================" + RESET);
        System.out.println(CYAN_BOLD + "   Manager Statistics Menu" + RESET);
        System.out.println(CYAN_BOLD + "=====================================" + RESET);

        displayMenuHeader("MANAGER STATISTICS MENU", 53);
        displayOption(GREEN_BOLD + "0. " + RESET + "View Cars Statistics");
        displayOption(GREEN_BOLD + "1. " + RESET + "View Auto Part Statistics");
        displayOption(GREEN_BOLD + "2. " + RESET + "View Service Statistics");
        displayOption(GREEN_BOLD + "3. " + RESET + "View Transaction Statistics");
        displayOption(GREEN_BOLD + "4. " + RESET + "View Revenue");
        displayOption(GREEN_BOLD + "5. " + RESET + "Back");
        Divider.printDivider();

        System.out.print("Enter Selection (0-5): ");
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

                break;
            case 5:
                displayManagerMenu(); // Go back to the main menu
                break;
            default:
                System.err.println("\n**Please, Enter a Valid Input**");
                System.out.println();
        }

    }
}