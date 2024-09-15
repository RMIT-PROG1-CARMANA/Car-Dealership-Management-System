package operations.statistics;

import java.util.*;
import java.util.stream.Collectors;

import interfaces.statistics.AutoPartStatisticsInterfaces;
import sales.PurchasedItem;
import sales.SalesTransaction;
import part.AutoPart;
import crudhandlers.SalesTransactionCRUD;
import utils.DateRange;
import utils.InputValidation;


public class AutoPartStatistics implements AutoPartStatisticsInterfaces {

    DateRange dateRange = new DateRange();
    private final SalesTransactionCRUD transactionCRUD = new SalesTransactionCRUD();
    private final List<PurchasedItem> purchaseItems = new ArrayList<>();


    @Override
    public void getSoldPartsByDay() {

        Date userInputDate = InputValidation.validateDate("Enter the date(dd/mm/yy): ");
        Date[] range = dateRange.getDayRange(userInputDate);
        List<SalesTransaction> dayTransactions = getTransactionsByDateRange(range[0], range[1]);
        List<AutoPart> soldParts = getSoldPartsInTransactions(dayTransactions);
        displaySoldParts(soldParts);
    }

    @Override
    public void getSoldPartsByWeek() {

        Date userInputDate = InputValidation.validateDate("Enter the date(dd/mm/yy): ");
        Date[] range = dateRange.getWeekRange(userInputDate);
        List<SalesTransaction> weekTransactions = getTransactionsByDateRange(range[0], range[1]);
        List<AutoPart> soldParts = getSoldPartsInTransactions(weekTransactions);
        displaySoldParts(soldParts);
    }

    @Override
    public void getSoldPartsByMonth() {

        Date userInputDate = InputValidation.validateDate("Enter the date(dd/mm/yy): ");
        Date[] range = dateRange.getMonthRange(userInputDate);
        List<SalesTransaction> monthTransactions = getTransactionsByDateRange(range[0], range[1]);
        List<AutoPart> soldParts = getSoldPartsInTransactions(monthTransactions);
        displaySoldParts(soldParts);
    }
    public void displaySoldParts(List<AutoPart> soldParts) {
        if (soldParts.isEmpty()) {
            System.out.println("No sold parts found.");
            return;
        }
        for (PurchasedItem item : purchaseItems) {
            AutoPart part = item.getPart();

            int qualityPart = item.getPartQuantity();

            // Print the part and car details
            if (part != null) {
                System.out.println("Part Name: " + part.getPartName() + ", Part Quality: " + qualityPart);
            }
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
