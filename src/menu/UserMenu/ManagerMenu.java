package menu.UserMenu;

import FileHandling.*;
import interfaces.*;
import interfaces.statistics.CarStatisticsInterfaces;
import interfaces.statistics.ServiceStatisticsInterfaces;
import logsystem.*;
import menu.Menu;
import operations.*;
import operations.statistics.CarStatistics;
import operations.statistics.ServiceStatistics;
import service.Service;
import utils.*;
import java.util.*;
import static menu.MenuStyle.*;
import static utils.InputValidation.isCarIDExists;
import static user.Authenticator.loggedUser;

public class ManagerMenu extends Menu {
    int choice;
    private static final AutoPartFileHandler partDAO = new AutoPartFileHandler();
    private static List<Service> serviceList = new ArrayList<>();

    private final ActivityLogService activityLogService;
    // Use the CarInterfaces reference
    static CarInterfaces carService = new CarService();
    static AutoPartInterfaces autoPartService = new AutoPartService();
    static ServiceInterfaces serviceService = new ServiceService();
    static TransactionInterfaces transactionService = new TransactionService();
    static UserInterfaces userService = new UserService();
    static CarStatisticsInterfaces carStatistics = new CarStatistics();
    static ServiceStatisticsInterfaces serviceStatistics = new ServiceStatistics();

    Scanner input = new Scanner(System.in);

    public ManagerMenu() {


        this.activityLogService = new ActivityLogService();

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
                carService.createCar(); // Add a new car
                break;

            case 1:
                carService.updateCar(); // Update an existing car
                break;

            case 2:
                carService.deleteCar(); // Delete a car
                break;

            case 3:
                String carID = InputValidation.validateCarIDFormat("Enter Car ID to search (format: C-XXXX where XXXX is a number): ");
                if (isCarIDExists(carID)) {
                    System.out.println("Car ID: " + carID);
                }
                break;

            case 4:
                carService.displayAllCar(); // View all available cars
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
                autoPartService.addPart();
                partDAO.serializeParts();
                break;
            case 1:
                autoPartService.updatePart();
                partDAO.serializeParts();
                break;
            case 2:
                autoPartService.deletePart();
                partDAO.serializeParts();
                break;
            case 3:
                autoPartService.viewPartDetails();
                partDAO.serializeParts();
                break;
            case 4:
                autoPartService.listAllParts();
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
        displayOption(GREEN_BOLD + "5. " + RESET + "Sort Transaction by total price");
        displayOption(GREEN_BOLD + "6. " + RESET + "Back");
        Divider.printDivider();

        System.out.print("Enter Selection (0-5): ");
        int choice = getValidatedChoice(0, 6);

        switch (choice) {
            case 0:
                transactionService.addTransaction(); // Call the addTransaction method to add a new sale
                break;

            case 1:
                String deleteID = InputValidation.validateTransactionID("Enter Transaction ID to delete (format: t-XXXX): ");
                transactionService.deleteTransaction(deleteID); // Call the deleteTransaction method to delete a sale
                break;

            case 2:
                transactionService.displayTransactionsByClientID();
                break;
            case 3:
                transactionService.displayAllTransactions();
                break;

            case 4:
                transactionService.displayTransactionsByID();
                break;

            case 5:
                transactionService.displayTransactionsSortByPrice();
                break;

            case 6:
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
                userService.createUser();
                break;

            case 1:
                userService.editProfile(loggedUser);
                break;
            case 2:
                userService.deleteUser();
                break;
            case 3:

                break;
            case 4:
                userService.displayAllUsers();
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
                serviceService.addService();
                break;
            case 1:
                serviceService.getServiceByID();
                break;
            case 2:
                serviceService.updateService();
                break;
            case 3:
                serviceService.deleteService();
                break;
            case 4:
                String serviceID = InputValidation.validateExistingServiceID("Enter Service ID: ",serviceList);
                String partID = InputValidation.validateExistingPartID("Enter Part ID: ");
                serviceService.addPartToService(serviceID, partID);
                break;
            case 5:
                String serviceIDToRemove = InputValidation.validateExistingServiceID("Enter Service ID: ",serviceList);
                serviceService.removePartFromService(serviceIDToRemove);
                break;
            case 6:
                serviceService.listAllServices();
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
            case 0 ->{
                carStatistics.calculateCarsSoldInMonth();
                System.out.println();
                carStatistics.calculateRevenueFromCars();
            }

            case 1 -> {}


            case 2 -> {
                serviceStatistics.calculateRevenueFromServices();
            }


            case 3 -> {}


            case 4 -> {}


            case 5 -> {
                // Go back to the main menu
                displayManagerMenu();
                break;
            }

            default -> {
                System.err.println("\n**Please, Enter a Valid Input**");
                System.out.println();
            }
        }

    }
}
