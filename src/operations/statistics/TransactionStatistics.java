package operations.statistics;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import interfaces.statistics.TransactionStatisticsInterfaces;
import sales.*;
import sales.SalesTransaction;
import crudhandlers.SalesTransactionCRUD;
import utils.DateRange;
import utils.InputValidation;

public class TransactionStatistics implements TransactionStatisticsInterfaces {

    DateRange dateRange = new DateRange();

    private final SalesTransactionCRUD transactionCRUD = new SalesTransactionCRUD();


    @Override
    public void getTransactionsByDay() {
        Date userInputDate = InputValidation.validateDate("Enter the date for the day(dd/mm/yy):");
        Date[] range = dateRange.getDayRange(userInputDate);
        List<SalesTransaction> dayTransactions = getTransactionsByDateRange(range[0], range[1]);
        displayTransactions(dayTransactions);
    }

    @Override
    public void getTransactionsByWeek() {
        Date userInputDate = InputValidation.validateDate("Enter the date for the week start(dd/mm/yy):");
        Date[] range = dateRange.getWeekRange(userInputDate);
        List<SalesTransaction> weekTransactions = getTransactionsByDateRange(range[0], range[1]);
        displayTransactions(weekTransactions);
    }

    @Override
    public void getTransactionsByMonth() {
        Date userInputDate = InputValidation.validateDate("Enter the date for the month start(dd/mm/yy):");
        Date[] range = dateRange.getMonthRange(userInputDate);
        List<SalesTransaction> monthTransactions = getTransactionsByDateRange(range[0], range[1]);
        displayTransactions(monthTransactions);
    }




    public void displayTransactions(List<SalesTransaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Print the header for transactions
        System.out.printf("%-15s %-12s %-10s %-15s %-10s %-10s %-12s %-50s%n",
                "TransactionID", "Date", "ClientID", "SalespersonID", "Items", "Discount", "Total", "Notes");
        System.out.println("------------------------------------------------------------------------------------------------------------");

        // Print each transaction
        for (SalesTransaction transaction : transactions) {
            String transactionID = transaction.getTransactionID();
            String date = sdf.format(transaction.getTransactionDate());
            String clientID = transaction.getClientID();
            String salespersonID = transaction.getSalespersonID();
            int numberOfItems = transaction.getPurchaseItems().size();
            double discount = transaction.getDiscount();
            double totalAmount = transaction.getTotalAmount();
            String notes = transaction.getNotes() != null ? transaction.getNotes() : "";

            // Print the main transaction details
            System.out.printf("%-15s %-12s %-10s %-15s %-10d %-10.2f %-12.2f %-50s%n",
                    transactionID,
                    date,
                    clientID,
                    salespersonID,
                    numberOfItems,
                    discount,
                    totalAmount,
                    notes);

            // Print the details of each purchased item
            System.out.println("    Items:");
            for (PurchasedItem item : transaction.getPurchaseItems()) {
                if (item.getCar() != null) {
                    System.out.printf("        CarID: %-10s Quantity: %-5d Price: %-8.2f%n",
                            item.getCar().getCarID(),
                            item.getCarQuantity(), // Assuming item.getCarQuality() gives the quantity
                            item.getCar().getPrice());
                } else if (item.getPart() != null) {
                    System.out.printf("        PartID: %-10s Quantity: %-5d Price: %-8.2f%n",
                            item.getPart().getPartID(),
                            item.getPartQuantity(), // Assuming item.getPartQuality() gives the quantity
                            item.getPart().getPrice());
                }
            }
            System.out.println(); // Add an empty line for better readability between transactions
        }
    }


    // Get transactions by date range
    private List<SalesTransaction> getTransactionsByDateRange(Date startDate, Date endDate) {
        List<SalesTransaction> allTransactions = transactionCRUD.getTransactionsOrderedByID(SalesTransactionCRUD.OrderType.transactionDate, true);

        return allTransactions.stream()
                .filter(transaction -> !transaction.getTransactionDate().before(startDate) && !transaction.getTransactionDate().after(endDate))
                .collect(Collectors.toList());
    }


}
