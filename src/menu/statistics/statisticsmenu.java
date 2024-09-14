package menu.statistics;
import interfaces.statistics.*;
import menu.Menu;
import operations.statistics.*;
import utils.ClearScreen;
import utils.Divider;


import static menu.MenuStyle.*;

public class statisticsmenu extends Menu {
    static CarStatisticsInterfaces carStatistics = new CarStatistics();
    static AutoPartStatisticsInterfaces autoPartStatistics = new AutoPartStatistics();
    static ServiceStatisticsInterfaces serviceStatistics = new ServiceStatistics();
    static TransactionStatisticsInterfaces transactionStatistics = new TransactionStatistics();
    static RevenueStatisticsInterfaces revenueStatistics = new RevenueStatistics();
    public void displayCarStatisticsMenu() {
        ClearScreen.clear();
        boolean back = false;
        while (!back) {
            System.out.println(CYAN_BOLD + "=====================================" + RESET);
            System.out.println(CYAN_BOLD + "   Car Statistics Menu" + RESET);
            System.out.println(CYAN_BOLD + "=====================================" + RESET);
            System.out.println();
            displayMenuHeader("CAR STATISTICS MENU", 43);
            displayOption(GREEN_BOLD + "0. " + RESET + "View Cars Sold in Month");
            displayOption(GREEN_BOLD + "1. " + RESET + "View Revenue from Cars");
            displayOption(GREEN_BOLD + "2. " + RESET + "View Cars Sold by Day");
            displayOption(GREEN_BOLD + "3. " + RESET + "View Cars Sold by Week");
            displayOption(GREEN_BOLD + "4. " + RESET + "View Cars Sold by Month");
            displayOption(GREEN_BOLD + "5. " + RESET + "Back");
            Divider.printDivider(46);

            System.out.print("Enter Selection (0-5): ");
            int choice = getValidatedChoice(0, 5);

            switch (choice) {
                case 0: carStatistics.calculateCarsSoldInMonth(); break;
                case 1: carStatistics.calculateRevenueFromCars(); break;
                case 2: carStatistics.getSoldCarsByDay(); break;
                case 3: carStatistics.getSoldCarsByWeek(); break;
                case 4: carStatistics.getSoldCarsByMonth(); break;
                case 5: back = true; break;
                default: System.err.println("\n**Please, Enter a Valid Input**");
            }
        }
    }

    // Auto Part Statistics Menu
    public void displayAutoPartStatisticsMenu() {
        ClearScreen.clear();
        boolean back = false;
        while (!back) {
            System.out.println(CYAN_BOLD + "=====================================" + RESET);
            System.out.println(CYAN_BOLD + "   Auto Part Statistics Menu" + RESET);
            System.out.println(CYAN_BOLD + "=====================================" + RESET);
            System.out.println();
            displayMenuHeader("AUTO PART STATISTICS MENU", 43);
            displayOption(GREEN_BOLD + "0. " + RESET + "View Parts Sold by Day");
            displayOption(GREEN_BOLD + "1. " + RESET + "View Parts Sold by Week");
            displayOption(GREEN_BOLD + "2. " + RESET + "View Parts Sold by Month");
            displayOption(GREEN_BOLD + "3. " + RESET + "Back");
            Divider.printDivider(46);

            System.out.print("Enter Selection (0-3): ");
            int choice = getValidatedChoice(0, 3);

            switch (choice) {
                case 0: autoPartStatistics.getSoldPartsByDay(); break;
                case 1: autoPartStatistics.getSoldPartsByWeek(); break;
                case 2: autoPartStatistics.getSoldPartsByMonth(); break;
                case 3: back = true; break;
                default: System.err.println("\n**Please, Enter a Valid Input**");
            }
        }
    }

    // Service Statistics Menu
    public void displayServiceStatisticsMenu() {
        ClearScreen.clear();
        boolean back = false;
        while (!back) {
            System.out.println(CYAN_BOLD + "=====================================" + RESET);
            System.out.println(CYAN_BOLD + "   Service Statistics Menu" + RESET);
            System.out.println(CYAN_BOLD + "=====================================" + RESET);
            System.out.println();
            displayMenuHeader("SERVICE STATISTICS MENU", 43);
            displayOption(GREEN_BOLD + "0. " + RESET + "View Services by Day");
            displayOption(GREEN_BOLD + "1. " + RESET + "View Services by Week");
            displayOption(GREEN_BOLD + "2. " + RESET + "View Services by Month");
            displayOption(GREEN_BOLD + "3. " + RESET + "View Revenue from Services");
            displayOption(GREEN_BOLD + "4. " + RESET + "Back");
            Divider.printDivider(46);

            System.out.print("Enter Selection (0-4): ");
            int choice = getValidatedChoice(0, 4);

            switch (choice) {
                case 0: serviceStatistics.getServicesByDay(); break;
                case 1: serviceStatistics.getServicesByWeek(); break;
                case 2: serviceStatistics.getServicesByMonth(); break;
                case 3: serviceStatistics.calculateRevenueFromServices(); break;
                case 4: back = true; break;
                default: System.err.println("\n**Please, Enter a Valid Input**");
            }
        }
    }

    public void displayTransactionStatisticsMenu() {
        ClearScreen.clear();
        boolean back = false;
        while (!back) {
            System.out.println(CYAN_BOLD + "=====================================" + RESET);
            System.out.println(CYAN_BOLD + "   Transaction Statistics Menu" + RESET);
            System.out.println(CYAN_BOLD + "=====================================" + RESET);
            System.out.println();
            displayMenuHeader("TRANSACTION STATISTICS MENU", 43);
            displayOption(GREEN_BOLD + "0. " + RESET + "View Transactions by Day");
            displayOption(GREEN_BOLD + "1. " + RESET + "View Transactions by Week");
            displayOption(GREEN_BOLD + "2. " + RESET + "View Transaction by Month");
            displayOption(GREEN_BOLD + "3. " + RESET + "Back");
            Divider.printDivider(46);

            System.out.print("Enter Selection (0-3): ");
            int choice = getValidatedChoice(0, 3);
            switch (choice) {
                case 0: transactionStatistics.getTransactionsByDay(); break;
                case 1: transactionStatistics.getTransactionsByWeek(); break;
                case 2: transactionStatistics.getTransactionsByMonth(); break;
                case 3: back = true; break;
                default: System.err.println("\n**Please, Enter a Valid Input**");
            }
        }
    }

    public void displayRevenueStatisticsMenu() {
        ClearScreen.clear();
        boolean back = false;
        while (!back) {
            System.out.println(CYAN_BOLD + "=====================================" + RESET);
            System.out.println(CYAN_BOLD + "    Revenue Statistics Menu" + RESET);
            System.out.println(CYAN_BOLD + "=====================================" + RESET);
            System.out.println();
            displayMenuHeader("REVENUE STATISTICS MENU", 43);
            displayOption(GREEN_BOLD + "0. " + RESET + "View Revenue by Day");
            displayOption(GREEN_BOLD + "1. " + RESET + "View Revenue by Week");
            displayOption(GREEN_BOLD + "2. " + RESET + "View Revenue by Month");
            displayOption(GREEN_BOLD + "3. " + RESET + "Back");
            Divider.printDivider(46);

            System.out.print("Enter Selection (0-3): ");
            int choice = getValidatedChoice(0, 3);
            switch (choice) {
                case 0: revenueStatistics.calculateTotalRevenueForDay(); break;
                case 1: revenueStatistics.calculateTotalRevenueByWeek(); break;
                case 2: revenueStatistics.calculateTotalRevenueByMonth(); break;
                case 3: back = true; break;
                default: System.err.println("\n**Please, Enter a Valid Input**");
            }
        }
    }
}
