package operations.statistics;

import interfaces.statistics.RevenueStatisticsInterfaces;
import sales.SalesTransaction;
import utils.DateRange;
import utils.InputValidation;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RevenueStatistics implements RevenueStatisticsInterfaces {
    DateRange dateRange = new DateRange();

    private List<SalesTransaction> allTransactions;

    private List<SalesTransaction> filterTransactionsByDateRange(Date startDate, Date endDate) {
        return allTransactions.stream()
                .filter(transaction -> {
                    Date transactionDate = transaction.getTransactionDate();
                    return !transactionDate.before(startDate) && !transactionDate.after(endDate);
                })
                .toList();
    }

    private double calculateTotalRevenue(List<SalesTransaction> transactions) {
        return transactions.stream()
                .mapToDouble(SalesTransaction::getTotalAmount)
                .sum();
    }
    @Override
    public void calculateTotalRevenueForDay() {
        Date userInputDate = InputValidation.validateDate("Enter the date:");
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
        Date userInputDate = InputValidation.validateDate("Enter the date:");
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
        Date userInputDate = InputValidation.validateDate("Enter the date:");
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
