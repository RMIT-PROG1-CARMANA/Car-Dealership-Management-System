package operations;

import FileHandling.SalesTransactionDataHandler;
import sales.SalesTransaction;
import utils.InputValidation;
import logsystem.*;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static user.Authenticator.loggedUser;

public class TransactionService {
    private static final SalesTransactionDataHandler transactionDAO = new SalesTransactionDataHandler();

    public enum OrderType {
        transactionID,
        transactionDate,
        clientID,
        salespersonID,
        discount,
        totalAmount,

        notes
    }
    //Constructor
    public void TransactionService() {
        transactionDAO.loadTransactionDatabase();
    }



    public void addTransaction() {
        // Validate input for a new transaction
        String transactionID = InputValidation.validateTransactionID("Transaction ID (format: t-XXXX): ");
        Date transactionDate = InputValidation.validateDate("Transaction Date (DD/MM/YYYY): ");
        String clientID = InputValidation.validateUserID("Client ID (format: CL-XXXX): ");
        String salespersonID = InputValidation.validateUserID("Salesperson ID (format: SP-XXXX): ");
        double totalAmount = InputValidation.validateDouble("Total Amount: ");
        double discount = InputValidation.validateDouble("Discount (as a percentage): ");
        String notes = InputValidation.validateString("Notes: ");

        // Create a new SalesTransaction object
        SalesTransaction newTransaction = new SalesTransaction(
                transactionID,
                transactionDate,
                clientID,
                salespersonID,
                new ArrayList<>(),   // Assuming no purchased items at creation, modify this if needed
                discount,
                totalAmount,
                notes
        );

        // Add the transaction to the transactionDAO and sort by transactionID
        transactionDAO.transactions.add(newTransaction);
        transactionDAO.transactions.sort(Comparator.comparing(SalesTransaction::getTransactionID));
        transactionDAO.overwriteDatabase(transactionDAO.transactions);

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



    //Read
    public List<SalesTransaction> getTransactionsOrderedByID(OrderType type, boolean ascending) {
        ArrayList<SalesTransaction> sortedTransactions = new ArrayList<>(transactionDAO.transactions);
        Comparator<SalesTransaction> comparator;

        switch (type) {
            case transactionID -> comparator = Comparator.comparing(SalesTransaction::getTransactionID);
            case transactionDate -> comparator = Comparator.comparing(SalesTransaction::getTransactionDate);
            case clientID -> comparator = Comparator.comparing(SalesTransaction::getClientID);
            case salespersonID -> comparator = Comparator.comparing(SalesTransaction::getSalespersonID);
            case discount -> comparator = Comparator.comparingDouble(SalesTransaction::getDiscount);
            case totalAmount -> comparator = Comparator.comparingDouble(SalesTransaction::getTotalAmount);
            case notes -> comparator = Comparator.comparing(SalesTransaction::getNotes);
            default -> throw new IllegalArgumentException("Unexpected value: " + type);
        }

        if (!ascending) {
            comparator = comparator.reversed();
        }

        sortedTransactions.sort(comparator);

        // Log the activity
        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Retrieved transactions ordered by " + type + (ascending ? " in ascending order." : " in descending order.")
        );

        return sortedTransactions;
    }

    public void deleteTransaction(String transactionID) {
        boolean transactionFound = false;
        for (SalesTransaction transaction : transactionDAO.transactions) {
            if (transaction.getTransactionID().equals(transactionID)) {
                transaction.setDeleted(true);
                transactionFound = true;
                break;
            }
        }
        if (transactionFound) {
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
        } else {
            System.out.println("Unable to find transaction with that ID.");
        }
    }

    public List<SalesTransaction> getTransactionsByClientID(String clientID) {
        List<SalesTransaction> clientTransactions = new ArrayList<>();

        for (SalesTransaction transaction : transactionDAO.transactions) {
            if (transaction.getClientID().equals(clientID) && !transaction.isDeleted()) {
                clientTransactions.add(transaction);
            }
        }

        // Log the activity
        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Retrieved transactions for client ID: " + clientID
        );

        return clientTransactions;
    }

    public void displayTransactionByID(String id) {
        boolean transactionFound = false;

        for (SalesTransaction transaction : transactionDAO.transactions) {
            if (transaction.getTransactionID().equals(id)) {
                transaction.displayTransactionDetails();
                transactionFound = true;
                break;
            }
        }
        if (transactionFound) {
            // Log the activity
            String logID = ActivityLog.generateLogID();
            ActivityLogService.logActivity(
                    logID,
                    new Date(),
                    loggedUser.getUsername(),
                    loggedUser.getUserID(),
                    "Displayed transaction details for ID: " + id
            );
        } else {
            System.out.println("Unable to find transaction with ID " + id);
        }
    }


}
