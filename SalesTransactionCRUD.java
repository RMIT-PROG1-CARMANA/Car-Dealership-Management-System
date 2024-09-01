package SalesTransaction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SalesTransactionCRUD {
    private final SalesTransactionDatabaseLoader stdl = new SalesTransactionDatabaseLoader();

    public enum OrderType {
        transactionID,
        transactionDate,
        clientID,
        salespersonID,
        discount,
        totalAmount,
        notes
    }

    public SalesTransactionCRUD(String transactionDB) {
        stdl.loadTransactionDatabase(transactionDB);
    }

    // Creation
    public void addTransaction(SalesTransaction transaction) {
        stdl.transactions.add(transaction);
        stdl.transactions.sort(Comparator.comparing(SalesTransaction::getTransactionID));
        stdl.overwriteDatabase(stdl.transactions);
    }

    // Read
    public List<SalesTransaction> getTransactionsOrderedBy(OrderType type, boolean ascending) {
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

    public SalesTransaction readTransactionByID(String id) {
        for (SalesTransaction transaction : stdl.transactions) {
            if (transaction.getTransactionID().equals(id)) {
                return transaction;
            }
        }
        return null; // Return null if not found
    }

    // Sales Transaction cannot be updated

    // Delete
    public void deleteTransaction(String transactionID) {
        for (SalesTransaction t : stdl.transactions) {
            if (t.getTransactionID().equals(transactionID)) {
                stdl.transactions.remove(t);
                stdl.overwriteDatabase(stdl.transactions);
                return;
            }
        }
        System.out.println("Unable to find transaction with ID " + transactionID);
    }
}
