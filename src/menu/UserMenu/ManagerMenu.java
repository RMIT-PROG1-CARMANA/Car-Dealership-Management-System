package menu.usermenu;

import interfaces.*;
import logsystem.*;
import menu.Menu;
import menu.statistics.statisticsmenu;
import operations.*;
import utils.*;
import java.util.*;
import static menu.MenuStyle.*;


public class ManagerMenu extends Menu {
    int choice;
    private final ActivityLogService activityLogService;
    // Use the CarInterfaces reference
    static CarInterfaces carService = new CarService();
    static AutoPartInterfaces autoPartService = new AutoPartService();
    static ServiceInterfaces serviceService = new ServiceService();
    static TransactionInterfaces transactionService = new TransactionService();
    static UserInterfaces userService = new UserService();
    statisticsmenu statisticsMenu = new statisticsmenu();




    Scanner input = new Scanner(System.in);

    public ManagerMenu() {
        this.activityLogService = new ActivityLogService();
    }


    public void displayManagerMenu(){
        ClearScreen.clear(); // Assuming ClearScreen is a utility class to clear console

        System.out.println(CYAN_BOLD + "=====================================" + RESET);
        System.out.println(CYAN_BOLD + "         Manager Main Menu" + RESET);
        System.out.println(CYAN_BOLD + "=====================================" + RESET);

        displayMenuHeader("MANAGER MENU", 43);
        displayOption(GREEN_BOLD + "0. " + RESET + "Manage Cars");
        displayOption(GREEN_BOLD + "1. " + RESET + "Manage Auto Parts");
        displayOption(GREEN_BOLD + "2. " + RESET + "Manage Sale Transactions");
        displayOption(GREEN_BOLD + "3. " + RESET + "Manage Users");
        displayOption(GREEN_BOLD + "4. " + RESET + "Manage Services");
        displayOption(GREEN_BOLD + "5. " + RESET + "Manage Activity Log");
        displayOption(GREEN_BOLD + "6. " + RESET + "Statistics");
        displayOption(GREEN_BOLD + "7. " + RESET + "Go Main Menu");
        displayOption(GREEN_BOLD + "8. " + RESET + "Exit");
        Divider.printDivider(46);

        System.out.print("Enter Selection (0-8): ");
        choice = getValidatedChoice(8);

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
                displayManagerActivityLog();
                break;
            case 6:
                displayManagerStatisticsMenu();
                break;
            case 7:
                boolean confirmBack = InputValidation.validateBoolean("Are you sure you want to return to the Main Menu? (yes/no): ");
                if (confirmBack) {
                    System.out.println("Returning to Main Menu...");
                    return; // Exits the current menu loop and returns to the Menu class
                }
                break;
            case 8:
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
        boolean exit = false;
        while (!exit) {
            System.out.println(CYAN_BOLD + "=====================================" + RESET);
            System.out.println(CYAN_BOLD + "       Manager Car Menu" + RESET);
            System.out.println(CYAN_BOLD + "=====================================" + RESET);
            System.out.println();
            displayMenuHeader("MANAGER CAR MENU", 43);
            displayOption(GREEN_BOLD + "0. " + RESET + "Add Cars");
            displayOption(GREEN_BOLD + "1. " + RESET + "Update Cars");
            displayOption(GREEN_BOLD + "2. " + RESET + "Delete Cars");
            displayOption(GREEN_BOLD + "3. " + RESET + "Search Car");
            displayOption(GREEN_BOLD + "4. " + RESET + "View All Cars");
            displayOption(GREEN_BOLD + "5. " + RESET + "View Car Sorted By Price");
            displayOption(GREEN_BOLD + "6. " + RESET + "Back");
            Divider.printDivider(46);

            System.out.print("Enter Selection (0-6): ");
            choice = getValidatedChoice(6);
            switch (choice) {
                case 0:
                    carService.addCar(); // Add a new car
                    break;

                case 1:
                    carService.updateCar(); // Update an existing car
                    break;

                case 2:
                    carService.deleteCar(); // Delete a car
                    break;

                case 3:
                    carService.searchCarByID(); // search car
                    break;
                case 4:
                    carService.displayAllCar(); // View all available cars
                    break;
                case 5:
                    carService.displayCarByPrice();
                    break;
                case 6:
                    displayManagerMenu(); // Go back to the main menu
                    exit = true;
                    break;
                default:
                    System.err.println("\nPlease, Enter a Valid Input");
                    System.out.println();
            }
        }
    }
    public void displayManagerAutoPartMenu(){
        ClearScreen.clear();
        boolean exit = false;
        while (!exit){

        System.out.println(CYAN_BOLD + "=====================================" + RESET);
        System.out.println(CYAN_BOLD + "    Manager Auto Parts Menu" + RESET);
        System.out.println(CYAN_BOLD + "=====================================" + RESET);

        displayMenuHeader("MANAGER AUTO PARTS MENU", 43);
        displayOption(GREEN_BOLD + "0. " + RESET + "Add Auto Parts");
        displayOption(GREEN_BOLD + "1. " + RESET + "Update Auto Parts");
        displayOption(GREEN_BOLD + "2. " + RESET + "Delete Auto Parts");
        displayOption(GREEN_BOLD + "3. " + RESET + "Search Auto Parts");
        displayOption(GREEN_BOLD + "4. " + RESET + "View All Auto Parts");
        displayOption(GREEN_BOLD + "5. " + RESET + "Back");
        Divider.printDivider(46);

        System.out.print("Enter Selection (0-5): ");
        choice = getValidatedChoice(5);

        switch (choice) {
            case 0:
                autoPartService.addPart();
                break;
            case 1:
                autoPartService.updatePart();
                break;
            case 2:
                autoPartService.deletePart();
                break;
            case 3:
                autoPartService.searchPartByID();
                break;
            case 4:
                autoPartService.listAllParts();
                break;
            case 5:
                displayManagerMenu(); // Go back to the main menu
                exit = true;
                break;
            default:
                System.err.println("\n**Please, Enter a Valid Input**");
                System.out.println();
        }
        }
    }
    public void displayManagerSaleTransactionsMenu() {
        ClearScreen.clear();
        boolean exit = false;
        while (!exit){
        System.out.println(CYAN_BOLD + "=====================================" + RESET);
        System.out.println(CYAN_BOLD + "Manager Sale Transactions Menu" + RESET);
        System.out.println(CYAN_BOLD + "=====================================" + RESET);

        displayMenuHeader("MANAGER SALE TRANSACTION MENU", 43);
        displayOption(GREEN_BOLD + "0. " + RESET + "Add Sale Transaction");
        displayOption(GREEN_BOLD + "1. " + RESET + "Delete Sale Transaction");
        displayOption(GREEN_BOLD + "2. " + RESET + "Search Sale Transactions by Client ID");
        displayOption(GREEN_BOLD + "3. " + RESET + "View All Sale Transactions");
        displayOption(GREEN_BOLD + "4. " + RESET + "View Transaction Details by ID");
        displayOption(GREEN_BOLD + "5. " + RESET + "Sort Transaction by total price");
        displayOption(GREEN_BOLD + "6. " + RESET + "Back");
        Divider.printDivider(46);

        System.out.print("Enter Selection (0-5): ");
        int choice = getValidatedChoice(6);

        switch (choice) {
            case 0:
                transactionService.addTransaction(); // Call the addTransaction method to add a new sale
                break;

            case 1:
                transactionService.deleteTransaction(); // Call the deleteTransaction method to delete a sale
                break;

            case 2:
                transactionService.displayTransactionsByClientID();
                break;
            case 3:
                transactionService.displayAllTransactions();
                break;

            case 4:
                transactionService.searchTransactionsByID();
                break;

            case 5:
                transactionService.displayTransactionsSortByPrice();
                break;

            case 6:
                displayManagerMenu(); // Go back to the main menu
                exit = true;
                break;
            default:
                System.err.println("\n**Please, Enter a Valid Input**");
                System.out.println();
        }
    }
    }
    public void displayManagerUsersMenu(){
        boolean exit = false;
        while (!exit){
        ClearScreen.clear();
        System.out.println(CYAN_BOLD + "=====================================" + RESET);
        System.out.println(CYAN_BOLD + "       Manager Users Menu" + RESET);
        System.out.println(CYAN_BOLD + "=====================================" + RESET);

        displayMenuHeader("MANAGER USERS MENU", 43);
        displayOption(GREEN_BOLD + "0. " + RESET + "Add User");
        displayOption(GREEN_BOLD + "1. " + RESET + "Update User");
        displayOption(GREEN_BOLD + "2. " + RESET + "Delete User");
        displayOption(GREEN_BOLD + "3. " + RESET + "Search User");
        displayOption(GREEN_BOLD + "4. " + RESET + "View All Users");
        displayOption(GREEN_BOLD + "5. " + RESET + "Back");
        Divider.printDivider(46);

        System.out.print("Enter Selection (0-5): ");
        choice = getValidatedChoice(5);

        switch (choice) {
            case 0:
                userService.addUser();
                break;

            case 1:
                userService.updateUser();
                break;
            case 2:
                userService.deleteUser();
                break;
            case 3:
                userService.searchUserByID();
                break;
            case 4:
                userService.displayAllUsers();
                break;
            case 5:
                displayManagerMenu(); // Go back to the main menu
                exit = true;
                break;
            default:
                System.err.println("\n**Please, Enter a Valid Input**");
                System.out.println();
        }
    }
    }
    public void displayManagerServicesMenu(){
        boolean exit = false;
        while (!exit) {
            ClearScreen.clear();
            System.out.println(CYAN_BOLD + "=====================================" + RESET);
            System.out.println(CYAN_BOLD + "      Manager Services Menu" + RESET);
            System.out.println(CYAN_BOLD + "=====================================" + RESET);

            displayMenuHeader("MANAGER SERVICE MENU", 43);
            displayOption(GREEN_BOLD + "0. " + RESET + "Add Services");
            displayOption(GREEN_BOLD + "1. " + RESET + "Get Service by ID");
            displayOption(GREEN_BOLD + "2. " + RESET + "Update Service");
            displayOption(GREEN_BOLD + "3. " + RESET + "Delete Service");
            displayOption(GREEN_BOLD + "4. " + RESET + "Add Part to Service");
            displayOption(GREEN_BOLD + "5. " + RESET + "Remove Part from Service");
            displayOption(GREEN_BOLD + "6. " + RESET + "List All Services");
            displayOption(GREEN_BOLD + "7. " + RESET + "Back");
            Divider.printDivider(46);

            System.out.print("Enter Selection (0-7): ");
            choice = getValidatedChoice(7);

            switch (choice) {
                case 0:
                    serviceService.addService();
                    break;
                case 1:
                    serviceService.searchServiceByID();
                    break;
                case 2:
                    serviceService.updateService();
                    break;
                case 3:
                    serviceService.deleteService();
                    break;
                case 4:
                    serviceService.addPartToService();
                    break;
                case 5:
                    serviceService.removePartFromService();
                    break;
                case 6:
                    serviceService.displayAllServices();
                    break;
                case 7:
                    displayManagerMenu(); // Go back to the main menu
                    exit = true;
                    break;
                default:
                    System.err.println("\n**Please, Enter a Valid Input**");
                    System.out.println();
            }
        }

    }
    public void displayManagerActivityLog() {
        ClearScreen.clear();
        boolean exit = false;
        while (!exit) {
            System.out.println(CYAN_BOLD + "=====================================" + RESET);
            System.out.println(CYAN_BOLD + "       Manager Activity log" + RESET);
            System.out.println(CYAN_BOLD + "=====================================" + RESET);

            displayMenuHeader("ACTIVITY LOG MENU", 43);
            displayOption(GREEN_BOLD + "0. " + RESET + "View All Logs");
            displayOption(GREEN_BOLD + "1. " + RESET + "Search by Log ID");
            displayOption(GREEN_BOLD + "2. " + RESET + "Search by Username");
            displayOption(GREEN_BOLD + "3. " + RESET + "Search by User ID");
            displayOption(GREEN_BOLD + "4. " + RESET + "Search by Date");
            displayOption(GREEN_BOLD + "5. " + RESET + "Back");
            Divider.printDivider(46);

            System.out.print("Enter Selection (0-5): ");
            choice = getValidatedChoice(5);
            switch (choice) {
                case 0:
                    List<ActivityLog> allLogs = activityLogService.viewAllLogs();
                    activityLogService.displayLogs(allLogs);
                    break;

                case 1:
                    String logID = InputValidation.validateExistingUserID("Enter Log ID: ");
                    List<ActivityLog> logById = activityLogService.viewLogById(logID);
                    activityLogService.displayLogs(logById);
                    break;

                case 2:
                    String username = InputValidation.validateExistingUsername("Enter Username: ");
                    List<ActivityLog> logsByUsername = activityLogService.viewLogsByUsername(username);
                    activityLogService.displayLogs(logsByUsername);
                    break;

                case 3:
                    String userID = InputValidation.validateUserIDFormat("Enter User ID: ");
                    List<ActivityLog> logsByUserID = activityLogService.viewLogsByUserID(userID);
                    activityLogService.displayLogs(logsByUserID);
                    break;

                case 4:
                    Date date = InputValidation.validateDate("Enter Date (dd/MM/yyyy): ");
                    List<ActivityLog> logsByDate = activityLogService.viewLogsByDate(date);
                    activityLogService.displayLogs(logsByDate);
                    break;

                case 5:
                    displayManagerMenu(); // Go back to the main menu
                    exit = true;
                    break;
                default:
                    System.err.println("\n**Please, Enter a Valid Input**");
                    System.out.println();
            }
        }
    }
    public void displayManagerStatisticsMenu(){
        ClearScreen.clear();
        boolean exit = false;
        while (!exit) {
            System.out.println(CYAN_BOLD + "=====================================" + RESET);
            System.out.println(CYAN_BOLD + "   Manager Statistics Menu" + RESET);
            System.out.println(CYAN_BOLD + "=====================================" + RESET);

            displayMenuHeader("MANAGER STATISTICS MENU", 43);
            displayOption(GREEN_BOLD + "0. " + RESET + "View Cars Statistics");
            displayOption(GREEN_BOLD + "1. " + RESET + "View Auto Part Statistics");
            displayOption(GREEN_BOLD + "2. " + RESET + "View Service Statistics");
            displayOption(GREEN_BOLD + "3. " + RESET + "View Transaction Statistics");
            displayOption(GREEN_BOLD + "4. " + RESET + "View Revenue");
            displayOption(GREEN_BOLD + "5. " + RESET + "Back");
            Divider.printDivider(46);

            System.out.print("Enter Selection (0-5): ");
            choice = getValidatedChoice(5);

            switch (choice) {
                case 0 -> {
                    statisticsMenu.displayCarStatisticsMenu();
                }

                case 1 -> {
                    statisticsMenu.displayAutoPartStatisticsMenu();
                }


                case 2 -> {
                    statisticsMenu.displayServiceStatisticsMenu();
                }


                case 3 -> {
                    statisticsMenu.displayTransactionStatisticsMenu();
                }


                case 4 -> {
                    statisticsMenu.displayRevenueStatisticsMenu();
                    }
                case 5 -> {
                    // Go back to the main menu
                    displayManagerMenu();
                    exit = true;
                    break;
                }

                default -> {
                    System.err.println("\n**Please, Enter a Valid Input**");
                    System.out.println();
                }
            }
        }
    }
}
