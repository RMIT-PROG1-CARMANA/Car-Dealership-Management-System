package menu.UserMenu;

import logout.UserLogOut;
import menu.Menu;
import utils.Divider;
import utils.InputValidation;

import java.util.Scanner;


public class ManagerMenu extends Menu {
    int choice;
    Scanner input = new Scanner(System.in);

    public void displayManagerMenu(){
//        uiUtils.clearScreen();
        System.out.println("Welcome Manager!");
        System.out.println();

        displayMenuHeader("ADMIN MENU", 53);
        displayOption("0. Manage Cars");
        displayOption("1. Manage AutoPart");
        displayOption("2. Manage Sale Transactions");
        displayOption("3. Manage Users");
        displayOption("4. Manage Service");
        displayOption("5. Statistics");
        displayOption("6. Go Back Profile");
        displayOption("7. Logout");
        Divider.printDivider();

        System.out.print("Enter Selection: ");
        System.out.println();

        choice = getValidatedChoice(0, 6);

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

                break;
            case 7:
                boolean confirmExit = InputValidation.validateBoolean("Are you sure you want to exit? (yes/no): ");
                if (confirmExit) {
                    input.close();
                }
                Divider.printDivider(); // Print a divider for clarity
                break;
            default:
                System.err.println("\n**Please, Enter a Valid Input**");
                System.out.println();
        }
    }

    public void displayManagerCarMenu(){
        displayMenuHeader("MANAGER CAR MENU", 53);
        displayOption("0. Add Cars");
        displayOption("1. Update Cars");
        displayOption("2. Delete Cars");
        displayOption("3. Search Cars");
        displayOption("4. View All Cars");
        displayOption("5. Back");
        Divider.printDivider();

        System.out.print("Enter Selection from 0-5: ");
        System.out.println();

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
                System.exit(0);// terminates the program
                new UserLogOut();
                break;
            default:
                System.err.println("\n**Please, Enter a Valid Input**");
                System.out.println();
        }
    }
    public void displayManagerAutoPartMenu(){
        displayMenuHeader("MANAGER AUTO PARTS MENU", 53);
        displayOption("0. Add Auto Parts");
        displayOption("1. Update Auto Parts");
        displayOption("2. Delete Auto Parts");
        displayOption("3. Search Auto Parts");
        displayOption("4. View All Auto Parts");
        displayOption("5. Back");
        Divider.printDivider();

        System.out.print("Enter Selection from 0-5: ");
        System.out.println();

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
                System.exit(0);// terminates the program
                new UserLogOut();
                break;
            default:
                System.err.println("\n**Please, Enter a Valid Input**");
                System.out.println();
        }
    }

    public void displayManagerSaleTransactionsMenu(){
        displayMenuHeader("MANAGER SALE TRANSACTION MENU", 53);
        displayOption("0. Add Sale Transactions");
        displayOption("1. Update Sale Transactions");
        displayOption("2. Delete Sale Transactions");
        displayOption("3. Search Sale Transactions");
        displayOption("4. View All Sale Transactions");
        displayOption("5. Back");
        Divider.printDivider();

        System.out.print("Enter Selection from 0-5: ");
        System.out.println();

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
                System.exit(0);// terminates the program
                new UserLogOut();
                break;
            default:
                System.err.println("\n**Please, Enter a Valid Input**");
                System.out.println();
        }

    }
    public void displayManagerUsersMenu(){
        displayMenuHeader("ADMIN USERS MENU", 53);
        displayOption("0. Add ");
        displayOption("1. Update ");
        displayOption("2. Delete ");
        displayOption("3. Search ");
        displayOption("4. View All");
        displayOption("5. Back");

    }
    public void displayManagerServicesMenu(){
        displayMenuHeader("MANAGER SERVICE MENU", 53);
        displayOption("0. Add Services");
        displayOption("1. Update Services");
        displayOption("2. Delete Services");
        displayOption("3. Search Services");
        displayOption("4. View All Services");
        displayOption("5. Back");
        Divider.printDivider();

        System.out.print("Enter Selection from 0-5: ");
        System.out.println();

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
                System.exit(0);// terminates the program
                new UserLogOut();
                break;
            default:
                System.err.println("\n**Please, Enter a Valid Input**");
                System.out.println();
        }


    }
    public void displayManagerStatisticsMenu(){
        displayMenuHeader("MANAGER STATISTICS MENU", 53);
        displayOption("0. View Cars Statistics");
        displayOption("1. View Auto Part Statistics");
        displayOption("2. View Service Statistics");
        displayOption("3. View Transaction Statistics");
        displayOption("4. View Revenue");
        displayOption("5. Back");
        Divider.printDivider();

        System.out.print("Enter Selection from 0-5: ");
        System.out.println();

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
                System.exit(0);// terminates the program
                new UserLogOut();
                break;
            default:
                System.err.println("\n**Please, Enter a Valid Input**");
                System.out.println();
        }

    }
}
