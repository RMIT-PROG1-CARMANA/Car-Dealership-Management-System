package menu.usermenu;

import interfaces.CarInterfaces;
import interfaces.ServiceInterfaces;
import interfaces.TransactionInterfaces;
import interfaces.UserInterfaces;

import menu.Menu;
import operations.CarService;
import operations.ServiceService;
import operations.TransactionService;
import operations.UserService;

import utils.InputValidation;
import utils.*;


import static menu.MenuStyle.*;

public class ClientMenu extends Menu {

    static TransactionInterfaces transactionService = new TransactionService();
    static ServiceInterfaces serviceService = new ServiceService();
    static UserInterfaces userService = new UserService();

    static CarInterfaces carService = new CarService();
    public void displayClientMenu() {
        int choice;

        do {
            // Clear the screen
            ClearScreen.clear();
//            displayCarByPrice
            System.out.println(CYAN_BOLD + "=====================================" + RESET);
            System.out.println(CYAN_BOLD + "           Client Menu" + RESET);
            System.out.println(CYAN_BOLD + "=====================================" + RESET);

            displayMenuHeader("CLIENT MENU", 53);
            System.out.println(GREEN_BOLD + "0. " + RESET + "View Transaction History");
            System.out.println(GREEN_BOLD + "1. " + RESET + "View Membership Details");
            System.out.println(GREEN_BOLD + "2. " + RESET + "View Service History");
            System.out.println(GREEN_BOLD + "3. " + RESET + "View Car ");
            System.out.println(GREEN_BOLD + "4. " + RESET + "Back to Main Menu");
            System.out.println(GREEN_BOLD + "5. " + RESET + "Exit");
            System.out.println(CYAN_BOLD + "=====================================" + RESET);

            System.out.print("Enter Selection (0-5) ");
            choice = getValidatedChoice(5);

            switch (choice) {
                case 0:
                    transactionService.displayClientTransactionHistory();
                    break;

                case 1:
                    userService.displayInfoUsersMembership();
                    break;
                case 2:
                    serviceService.displayClientServiceHistory();
                    break;

                case 3:
                    // the client just can view available car
                    carService.displayAllAvailableCar();
                case 4:
                    boolean confirmBack = InputValidation.validateBoolean("Are you sure you want to back to Main Menu? (yes/no): ");
                    if (confirmBack) {
                        System.out.println("Returning to main menu...");
                    }
                    return;

                case 5:
                    boolean confirmExit = InputValidation.validateBoolean("Are you sure you want to exit? (yes/no): ");
                    if (confirmExit) {
                        scanner.close();
                        System.out.println("Exiting the application... Goodbye!");
                        System.exit(0);

                    }
                    Divider.printDivider();
                    continue;
                default:
                    System.err.println("\n**Please, Enter a Valid Input**");
                    break;
            }

            System.out.println(CYAN_BOLD + "=====================================" + RESET);
        } while (choice != 6 && choice != 7);
    }
}
