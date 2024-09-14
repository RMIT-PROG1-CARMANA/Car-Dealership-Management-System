package operations;

import FileHandling.CarDataHandler;
import FileHandling.UserDataHandler;
import interfaces.TransactionInterfaces;
import logsystem.ActivityLog;
import part.AutoPart;
import sales.SalesTransaction;
import user.Client;
import utils.InputValidation;
import crudHandlers.CarCRUDMethodHandler;
import crudHandlers.SalesTransactionCRUD;
import sales.PurchasedItem;
import user.Membership;
import vehicle.Car;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static user.Authenticator.loggedUser;

public class TransactionService implements TransactionInterfaces {

    Scanner scanner = new Scanner(System.in);
    static UserDataHandler userDataHandler = new UserDataHandler();
    private static CarCRUDMethodHandler methodHandler;
    SalesTransactionCRUD salesTransactionCRUD = new SalesTransactionCRUD();

    public TransactionService() {
        // Initialize CarDataHandler and CarCRUDMethodHandler correctly
        CarDataHandler carDataHandler = new CarDataHandler();
        carDataHandler.loadCarDatabase("src/DataBase/CarDatabase.ser"); // Adjust the file path as needed for cars
        methodHandler = new CarCRUDMethodHandler(carDataHandler);
    }

    // Calculate total amount, applying membership discount
    private static double totalAmountCalculation(List<PurchasedItem> purchaseItems, Membership membership) {
        double sum = purchaseItems.stream()
                .mapToDouble(PurchasedItem::getItemPrice)
                .sum();

        // Apply membership discount
        double discount = membership.getDiscount();
        return sum * (1 - discount);
    }

    @Override
    public void addTransaction() {
        // Add a new transaction
        String transactionID = InputValidation.validateTransactionID("Transaction ID (format: t-XXXX): ");
        Date transactionDate = InputValidation.validateDate("Transaction Date (DD/MM/YYYY): ");
        String clientID = InputValidation.validateExistingUserID("Client ID (format: CL-XXXX): ");
        String salespersonID = InputValidation.validateExistingUserID("Salesperson ID (format: SP-XXXX): ");

        // Collect PurchasedItem details
        List<PurchasedItem> purchaseItems = new ArrayList<>();
        boolean addMoreItems = true;

        while (addMoreItems) {
            System.out.print("Enter car ID (or leave blank if none): ");
            String carID = scanner.nextLine();
            Car car = null;
            if (!carID.isEmpty()) {
                car = methodHandler.findCarByID(carID);
                if (car == null) {
                    System.out.println("Car with ID " + carID + " not found.");
                }
            }

            System.out.print("Enter part ID (or leave blank if none): ");
            String partID = scanner.nextLine();
            AutoPart part = null;
            if (!partID.isEmpty()) {
                part = AutoPartService.findAutoPartByID(partID);  // Ensure this method exists
                if (part == null) {
                    System.out.println("Part with ID " + partID + " not found.");
                }
            }

            // Create a PurchasedItem object and add it to the list
            PurchasedItem purchasedItem = new PurchasedItem(car, part);
            purchaseItems.add(purchasedItem);

            System.out.print("Do you want to add another item? (yes/no): ");
            addMoreItems = scanner.nextLine().equalsIgnoreCase("yes");
        }

        String notes = InputValidation.validateString("Notes: ");

        // Calculate total amount based on client's membership
        Client client = userDataHandler.findClientByID(clientID);
        Membership membership = client.getMembership();
        double totalAmount = totalAmountCalculation(purchaseItems, membership);

        SalesTransaction newTransaction = new SalesTransaction(transactionID, transactionDate, clientID, salespersonID, purchaseItems, membership.getDiscount(), totalAmount, notes);
        salesTransactionCRUD.addTransaction(newTransaction);

        System.out.println("New transaction added successfully.");

        // Log the activity
        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Created new sales transaction: " + transactionID
        );
    }

    // Display all transactions sorted by TransactionID
    @Override
    public void displayAllTransactions() {
        List<SalesTransaction> transactionsByID = salesTransactionCRUD.getTransactionsOrderedByID(SalesTransactionCRUD.OrderType.transactionID, true);

        // Adjusted Table headers with more spacing
        System.out.printf("%-20s %-30s %-20s %-20s %-18s %-10s %-12s %-15s %-10s%n",
                "Transaction ID", "Transaction Date", "Client ID", "Salesperson ID", "Items Purchased",
                "Discount", "Total", "Notes", "Deleted");

        System.out.println("----------------------------------------------------------------------------------------------------------------------------");

        // Loop through the transactions and print them in a table format
        for (SalesTransaction transaction : transactionsByID) {
            if (!transaction.isDeleted()) {
                String transactionID = transaction.getTransactionID();
                Date transactionDate = transaction.getTransactionDate();
                String clientID = transaction.getClientID();
                String salespersonID = transaction.getSalespersonID();
                int itemsPurchased = transaction.getPurchaseItems().size();
                double discount = transaction.getDiscount();
                double totalAmount = transaction.getTotalAmount();
                String notes = transaction.getNotes();
                boolean deleted = transaction.isDeleted();

                // Display transaction details with adjusted formatting for wider gaps
                System.out.printf("%-20s %-30s %-20s %-20s %-18d %-10.2f %-12.2f %-15s %-10s%n",
                        transactionID, transactionDate, clientID, salespersonID,
                        itemsPurchased, discount, totalAmount, notes, deleted ? "Yes" : "No");
            }
        }

        // Log the activity
        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Retrieved transactions ordered by " + transactionsByID
        );
    }

    @Override
    public void deleteTransaction() {
        String deleteTransactionID = InputValidation.validateExistingTransactionID("Transaction ID (format: t-XXXX): ");
        salesTransactionCRUD.deleteTransaction(deleteTransactionID);
        System.out.println("Transaction deleted successfully.");

        // Log the activity
        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Deleted transaction with ID: " + deleteTransactionID
        );
    }

    @Override
    public void displayTransactionsSortByPrice() {
        List<SalesTransaction> transactionsByAmount = salesTransactionCRUD.getTransactionsOrderedByID(SalesTransactionCRUD.OrderType.totalAmount, true);
        for (SalesTransaction transaction : transactionsByAmount) {
            displayTransactionDetails(transaction);
            System.out.println();
        }
    }

    @Override
    public void displayTransactionsByClientID() {
        String displayClientID = InputValidation.validateExistingUserID("Client ID (format: CL-XXXX): ");
        salesTransactionCRUD.getTransactionsByClientID(displayClientID)
                .forEach(transaction -> {
                    displayTransactionDetails(transaction);
                    System.out.println();
                });

        // Log the activity
        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Retrieved transactions for client ID: " + displayClientID
        );
    }

    @Override
    public void displayTransactionsByID() {
        String displayTransactionID = InputValidation.validateExistingTransactionID("Transaction ID (format: t-XXXX): ");
        salesTransactionCRUD.displayTransactionByID(displayTransactionID);

        // Log the activity
        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Displayed transaction details for ID: " + displayTransactionID
        );
    }

    // Method to display the details of a SalesTransaction
    public void displayTransactionDetails(SalesTransaction transaction) {
        System.out.println("Transaction ID: " + transaction.getTransactionID());
        System.out.println("Transaction Date: " + transaction.getTransactionDate());
        System.out.println("Client ID: " + transaction.getClientID());
        System.out.println("Salesperson ID: " + transaction.getSalespersonID());
        System.out.println("Purchased Items: " + transaction.getPurchaseItems().size() + " items");
        System.out.println("Discount: " + transaction.getDiscount());
        System.out.println("Total Amount: " + transaction.getTotalAmount());
        System.out.println("Notes: " + transaction.getNotes());
        System.out.println("Deleted Status: " + transaction.isDeleted());
    }
}
