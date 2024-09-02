package FileHandling;



import sales.SalesTransaction;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionFileOperations {
    private static final String TRANSACTION_DATABASE_PATH = "src/Database/TransactionDatabase.txt";
    private static final String TEMP_DATABASE_PATH = "src/Database/tempTransactionDatabase.txt";

    // Save a SalesTransaction object to the file
    public static void saveTransaction(SalesTransaction transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTION_DATABASE_PATH, true))) {
            writer.write(transactionToString(transaction));
            writer.newLine();
            System.out.println("Transaction saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving transaction: " + e.getMessage());
        }
    }

    // Fetch a SalesTransaction object by transactionID from the file
    public static SalesTransaction fetchTransaction(String transactionID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTION_DATABASE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                SalesTransaction transaction = stringToTransaction(line);
                if (transaction != null && transaction.getTransactionID().equals(transactionID)) {
                    return transaction;
                }
            }
        } catch (IOException e) {
            System.err.println("Error fetching transaction: " + e.getMessage());
        }
        return null; // If the transaction is not found
    }

    // Remove a SalesTransaction object by transactionID from the file
    public static boolean removeTransactionById(String transactionID) {
        File inputFile = new File(TRANSACTION_DATABASE_PATH);
        File tempFile = new File(TEMP_DATABASE_PATH);

        boolean removed = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                SalesTransaction transaction = stringToTransaction(line);
                if (transaction != null && transaction.getTransactionID().equals(transactionID)) {
                    removed = true;
                    continue; // Skip writing this transaction to the temp file
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error removing transaction: " + e.getMessage());
            return false;
        }

        // Replace original file with the temp file
        if (!inputFile.delete()) {
            System.err.println("Error deleting the original file.");
            return false;
        }
        if (!tempFile.renameTo(inputFile)) {
            System.err.println("Error renaming the temp file.");
            return false;
        }

        if (removed) {
            System.out.println("Transaction removed successfully.");
        } else {
            System.out.println("Transaction not found.");
        }

        return removed;
    }

    // Get all SalesTransactions from the file and return them as an ArrayList
    public static ArrayList<SalesTransaction> getAllTransactions() {
        ArrayList<SalesTransaction> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTION_DATABASE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                SalesTransaction transaction = stringToTransaction(line);
                if (transaction != null) {
                    transactions.add(transaction);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading transactions: " + e.getMessage());
        }

        return transactions;
    }

    // Helper method to convert a SalesTransaction object to a String for saving
    private static String transactionToString(SalesTransaction transaction) {
        StringBuilder sb = new StringBuilder();
        sb.append(transaction.getTransactionID()).append(";")
                .append(transaction.getTransactionDate()).append(";")
                .append(transaction.getClientID()).append(";")
                .append(transaction.getSalespersonID()).append(";")
                .append(transaction.getDiscount()).append(";")
                .append(transaction.getTotalAmount()).append(";")
                .append(transaction.getNotes()).append(";");

        // Convert purchased items to String
        List<Object> items = transaction.getPurchasedItems();
        sb.append(items.size()).append(";");
        for (Object item : items) {
            sb.append(item.toString()).append(",");
        }

        return sb.toString();
    }

    // Helper method to convert a String from the file back to a SalesTransaction object
    private static SalesTransaction stringToTransaction(String transactionString) {
        String[] fields = transactionString.split(";");
        if (fields.length < 8) {
            return null; // Invalid transaction record
        }

        SalesTransaction transaction = new SalesTransaction(
                fields[0], // transactionID
                fields[1], // transactionDate
                String.valueOf(new ArrayList<>()),
                fields[2], // clientID
                fields[3], // salespersonID

                Double.parseDouble(fields[4]), // discount
                Double.parseDouble(fields[5]), // totalAmount
                fields[6] // notes
        );

        // Convert purchased items from String
        int itemCount = Integer.parseInt(fields[7]);
        List<Object> items = new ArrayList<>();
        for (int i = 8; i < 8 + itemCount; i++) {
            String item = fields[i];
            items.add(item); // You might need to adjust this if items are more complex
        }
        transaction.setPurchasedItems(items);

        return transaction;
    }
}