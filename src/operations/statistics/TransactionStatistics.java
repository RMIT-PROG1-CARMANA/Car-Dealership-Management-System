package operations.statistics;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import part.AutoPart;
import sales.*;
import sales.SalesTransaction;
import crudHandlers.SalesTransactionCRUD;

public class TransactionStatistics implements interfaces.statistics.TransactionStatisticsInterfaces {

    private SalesTransactionCRUD transactionCRUD;

    public TransactionStatistics(SalesTransactionCRUD transactionCRUD) {
        this.transactionCRUD = transactionCRUD;
    }

    @Override
    public List<SalesTransaction> getTransactionsByDay(Date date) {
        Date[] range = getDayRange(date);
        return getTransactionsByDateRange(range[0], range[1]);
    }

    @Override
    public List<SalesTransaction> getTransactionsByWeek(Date date) {
        Date[] range = getWeekRange(date);
        return getTransactionsByDateRange(range[0], range[1]);
    }

    @Override
    public List<SalesTransaction> getTransactionsByMonth(Date date) {
        Date[] range = getMonthRange(date);
        return getTransactionsByDateRange(range[0], range[1]);
    }

    @Override
    import java.text.SimpleDateFormat;
import java.util.List;

    public void displayTransactions(List<SalesTransaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Print the header for transactions
        System.out.println(String.format("%-15s %-12s %-10s %-15s %-10s %-10s %-12s %-50s",
                "TransactionID", "Date", "ClientID", "SalespersonID", "Items", "Discount", "Total", "Notes"));
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
            System.out.println(String.format("%-15s %-12s %-10s %-15s %-10d %-10.2f %-12.2f %-50s",
                    transactionID,
                    date,
                    clientID,
                    salespersonID,
                    numberOfItems,
                    discount,
                    totalAmount,
                    notes));

            // Print the details of each purchased item
            System.out.println("    Items:");
            for (PurchasedItem item : transaction.getPurchaseItems()) {
                if (item.getCar() != null) {
                    System.out.println(String.format("        CarID: %-10s Quantity: %-5d Price: %-8.2f",
                            item.getCar().getCarID(),
                            1, // Assuming 1 car per purchased item
                            item.getCar().getPrice()));
                } else if (item.getPart() != null) {
                    System.out.println(String.format("        PartID: %-10s Quantity: %-5d Price: %-8.2f",
                            item.getPart().getPartID(),
                            1, // Assuming 1 part per purchased item
                            item.getPart().getPrice()));
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

    // Get the start and end of the day for filtering
    private Date[] getDayRange(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date start = cal.getTime();

        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date end = cal.getTime();

        return new Date[]{start, end};
    }

    // Get the start and end of the week for filtering
    private Date[] getWeekRange(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date start = cal.getTime();

        cal.add(Calendar.WEEK_OF_YEAR, 1);
        Date end = cal.getTime();

        return new Date[]{start, end};
    }

    // Get the start and end of the month for filtering
    private Date[] getMonthRange(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date start = cal.getTime();

        cal.add(Calendar.MONTH, 1);
        Date end = cal.getTime();

        return new Date[]{start, end};
    }
}
