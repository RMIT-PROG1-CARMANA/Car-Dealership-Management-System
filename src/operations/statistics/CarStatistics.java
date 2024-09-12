package operations.statistics;

import interfaces.statistics.CarStatisticsInterfaces;
import sales.PurchasedItem;
import sales.SalesTransaction;

import java.util.*;
import java.util.List;

public class CarStatistics implements CarStatisticsInterfaces {
    private List<SalesTransaction> transactions;

    private String salespersonID;


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
}
