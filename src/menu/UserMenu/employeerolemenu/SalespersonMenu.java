package menu.usermenu.employeerolemenu;

import interfaces.TransactionInterfaces;
import interfaces.statistics.RevenueStatisticsInterfaces;
import interfaces.statistics.TransactionStatisticsInterfaces;
import menu.usermenu.EmployeeBaseMenu;
import operations.TransactionService;
import operations.statistics.RevenueStatistics;
import operations.statistics.TransactionStatistics;
import user.Authenticator;
import utils.*;

import static menu.MenuStyle.*;

public class SalespersonMenu extends EmployeeBaseMenu {
    static TransactionInterfaces transactionService = new TransactionService();
    static RevenueStatisticsInterfaces revenueStatistics = new RevenueStatistics();
    static TransactionStatisticsInterfaces transactionStatistics = new TransactionStatistics();
    @Override
    public void displayEmployeeMenu() {
        int choice;

        do {
            // Clear the screen
            ClearScreen.clear();

            System.out.println(CYAN_BOLD + "=====================================" + RESET);
            System.out.println(CYAN_BOLD + "     Welcome, Salesperson!" + RESET);
            System.out.println(CYAN_BOLD + "=====================================" + RESET);
            System.out.println();
            displayMenuHeader("Salesperson Menu", 43);
            displayOption(GREEN_BOLD + "0. " + RESET + "Add Sales Transaction");
            displayOption(GREEN_BOLD + "1. " + RESET + "View Sales Transactions");
            displayOption(GREEN_BOLD + "2. " + RESET + "Calculate Revenue (Day/Week/Month)");
            displayOption(GREEN_BOLD + "3. " + RESET + "List Number of Sales Transaction(Day/Week/Month)");
            displayOption(GREEN_BOLD + "4. " + RESET + "Back to Main Menu");
            displayOption(GREEN_BOLD + "5. " + RESET + "Exit");
            Divider.printDivider(46);

            choice = getValidatedChoice(7);

            switch (choice) {
                case 0:
                    transactionService.addTransaction();
                    break;
                case 1:
                    transactionService.displayAllTransactions();
                    break;
                case 2:
                    System.out.println("Number of transaction in specific day");
                    revenueStatistics.calculateTotalRevenueForDay();
                    System.out.println("Number of transaction in specific week");
                    revenueStatistics.calculateTotalRevenueByWeek();
                    System.out.println("Number of transaction in specific month");
                    revenueStatistics.calculateTotalRevenueByMonth();
                    break;

                case 3:
                    System.out.println("Revenue in specific day");
                    transactionStatistics.getTransactionsByDay();
                    System.out.println("Revenue in specific day");
                    transactionStatistics.getTransactionsByWeek();
                    System.out.println("Revenue in specific day");
                    transactionStatistics.getTransactionsByMonth();
                    break;

                case 4:
                    // Return to main menu
                    boolean confirmBack = InputValidation.validateBoolean("Are you sure you want to back to Main Menu? (yes/no): ");
                    if (confirmBack) {
                        System.out.println("Returning to main menu...");
                    }
                    return;

                case 5:
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
        } while (choice != 5);
    }

   

}
