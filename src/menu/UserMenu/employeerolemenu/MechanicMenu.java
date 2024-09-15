package menu.usermenu.employeerolemenu;


import interfaces.AutoPartInterfaces;
import interfaces.TransactionInterfaces;
import interfaces.statistics.RevenueStatisticsInterfaces;
import interfaces.statistics.ServiceStatisticsInterfaces;
import menu.usermenu.EmployeeBaseMenu;
import operations.AutoPartService;
import operations.TransactionService;
import operations.statistics.RevenueStatistics;
import operations.statistics.ServiceStatistics;
import user.Authenticator;
import utils.Divider;
import utils.*;
import java.util.*;

import static menu.MenuStyle.*;

public class MechanicMenu extends EmployeeBaseMenu {
    static AutoPartInterfaces autoPartService = new AutoPartService();
    static TransactionInterfaces transactionService = new TransactionService();
    static RevenueStatisticsInterfaces revenueStatistics = new RevenueStatistics();
    static ServiceStatisticsInterfaces serviceStatistics = new ServiceStatistics();

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
            System.out.println();
            displayMenuHeader("Mechanic Menu", 43);
            displayOption(GREEN_BOLD + "0. " + RESET + "Add Service Transaction");
            displayOption(GREEN_BOLD + "1. " + RESET + "View Service Transactions");
            displayOption(GREEN_BOLD + "2. " + RESET + "Update Vehicle Status");
            displayOption(GREEN_BOLD + "3. " + RESET + "List Number of Services(Day/Week/Month)");
            displayOption(GREEN_BOLD + "4. " + RESET + "Calculate Revenue (Day/Week/Month)");
            displayOption(GREEN_BOLD + "5. " + RESET + "View Parts Inventory");
            displayOption(GREEN_BOLD + "6. " + RESET + "Back to Main Menu");
            displayOption(GREEN_BOLD + "7. " + RESET + "Exit");
            Divider.printDivider(46);

            System.out.print("Enter Selection (0-7): ");
            choice = getValidatedChoice(7);

            switch (choice) {
                case 0:
                    transactionService.addTransaction();
                    break;
                case 1:
                    transactionService.displayAllTransactions();
                    break;
                case 2:
                    System.out.println("Revenue in specific day");
                    revenueStatistics.calculateTotalRevenueForDay();
                    System.out.println("Revenue in specific week");
                    revenueStatistics.calculateTotalRevenueByWeek();
                    System.out.println("Revenue in specific month");
                    revenueStatistics.calculateTotalRevenueByMonth();
                    break;
                case 3:

                    break;
                case 4:
                    System.out.println("Number of service in specific day");
                    serviceStatistics.getServicesByDay();
                    System.out.println("Number of service in specific week");
                    serviceStatistics.getServicesByWeek();
                    System.out.println("Number of service in specific month");
                    serviceStatistics.getServicesByMonth();
                    break;

                case 5:
                    autoPartService.listAllParts();
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
        } while (choice != 7);
    }
}