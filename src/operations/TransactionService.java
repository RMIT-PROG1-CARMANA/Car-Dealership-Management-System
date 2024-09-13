package operations;

import filehandling.CarDataHandler;
import interfaces.TransactionInterfaces;
import logsystem.ActivityLog;
import part.AutoPart;
import sales.SalesTransaction;
import user.Client;
import utils.InputValidation;
import filehandling.UserDataHandler;
import crudhandlers.CarCRUDMethodHandler;
import crudhandlers.SalesTransactionCRUD;
import sales.PurchasedItem;
import user.Membership;
import vehicle.Car;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static crudhandlers.SalesTransactionCRUD.OrderType.clientID;
import static crudhandlers.SalesTransactionCRUD.OrderType.transactionID;
import static user.Authenticator.loggedUser;


public class TransactionService implements TransactionInterfaces {

    Scanner scanner = new Scanner(System.in);
    static UserDataHandler userDataHandler = new UserDataHandler();
    private static CarCRUDMethodHandler methodHandler;

    public TransactionService() {
        // Initialize CarDataHandler and CarCRUDMethodHandler here
        CarDataHandler carDataHandler = new CarDataHandler();
//        carDataHandler.loadCarDatabase("src/database/SalesTransactionDatabase.txt"); // Adjust the file path as needed
        methodHandler = new CarCRUDMethodHandler(carDataHandler); // Proper initialization
    }


    SalesTransactionCRUD salesTransactionCRUD = new SalesTransactionCRUD();
    private static double totalAmountCalculation(List<PurchasedItem> purchaseItems, Membership membership) {
        double sum = purchaseItems.stream()
                .mapToDouble(PurchasedItem::getItemPrice)
                .sum();

        // Apply membership discount
        double discount = membership.getDiscount();
        double totalAmount = sum * (1 - discount);  // Apply discount

        return totalAmount;
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
            Integer carQuality = null;
            if (!carID.isEmpty()) {
                // Retrieve the car from the database
                car = methodHandler.findCarByID(carID);
                if (car == null) {
                    System.out.println("Car with ID " + carID + " not found.");
                } else {
                    // Ask for the quality if the car is selected
                    System.out.print("Enter quality for car (number of units): ");
                    carQuality = Integer.parseInt(scanner.nextLine());
                }
            }

            // Since AutoPart is optional, we'll ask for part details similarly
            System.out.print("Enter part ID (or leave blank if none): ");
            String partID = scanner.nextLine();
            AutoPart part = null;
            Integer partQuality = null;
            if (!partID.isEmpty()) {
                part = AutoPartService.findAutoPartByID(partID);
                if (part == null) {
                    System.out.println("Part with ID " + partID + " not found.");
                } else {
                    // Ask for the quality if the part is selected
                    System.out.print("Enter quality for part (number of units): ");
                    partQuality = Integer.parseInt(scanner.nextLine());
                }
            }

            // Create a PurchasedItem object and add it to the list
            PurchasedItem purchasedItem = new PurchasedItem(car, part, carQuality, partQuality);
            purchaseItems.add(purchasedItem);

            // Ask the user if they want to add more items
            System.out.print("Do you want to add another item? (yes/no): ");
            addMoreItems = scanner.nextLine().equalsIgnoreCase("yes");
        }
        String notes = InputValidation.validateString("Notes: ");

        // Calculate total amount based on client's membership
        Client client = userDataHandler.findClientByID(clientID);
        Membership membership = client.getMembership();  // Retrieve the Membership object
        double totalAmount = totalAmountCalculation(purchaseItems, membership);


        SalesTransaction newTransaction = new SalesTransaction(transactionID, transactionDate, clientID, salespersonID, purchaseItems, membership.getDiscount(), totalAmount, notes);
        salesTransactionCRUD.addTransaction(newTransaction);
        System.out.println("New transaction added successfully.");


        // Optionally log the activity
        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Created new sales transaction: " + transactionID
        );

        System.out.println("Transaction added successfully.");
    }

    // Display all transactions sorted by TransactionID
    @Override
    public void displayAllTransactions(){
        List<SalesTransaction> transactionsByID = salesTransactionCRUD.getTransactionsOrderedByID(SalesTransactionCRUD.OrderType.transactionID, true);
        for (SalesTransaction transaction : transactionsByID) {
            if (!transaction.isDeleted()) {
                transaction.displayTransactionDetails();
                System.out.println();  // Add blank line between transactions
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
                // + (ascending ? " in ascending order." : " in descending order.")
        );
    }
    // Soft delete a transaction
    @Override
    public void deleteTransaction() {
        String deleteTransactionID = InputValidation.validateExistingTransactionID("Transaction ID (format: t-XXXX): ");
        salesTransactionCRUD.deleteTransaction(deleteTransactionID);  // Call the soft delete method
        System.out.println("Transaction deleted successfully.");
        // Log the activity
        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Deleted transaction with ID: " + transactionID
        );
    }

    // Display all transactions sorted by Total Amount
    @Override
    public void displayTransactionsSortByPrice(){

        List<SalesTransaction> transactionsByAmount = salesTransactionCRUD.getTransactionsOrderedByID(SalesTransactionCRUD.OrderType.totalAmount, true);
        for (SalesTransaction transaction : transactionsByAmount) {
            transaction.displayTransactionDetails();
            System.out.println();  // Add blank line between transactions
        }
    }
    @Override
    public void displayTransactionsByClientID(){
        // Display transactions for a specific client ID
        String displayClientID = InputValidation.validateExistingUserID("Client ID (format: CL-XXXX): ");
        salesTransactionCRUD.getTransactionsByClientID(displayClientID)
                .forEach(transaction -> {
                    transaction.displayTransactionDetails();
                    System.out.println();  // Add blank line between transactions
                });
        // Log the activity
        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Retrieved transactions for client ID: " + clientID
        );
    }
    @Override
    public void displayTransactionsByID(){
        String displayTransactionID = InputValidation.validateExistingTransactionID("Transaction ID (format: t-XXXX): ");
        salesTransactionCRUD.displayTransactionByID(displayTransactionID);
        // Log the activity
        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Displayed transaction details for ID: " + transactionID
        );
    }

}