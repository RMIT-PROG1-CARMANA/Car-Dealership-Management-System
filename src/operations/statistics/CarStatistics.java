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
    private List<SalesTransaction> transactions;

    private String salespersonID;
    private SalesTransactionCRUD transactionCRUD;


    // Method to calculate number of cars sold in a specific month
    @Override
    public void calculateCarsSoldInMonth() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the year (e.g., 2024): ");
        int year = scanner.nextInt();

        System.out.print("Enter the month (1-12): ");
        int month = scanner.nextInt();

        // Validate month input
        if (month < 1 || month > 12) {
            System.out.println("Invalid month. Please enter a value between 1 and 12.");
            return;
        }

        int carsSold = 0;

        // Iterate over transactions and count cars sold
        for (SalesTransaction transaction : transactions) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(transaction.getTransactionDate());
            int transYear = cal.get(Calendar.YEAR);
            int transMonth = cal.get(Calendar.MONTH) + 1; // Months are 0-based in Calendar

            if (transYear == year && transMonth == month) {
                for (PurchasedItem item : transaction.getPurchaseItems()) {
                    if (item.getCar() != null) {
                        carsSold++;
                    }
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
        Date userInputDate = InputValidation.validateDate("Enter the date");
        Date[] range = dateRange.getMonthRange(userInputDate);
        List<SalesTransaction> weekTransactions = getTransactionsByDateRange(range[0], range[1]);
        List<Car> soldCars = getSoldCarsInTransactions(weekTransactions);
        displaySoldCars(soldCars);
    }

    @Override
    public void getSoldCarsByMonth() {
        Date userInputDate = InputValidation.validateDate("Enter the date");
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
