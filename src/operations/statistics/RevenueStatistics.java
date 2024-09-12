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


//    public void calculateTotalRevenueForDay() {
//
//        Date userInputDate = InputValidation.validateDate("Enter the date:");
//        // Get the start and end of the day range
//        Date[] dayRange = dateRange.getDayRange(userInputDate);
//        Date startOfDay = dayRange[0];
//        Date endOfDay = dayRange[1];
//
//        // Filter transactions by the specific day
//        List<SalesTransaction> transactionsForDay = allTransactions.stream()
//                .filter(transaction -> {
//                    Date transactionDate = transaction.getTransactionDate();
//                    return !transactionDate.before(startOfDay) && !transactionDate.after(endOfDay);
//                })
//                .toList();
//
//        // Calculate total revenue
//        double totalRevenue = transactionsForDay.stream()
//                .mapToDouble(SalesTransaction::getTotalAmount)
//                .sum();
//
//        System.out.println("Total Revenue for " + userInputDate + ": " + totalRevenue);
//    }
//
//    public void calculateTotalRevenueByWeek() {
//
//        Date userInputDate = InputValidation.validateDate("Enter the date:");
//        // Get the start and end of the week range
//        Date[] weekRange = dateRange.getWeekRange(userInputDate);
//        Date startOfWeek = weekRange[0];
//        Date endOfWeek = weekRange[1];
//
//        // Filter transactions by the specific week
//        List<SalesTransaction> transactionsForWeek = allTransactions.stream()
//                .filter(transaction -> {
//                    Date transactionDate = transaction.getTransactionDate();
//                    return !transactionDate.before(startOfWeek) && !transactionDate.after(endOfWeek);
//                })
//                .toList();
//
//        // Calculate total revenue
//        double totalRevenue = transactionsForWeek.stream()
//                .mapToDouble(SalesTransaction::getTotalAmount)
//                .sum();
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(userInputDate);
//        int year = calendar.get(Calendar.YEAR);
//        int week = calendar.get(Calendar.WEEK_OF_YEAR);
//
//        System.out.println("Total Revenue for Week " + week + " of " + year + ": " + totalRevenue);
//    }
//
//    public void calculateTotalRevenueByMonth() {
//        Date userInputDate = InputValidation.validateDate("Enter the date:");
//        // Get the start and end of the month range
//        Date[] monthRange = dateRange.getMonthRange(userInputDate);
//        Date startOfMonth = monthRange[0];
//        Date endOfMonth = monthRange[1];
//
//        // Filter transactions by the specific month
//        List<SalesTransaction> transactionsForMonth = allTransactions.stream()
//                .filter(transaction -> {
//                    Date transactionDate = transaction.getTransactionDate();
//                    return !transactionDate.before(startOfMonth) && !transactionDate.after(endOfMonth);
//                })
//                .toList();
//
//        // Calculate total revenue
//        double totalRevenue = transactionsForMonth.stream()
//                .mapToDouble(SalesTransaction::getTotalAmount)
//                .sum();
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(userInputDate);
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//
//        System.out.println("Total Revenue for " + month + " " + year + ": " + totalRevenue);
//    }

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
