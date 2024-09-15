package operations.statistics;

import java.util.*;
import java.util.stream.Collectors;

import sales.PurchasedItem;
import sales.SalesTransaction;
import utils.DateRange;
import utils.InputValidation;
import vehicle.Car;
import crudhandlers.SalesTransactionCRUD;
import interfaces.statistics.CarStatisticsInterfaces;


import java.util.List;

public class CarStatistics implements CarStatisticsInterfaces {
    DateRange dateRange = new DateRange();
    private final List<SalesTransaction> transactions = new ArrayList<>();

    private String salespersonID;
    private final SalesTransactionCRUD transactionCRUD = new SalesTransactionCRUD();


    // Method to calculate number of cars sold in a specific month
    @Override
    public void calculateCarsSoldInMonth() {
        Date userInputDate = InputValidation.validateDate("Enter the date (dd/MM/yyyy): ");
        Date[] range = dateRange.getMonthRange(userInputDate);
        List<SalesTransaction> monthTransactions = getTransactionsByDateRange(range[0], range[1]);
        int carsSold = 0;

        // Extract month and year for reporting
        Calendar calendar = Calendar.getInstance();
        assert userInputDate != null;
        calendar.setTime(userInputDate);
        int month = calendar.get(Calendar.MONTH) + 1; // Calendar.MONTH is zero-based
        int year = calendar.get(Calendar.YEAR);

        // Iterate over transactions and count cars sold
        for (SalesTransaction transaction : monthTransactions) {
            for (PurchasedItem item : transaction.getPurchaseItems()) {
                if (item.getCar() != null) {
                    carsSold++;
                }
            }
        }
        System.out.println("Number of cars sold in " + month + "/" + year + ": " + carsSold);
    }
    //Calculate the revenue of the cars sold of a salesperson
        @Override
        public void calculateRevenueFromCars() {
            double totalRevenue = 0.0;

            for (SalesTransaction transaction : transactions) {
                // Check if the transaction's salesperson matches the given ID
                if (transaction.getSalespersonID().equals(salespersonID)) {
                    // Sum up the total amount of the transaction
                    totalRevenue += transaction.getTotalAmount();
                }
            }

            // Print the total revenue
            System.out.println("Total revenue from cars sold by salesperson ID " + salespersonID + ": " + totalRevenue);
        }

    @Override
    public void getSoldCarsByDay() {
        Date userInputDate = InputValidation.validateDate("Enter the date");
        Date[] range = dateRange.getMonthRange(userInputDate);
        List<SalesTransaction> dayTransactions = getTransactionsByDateRange(range[0], range[1]);
        List<Car> soldCars = getSoldCarsInTransactions(dayTransactions);
        displaySoldCars(soldCars);
    }

    @Override
    public void getSoldCarsByWeek() {
        Date userInputDate = InputValidation.validateDate("Enter the date(dd/mm/yy): ");
        Date[] range = dateRange.getMonthRange(userInputDate);
        List<SalesTransaction> weekTransactions = getTransactionsByDateRange(range[0], range[1]);
        List<Car> soldCars = getSoldCarsInTransactions(weekTransactions);
        displaySoldCars(soldCars);
    }

    @Override
    public void getSoldCarsByMonth() {
        Date userInputDate = InputValidation.validateDate("Enter the date(dd/mm/yy): ");
        Date[] range = dateRange.getMonthRange(userInputDate);
        List<SalesTransaction> monthTransactions = getTransactionsByDateRange(range[0], range[1]);
        List<Car> soldCars = getSoldCarsInTransactions(monthTransactions);
        displaySoldCars(soldCars);
    }

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
