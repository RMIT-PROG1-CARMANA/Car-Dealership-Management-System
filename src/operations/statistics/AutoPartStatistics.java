package operations.statistics;

import java.util.*;
import java.util.stream.Collectors;

import sales.PurchasedItem;
import sales.SalesTransaction;
import part.AutoPart;
import crudHandlers.SalesTransactionCRUD;

public class AutoPartStatistics implements interfaces.statistics.AutoPartStatisticsInterfaces {

    private SalesTransactionCRUD transactionCRUD;

    public AutoPartStatistics(SalesTransactionCRUD transactionCRUD) {
        this.transactionCRUD = transactionCRUD;
    }

    @Override
    public List<AutoPart> getSoldPartsByDay(Date date) {
        Date[] range = getDayRange(date);
        List<SalesTransaction> dayTransactions = getTransactionsByDateRange(range[0], range[1]);
        return getSoldPartsInTransactions(dayTransactions);
    }

    @Override
    public List<AutoPart> getSoldPartsByWeek(Date date) {
        Date[] range = getWeekRange(date);
        List<SalesTransaction> weekTransactions = getTransactionsByDateRange(range[0], range[1]);
        return getSoldPartsInTransactions(weekTransactions);
    }

    @Override
    public List<AutoPart> getSoldPartsByMonth(Date date) {
        Date[] range = getMonthRange(date);
        List<SalesTransaction> monthTransactions = getTransactionsByDateRange(range[0], range[1]);
        return getSoldPartsInTransactions(monthTransactions);
    }

    @Override
    public void displaySoldParts(List<AutoPart> soldParts) {
        if (soldParts.isEmpty()) {
            System.out.println("No sold parts found.");
            return;
        }

        System.out.println(String.format("%-10s %-20s %-15s %-15s %-10s %-10s %-10s %-20s",
                "Part ID", "Part Name", "Manufacturer", "Part Number", "Condition", "Warranty", "Price", "Notes"));
        System.out.println("------------------------------------------------------------------------------------------");

        for (AutoPart part : soldParts) {
            System.out.println(String.format("%-10s %-20s %-15s %-15s %-10s %-10s $%-9.2f %-20s",
                    part.getPartID(),
                    part.getPartName(),
                    part.getManufacturer(),
                    part.getPartNumber(),
                    part.getCondition(),
                    part.getWarranty(),
                    part.getPrice(),
                    part.getNotes() != null ? part.getNotes() : "N/A"));
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

    // Method to get sold parts in a specific period
    private List<AutoPart> getSoldPartsInTransactions(List<SalesTransaction> transactions) {
        List<AutoPart> soldParts = new ArrayList<>();

        for (SalesTransaction transaction : transactions) {
            for (PurchasedItem item : transaction.getPurchaseItems()) {
                AutoPart part = item.getPart();
                if (part != null) { // Only include sold parts
                    soldParts.add(part);
                }
            }
        }

        return soldParts;
    }
}
