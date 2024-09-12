package operations.statistics;

import interfaces.statistics.RevenueStatisticsInterfaces;
import sales.SalesTransaction;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class RevenueStatistics implements RevenueStatisticsInterfaces {

    private Date getStartOfWeek(int year, int week) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear(); // Clear all fields
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // Start of the week is Monday
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private Date getEndOfWeek(int year, int week) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear(); // Clear all fields
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); // End of the week is Sunday
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    private Date getStartOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear(); // Clear all fields
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1); // Start of the month
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private Date getEndOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear(); // Clear all fields
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH)); // End of the month
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }


    private List<SalesTransaction> allTransactions;
    private Date userInputDate;

    public void calculateTotalRevenueForDay() {
        if (userInputDate == null) {
            System.out.println("User input date is not set.");
            return;
        }

        // Get the start and end of the day range
        Date[] dayRange = getDayRange(userInputDate);
        Date startOfDay = dayRange[0];
        Date endOfDay = dayRange[1];

        // Filter transactions by the specific day
        List<SalesTransaction> transactionsForDay = allTransactions.stream()
                .filter(transaction -> {
                    Date transactionDate = transaction.getTransactionDate();
                    return !transactionDate.before(startOfDay) && !transactionDate.after(endOfDay);
                })
                .toList();

        // Calculate total revenue
        double totalRevenue = transactionsForDay.stream()
                .mapToDouble(SalesTransaction::getTotalAmount)
                .sum();

        System.out.println("Total Revenue for " + userInputDate + ": " + totalRevenue);
    }

    public void calculateTotalRevenueByWeek() {
        if (userInputDate == null) {
            System.out.println("User input date is not set.");
            return;
        }

        // Get the start and end of the week range
        Date[] weekRange = getWeekRange(userInputDate);
        Date startOfWeek = weekRange[0];
        Date endOfWeek = weekRange[1];

        // Filter transactions by the specific week
        List<SalesTransaction> transactionsForWeek = allTransactions.stream()
                .filter(transaction -> {
                    Date transactionDate = transaction.getTransactionDate();
                    return !transactionDate.before(startOfWeek) && !transactionDate.after(endOfWeek);
                })
                .toList();

        // Calculate total revenue
        double totalRevenue = transactionsForWeek.stream()
                .mapToDouble(SalesTransaction::getTotalAmount)
                .sum();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(userInputDate);
        int year = calendar.get(Calendar.YEAR);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);

        System.out.println("Total Revenue for Week " + week + " of " + year + ": " + totalRevenue);
    }

    public void calculateTotalRevenueByMonth() {
        if (userInputDate == null) {
            System.out.println("User input date is not set.");
            return;
        }

        // Get the start and end of the month range
        Date[] monthRange = getMonthRange(userInputDate);
        Date startOfMonth = monthRange[0];
        Date endOfMonth = monthRange[1];

        // Filter transactions by the specific month
        List<SalesTransaction> transactionsForMonth = allTransactions.stream()
                .filter(transaction -> {
                    Date transactionDate = transaction.getTransactionDate();
                    return !transactionDate.before(startOfMonth) && !transactionDate.after(endOfMonth);
                })
                .toList();

        // Calculate total revenue
        double totalRevenue = transactionsForMonth.stream()
                .mapToDouble(SalesTransaction::getTotalAmount)
                .sum();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(userInputDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        System.out.println("Total Revenue for " + month + " " + year + ": " + totalRevenue);
    }


}
