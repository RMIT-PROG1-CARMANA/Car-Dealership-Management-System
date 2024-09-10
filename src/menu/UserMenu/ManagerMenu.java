package menu.UserMenu;

import FileHandling.*;
import logsystem.*;
import crudHandlers.CarCRUD;
import menu.Menu;
import operations.*;
import part.AutoPart;
import sales.SalesTransaction;
import service.Service;
import user.Authenticator;
import user.*;
import crudHandlers.*;
import vehicle.*;
import utils.*;
import java.util.*;

import static menu.MenuStyle.*;
import static operations.ActivityLogService.*;
import static utils.InputValidation.isCarIDExists;

public class ManagerMenu extends Menu {
    int choice;
    private static final UserDataHandler userDAO = new UserDataHandler();
    private static final CarDataHandler carDAO = new CarDataHandler();
    private static final AutoPartFileHandler partDAO = new AutoPartFileHandler();
    private static final ServiceFileHandler serviceDAO = new ServiceFileHandler();
    private static final SalesTransactionDataHandler transDAO = new SalesTransactionDataHandler();
    private static final ActivityLogDataHandler logDAO = new ActivityLogDataHandler();

    private final UserService userService;
    private final CarService carService;
    private final PartService partService;
    private final ServiceService serviceService;
    private final ActivityLogService activityLogService;
    private final TransactionService TransactionService;

    Scanner input = new Scanner(System.in);

    public ManagerMenu() {
        this.userService = new UserService();
        this.carService = new CarService();
        this.partService = new PartService();
        this.serviceService = new ServiceService();
        this.activityLogService = new ActivityLogService();
        this.TransactionService = new TransactionService();
    }


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
        displayOption(GREEN_BOLD + "5. " + RESET + "Manage Activity Log");
        displayOption(GREEN_BOLD + "6. " + RESET + "Statistics");
        displayOption(GREEN_BOLD + "7. " + RESET + "Go Main Menu");
        displayOption(GREEN_BOLD + "8. " + RESET + "Exit");
        Divider.printDivider();

        System.out.print("Enter Selection (0-8): ");
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
                CarService.createCar(); // Add a new car
                break;

            case 1:
                CarService.updateCar(); // Update an existing car
                break;

            case 2:
                CarService.deleteCar(); // Delete a car
                break;

            case 3:
                String carID = InputValidation.validateCarIDFormat("Enter Car ID to search (format: C-XXXX where XXXX is a number): ");
                if (isCarIDExists(carID)) {
                    System.out.println("Car ID: " + carID);
                }
                break;

            case 4:
                CarService.displayAllCar(); // View all available cars
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
        partDAO.deserializeParts();
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
                PartService.addPart();
                partDAO.serializeParts();
                break;
            case 1:
                PartService.updatePart(input);
                partDAO.serializeParts();
                break;
            case 2:
                PartService.deletePart(input);
                partDAO.serializeParts();
                break;
            case 3:
                PartService.viewPartDetails(input);
                partDAO.serializeParts();
                break;
            case 4:
                PartService.listAllParts();
                partDAO.serializeParts();
                break;
            case 5:
                displayManagerMenu(); // Go back to the main menu
                break;
            default:
                System.err.println("\n**Please, Enter a Valid Input**");
                System.out.println();
        }
    }
    public void displayManagerSaleTransactionsMenu() {
        ClearScreen.clear();
        System.out.println(CYAN_BOLD + "=====================================" + RESET);
        System.out.println(CYAN_BOLD + "Manager Sale Transactions Menu" + RESET);
        System.out.println(CYAN_BOLD + "=====================================" + RESET);

        displayMenuHeader("MANAGER SALE TRANSACTION MENU", 53);
        displayOption(GREEN_BOLD + "0. " + RESET + "Add Sale Transaction");
        displayOption(GREEN_BOLD + "1. " + RESET + "Delete Sale Transaction");
        displayOption(GREEN_BOLD + "2. " + RESET + "Search Sale Transactions by Client ID");
        displayOption(GREEN_BOLD + "3. " + RESET + "View All Sale Transactions");
        displayOption(GREEN_BOLD + "4. " + RESET + "View Transaction Details by ID");
        displayOption(GREEN_BOLD + "5. " + RESET + "Back");
        Divider.printDivider();

        System.out.print("Enter Selection (0-5): ");
        int choice = getValidatedChoice(0, 5);

        switch (choice) {
            case 0:
                TransactionService.addTransaction(); // Call the addTransaction method to add a new sale
                break;

            case 1:
                String deleteID = InputValidation.validateTransactionID("Enter Transaction ID to delete (format: t-XXXX): ");
                TransactionService.deleteTransaction(deleteID); // Call the deleteTransaction method to delete a sale
                break;

            case 2:
                String clientID = InputValidation.validateUserID("Enter Client ID to search transactions (format: CL-XXXX): ");
                List<SalesTransaction> transactions = TransactionService.getTransactionsByClientID(clientID);
                if (transactions.isEmpty()) {
                    System.out.println("No transactions found for client ID: " + clientID);
                } else {
                    transactions.forEach(SalesTransaction::displayTransactionDetails);
                }
                break;

            case 3:
                List<SalesTransaction> allTransactions = TransactionService.getTransactionsOrderedByID(operations.TransactionService.OrderType.transactionID, true);
                if (allTransactions.isEmpty()) {
                    System.out.println("No transactions available.");
                } else {
                    allTransactions.forEach(SalesTransaction::displayTransactionDetails);
                }
                break;

            case 4:
                String transactionID = InputValidation.validateTransactionID("Enter Transaction ID to view details (format: t-XXXX): ");
                TransactionService.displayTransactionByID(transactionID); // Call the displayTransactionByID method to show details of a specific transaction
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
                UserService.deleteUser();
                break;
            case 3:

                break;
            case 4:
                UserService.displayAllUsers();
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
        displayOption(GREEN_BOLD + "1. " + RESET + "Get Service by ID");
        displayOption(GREEN_BOLD + "2. " + RESET + "Update Service");
        displayOption(GREEN_BOLD + "3. " + RESET + "Delete Service");
        displayOption(GREEN_BOLD + "4. " + RESET + "Add Part to Service");
        displayOption(GREEN_BOLD + "5. " + RESET + "Remove Part from Service");
        displayOption(GREEN_BOLD + "6. " + RESET + "List All Services");
        displayOption(GREEN_BOLD + "7. " + RESET + "Back");
        Divider.printDivider();

        System.out.print("Enter Selection (0-7): ");
        choice = getValidatedChoice(0, 7);

        switch (choice) {
            case 0:
                ServiceService.addService();
                break;
            case 1:
                ServiceService.getServiceByID();
                break;
            case 2:
                ServiceService.updateService();
                break;
            case 3:
                ServiceService.deleteService();
                break;
            case 4:
                System.out.print("Enter Service ID: ");
                String serviceID = scanner.nextLine();
                System.out.print("Enter Part ID: ");
                String partID = scanner.nextLine();
                ServiceService.addPartToService(serviceID, partID);
                break;
            case 5:
                System.out.print("Enter Service ID: ");
                String serviceIDToRemove = scanner.nextLine();
                ServiceService.removePartFromService(serviceIDToRemove);
                break;
            case 6:
                ServiceService.listAllServices();
                break;
            case 7:
                displayManagerMenu(); // Go back to the main menu
                break;
            default:
                System.err.println("\n**Please, Enter a Valid Input**");
                System.out.println();
        }


    }
    public void displayManagerActivityLog(){
        ClearScreen.clear();
        System.out.println(CYAN_BOLD + "=====================================" + RESET);
        System.out.println(CYAN_BOLD + "       Manager Activity log" + RESET);
        System.out.println(CYAN_BOLD + "=====================================" + RESET);

        displayMenuHeader("ACTIVITY LOG MENU", 53);
        displayOption(GREEN_BOLD + "0. " + RESET + "View All Logs");
        displayOption(GREEN_BOLD + "1. " + RESET + "Search by Log ID");
        displayOption(GREEN_BOLD + "2. " + RESET + "Search by Username");
        displayOption(GREEN_BOLD + "3. " + RESET + "Search by User ID");
        displayOption(GREEN_BOLD + "4. " + RESET + "Search by Date");
        displayOption(GREEN_BOLD + "5. " + RESET + "Back");
        Divider.printDivider();

        System.out.print("Enter Selection (0-5): ");
        choice = getValidatedChoice(0, 5);
        switch (choice) {
            case 0:
                List<ActivityLog> allLogs = activityLogService.viewAllLogs();
                activityLogService.displayLogs(allLogs);
                break;

            case 1:
                String logID = InputValidation.validateString("Enter Log ID: ");
                List<ActivityLog> logById = activityLogService.viewLogById(logID);
                activityLogService.displayLogs(logById);
                break;

            case 2:
                String username = InputValidation.validateUsernameFormat("Enter Username: ");
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
