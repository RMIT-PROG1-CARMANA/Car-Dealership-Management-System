package menu.UserMenu;


import menu.Menu;
import part.AutoPart;
import FileHandling.AutoPartFileHandler;
import service.Service;
import FileHandling.ServiceFileHandler;
import user.Authenticator;
import utils.Divider;
import utils.InputValidation;
import service.*;
import operations.*;


import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class ManagerMenu extends Menu {
    int choice;
    Scanner input = new Scanner(System.in);
    private operations.UserService UserService;
    private operations.PartService PartService;
    private operations.ServiceService ServiceService;

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
                Authenticator.UserLogOut();
                break;
            default:
                System.err.println("\n**Please, Enter a Valid Input**");
                System.out.println();
        }
    }
    public void displayManagerAutoPartMenu() {
        AutoPartFileHandler.deserializeParts(); // Load parts at the start of the menu
        boolean exit = false;
        while (!exit) {
            displayMenuHeader("MANAGER AUTO PARTS MENU", 53);
            displayOption("0. Add Auto Parts");
            displayOption("1. Update Auto Parts");
            displayOption("2. Delete Auto Parts");
            displayOption("3. Search Auto Parts");
            displayOption("4. View All Auto Parts");
            displayOption("5. Back");
            Divider.printDivider();

            System.out.print("Enter Selection from 0-5: ");
            int choice = getValidatedChoice(0, 5);

            switch (choice) {
                case 0:
                    PartService.addPart(input); // Ensure these methods are accessible
                    break;
                case 1:
                    PartService.updatePart(input);
                    break;
                case 2:
                    PartService.deletePart(input);
                    break;
                case 3:
                    PartService.viewPartDetails(input);
                    break;
                case 4:
                    PartService.listAllParts();
                    break;
                case 5:
                    exit = true; // Exit the loop and return to the main manager menu
                    break;
                default:
                    System.err.println("\n**Please, Enter a Valid Input**");
            }
        }
        AutoPartFileHandler.serializeParts(); // Save parts before exiting the menu
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
                Authenticator.UserLogOut();
                break;
            default:
                System.err.println("\n**Please, Enter a Valid Input**");
                System.out.println();
        }

    }
    public void displayManagerUsersMenu(){
        displayMenuHeader("ADMIN USERS MENU", 53);
        displayOption("0. Add User");
        displayOption("1. Update User");
        displayOption("2. Delete User");
        displayOption("3. Search User");
        displayOption("4. View All User");
        displayOption("5. Back");
        Divider.printDivider();

        System.out.print("Enter Selection from 0-5: ");
        System.out.println();

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

                break;
            default:
                System.err.println("\n**Please, Enter a Valid Input**");
                System.out.println();
        }
    }
    public void displayManagerServicesMenu() {
        try {
            List<Service> services = ServiceFileHandler.loadServices();
            Map<String, AutoPart> partsMap = ServiceFileHandler.loadParts();
            // Ensure services and parts are loaded

            Scanner scanner = new Scanner(System.in); // Initialize scanner
            while (true) {
                displayMenuHeader("MANAGER SERVICE MENU", 53);
                displayOption("0. Add Service");
                displayOption("1. Get Service by ID");
                displayOption("2. Update Service");
                displayOption("3. Delete Service");
                displayOption("4. Add Part to Service");
                displayOption("5. Remove Part from Service");
                displayOption("6. List All Services");
                displayOption("7. Exit");
                Divider.printDivider();

                System.out.print("Enter Selection from 0-7: ");
                int choice = getValidatedChoice(0, 7);

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
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.err.println("\n**Please, Enter a Valid Input**");
                }
            }
        } catch (Exception e) {
            System.err.println("Error initializing ServiceMenu: " + e.getMessage());
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
                Authenticator.UserLogOut();
                break;
            default:
                System.err.println("\n**Please, Enter a Valid Input**");
                System.out.println();
        }

    }
}
