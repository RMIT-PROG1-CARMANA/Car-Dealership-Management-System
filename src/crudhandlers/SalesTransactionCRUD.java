package crudhandlers;

import filehandling.SalesTransactionDataHandler;
import sales.SalesTransaction;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SalesTransactionCRUD {
    private final SalesTransactionDataHandler stdl = new SalesTransactionDataHandler();

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
    public SalesTransactionCRUD() {
        stdl.loadTransactionDatabase();
    }
    //Creation
    public void addTransaction(SalesTransaction transaction) {
        stdl.transactions.add(transaction);
        stdl.transactions.sort(Comparator.comparing(SalesTransaction::getTransactionID));
        stdl.overwriteDatabase(stdl.transactions);
    }
    //Read
    public List<SalesTransaction> getTransactionsOrderedByID(OrderType type, boolean ascending) {
        ArrayList<SalesTransaction> sortedTransactions = new ArrayList<>(stdl.transactions);
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
        return new ArrayList<>(sortedTransactions);
    }

    //Sales Transaction cannot be updated

    //Soft Delete
    public void deleteTransaction(String transactionID) {
        boolean transactionFound = false;
        for (SalesTransaction transaction : stdl.transactions) {
            if (transaction.getTransactionID().equals(transactionID)) {
                transaction.setDeleted(true);
                transactionFound = true;
                break;
            }
        }
        if (transactionFound) {
            System.out.println("Transaction deleted successfully.");
        }
        else {
            System.out.println("Unable to find transaction with that ID.");
        }
    }

    public List<SalesTransaction> getTransactionsByClientID(String clientID) {
        List<SalesTransaction> clientTransactions = new ArrayList<>();

        // Loop through all transactions in the SalesTransactionDataHandler
        for (SalesTransaction transaction : stdl.transactions) {
            // Check if the transaction's clientID matches the provided clientID
            if (transaction.getClientID().equals(clientID) && !transaction.isDeleted()) {
                clientTransactions.add(transaction);
            }
        }

        return clientTransactions;
    }
    //Display transaction by transaction ID
    public void displayTransactionByID(String id) {
        boolean transactionFound = false;

        for (SalesTransaction transaction : stdl.transactions) {
            if (transaction.getTransactionID().equals(id)) {
                transaction.displayTransactionDetails();
                transactionFound = true;
                break;
            }
        }
        if (!transactionFound) {
            System.out.println("Unable to find transaction with ID " + id);
        }
    }
    public double totalSpendingByClient(String clientID) {
        double totalSpending = 0;

        // Get all transactions for the provided client ID
        List<SalesTransaction> clientTransactions = getTransactionsByClientID(clientID);

        // Sum up the total amount for each transaction
        for (SalesTransaction transaction : clientTransactions) {
            totalSpending += transaction.getTotalAmount();
        }

        // Return the total spending for the client
        return totalSpending;
    }


}