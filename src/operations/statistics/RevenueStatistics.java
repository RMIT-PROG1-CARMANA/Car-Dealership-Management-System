package operations.statistics;

import crudhandlers.SalesTransactionCRUD;
import interfaces.statistics.RevenueStatisticsInterfaces;
import sales.SalesTransaction;
import utils.DateRange;
import utils.InputValidation;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class RevenueStatistics implements RevenueStatisticsInterfaces {
    DateRange dateRange = new DateRange();

    private final SalesTransactionCRUD transactionCRUD = new SalesTransactionCRUD();

    private List<SalesTransaction> filterTransactionsByDateRange(Date startDate, Date endDate) {
        List<SalesTransaction> allTransactions = transactionCRUD.getTransactionsOrderedByID(SalesTransactionCRUD.OrderType.transactionDate, true);

        return allTransactions.stream()
                .filter(transaction -> !transaction.getTransactionDate().before(startDate) && !transaction.getTransactionDate().after(endDate))
                .collect(Collectors.toList());
    }

    private double calculateTotalRevenue(List<SalesTransaction> transactions) {
        return transactions.stream()
                .mapToDouble(SalesTransaction::getTotalAmount)
                .sum();
    }
    @Override
    public void calculateTotalRevenueForDay() {
        Date userInputDate = InputValidation.validateDate("Enter the date(dd/mm/yy): ");
        Date[] dayRange = dateRange.getDayRange(userInputDate);
        Date startOfDay = dayRange[0];
        Date endOfDay = dayRange[1];

        // Reuse filterTransactionsByDateRange and calculateTotalRevenue
        List<SalesTransaction> transactionsForDay = filterTransactionsByDateRange(startOfDay, endOfDay);
        double totalRevenue = calculateTotalRevenue(transactionsForDay);

        System.out.println("Total Revenue for " + userInputDate + ": " + totalRevenue);
    }
    @Override
    public void calculateTotalRevenueByWeek() {
        Date userInputDate = InputValidation.validateDate("Enter the date(dd/mm/yy): ");
        Date[] weekRange = dateRange.getWeekRange(userInputDate);
        Date startOfWeek = weekRange[0];
        Date endOfWeek = weekRange[1];

        // Reuse filterTransactionsByDateRange and calculateTotalRevenue
        List<SalesTransaction> transactionsForWeek = filterTransactionsByDateRange(startOfWeek, endOfWeek);
        double totalRevenue = calculateTotalRevenue(transactionsForWeek);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(userInputDate);
        int year = calendar.get(Calendar.YEAR);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);

        System.out.println("Total Revenue for Week " + week + " of " + year + ": " + totalRevenue);
    }
    @Override
    public void calculateTotalRevenueByMonth() {
        Date userInputDate = InputValidation.validateDate("Enter the date(dd/mm/yy): ");
        Date[] monthRange = dateRange.getMonthRange(userInputDate);
        Date startOfMonth = monthRange[0];
        Date endOfMonth = monthRange[1];

        // Reuse filterTransactionsByDateRange and calculateTotalRevenue
        List<SalesTransaction> transactionsForMonth = filterTransactionsByDateRange(startOfMonth, endOfMonth);
        double totalRevenue = calculateTotalRevenue(transactionsForMonth);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(userInputDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Add 1 since Calendar.MONTH is zero-based

        System.out.println("Total Revenue for " + month + " " + year + ": " + totalRevenue);
    }

}
