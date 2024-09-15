package filehandling;

import sales.SalesTransaction;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SalesTransactionDataHandler {
    public List<SalesTransaction> transactions = new ArrayList<>();
    private String transactionDatabaseFile = "src/database/transaction.txt";

    // Load the transactions from the .ser file using deserialization
    public void loadTransactionDatabase() {
        File file = new File(transactionDatabaseFile);

        // Check if the file exists before trying to read it
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                transactions = (List<SalesTransaction>) ois.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("Database file not found: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error reading from the database file: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println("Class not found during deserialization: " + e.getMessage());
            }
        } else {
            System.out.println("Database file does not exist. Starting with an empty transaction list.");
            transactions = new ArrayList<>();
        }
    }

    // Overwrite the transactions to the file using serialization
    public void overwriteDatabase(List<SalesTransaction> transactions) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(transactionDatabaseFile))) {
            oos.writeObject(transactions);
            System.out.println("Transaction database saved successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error writing to the database file: " + e.getMessage());
        }
    }
}
