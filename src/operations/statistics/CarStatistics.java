package operations.statistics;

import java.util.*;
import java.util.stream.Collectors;

import sales.PurchasedItem;
import sales.SalesTransaction;
import vehicle.Car;
import crudHandlers.SalesTransactionCRUD;

public class CarStatistics implements interfaces.statistics.CarStatisticsInterfaces {

    private SalesTransactionCRUD transactionCRUD;

    public CarStatistics(SalesTransactionCRUD transactionCRUD) {
        this.transactionCRUD = transactionCRUD;
    }

    @Override
    public List<Car> getSoldCarsByDay(Date date) {
        Date[] range = getDayRange(date);
        List<SalesTransaction> dayTransactions = getTransactionsByDateRange(range[0], range[1]);
        return getSoldCarsInTransactions(dayTransactions);
    }

    @Override
    public List<Car> getSoldCarsByWeek(Date date) {
        Date[] range = getWeekRange(date);
        List<SalesTransaction> weekTransactions = getTransactionsByDateRange(range[0], range[1]);
        return getSoldCarsInTransactions(weekTransactions);
    }

    @Override
    public List<Car> getSoldCarsByMonth(Date date) {
        Date[] range = getMonthRange(date);
        List<SalesTransaction> monthTransactions = getTransactionsByDateRange(range[0], range[1]);
        return getSoldCarsInTransactions(monthTransactions);
    }

    @Override
    public void displaySoldCars(List<Car> soldCars) {
        if (soldCars.isEmpty()) {
            System.out.println("No sold cars found.");
            return;
        }

        System.out.println(String.format("%-10s %-15s %-15s %-5s %-7s %-10s %-10s %-10s",
                "Car ID", "Make", "Model", "Year", "Mileage", "Color", "Price", "Notes"));
        System.out.println("------------------------------------------------------------------------------------");

        for (Car car : soldCars) {
            System.out.println(String.format("%-10s %-15s %-15s %-5d %-7d %-10s $%-9.2f %-10s",
                    car.getCarID(),
                    car.getMake(),
                    car.getModel(),
                    car.getYear(),
                    car.getMileage(),
                    car.getColor(),
                    car.getPrice(),
                    car.getNotes() != null ? car.getNotes() : "N/A"));
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

    // Method to get sold cars in a specific period
    private List<Car> getSoldCarsInTransactions(List<SalesTransaction> transactions) {
        List<Car> soldCars = new ArrayList<>();

        for (SalesTransaction transaction : transactions) {
            for (PurchasedItem item : transaction.getPurchaseItems()) {
                Car car = item.getCar();
                if (car != null && !car.getStatus()) { // Only include sold cars
                    soldCars.add(car);
                }
            }
        }

        return soldCars;
    }
}
