package sales;

import crudHandlers.SalesTransactionCRUD;

import java.util.List;
import java.util.Scanner;



public class SalesTransactionCRUDTest {

    public static void main(String[] args) {
        SalesTransactionCRUD salesTransactionCRUD = new SalesTransactionCRUD("");
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("SalesTransactionCRUD Operations:");
            System.out.println("1. Display all transactions sorted by TransactionID");
            System.out.println("2. Add a new transaction");
            System.out.println("3. Update an existing transaction");
            System.out.println("4. Delete a transaction (soft delete)");
            System.out.println("5. Display all transactions sorted by Total Amount");
            System.out.println("6. Display transactions for a specific client ID");
            System.out.println("7. Display transaction by ID");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    // Display all transactions sorted by TransactionID
                    List<SalesTransaction> transactionsByID = salesTransactionCRUD.getTransactionsOrderedByID(SalesTransactionCRUD.OrderType.transactionID, true);
                    for (SalesTransaction transaction : transactionsByID) {
                        if (!transaction.isDeleted()) {
                            transaction.displayTransactionDetails();
                            System.out.println();  // Add blank line between transactions
                        }
                    }
                    break;

                case 2:
                    // Add a new transaction
                    System.out.println("Enter details of the new transaction:");
                    System.out.print("Transaction ID: ");
                    String transactionID = scanner.nextLine();
                    System.out.print("Transaction Date (yyyy-MM-dd): ");
                    String transactionDate = scanner.nextLine();
                    System.out.print("Client ID: ");
                    String clientID = scanner.nextLine();
                    System.out.print("Salesperson ID: ");
                    String salespersonID = scanner.nextLine();
                    System.out.print("Total Amount: ");
                    double totalAmount = scanner.nextDouble();
                    scanner.nextLine();  // Consume the newline character
                    System.out.print("Notes: ");
                    String notes = scanner.nextLine();

                    SalesTransaction newTransaction = new SalesTransaction(transactionID, transactionDate, clientID, , totalAmount, notes);
                    salesTransactionCRUD.addTransaction(newTransaction);
                    System.out.println("New transaction added successfully.");
                    break;


                case 3:
                    // Soft delete a transaction
                    System.out.print("Enter the Transaction ID to delete: ");
                    String deleteTransactionID = scanner.nextLine();
                    salesTransactionCRUD.deleteTransaction(deleteTransactionID);  // Call the soft delete method
                    break;

                case 4:
                    // Display all transactions sorted by Total Amount
                    List<SalesTransaction> transactionsByAmount = salesTransactionCRUD.getTransactionsOrderedByID(SalesTransactionCRUD.OrderType.TotalAmount, true);
                    for (SalesTransaction transaction : transactionsByAmount) {
                        transaction.displayTransactionDetails();
                        System.out.println();  // Add blank line between transactions
                    }
                    break;

//                case 5:
//                    // Display transactions for a specific client ID
//                    System.out.print("Enter the Client ID to display transactions: ");
//                    String displayClientID = scanner.nextLine();
//                    salesTransactionCRUD.getTransactionsByClientID(displayClientID)
//                            .forEach(transaction -> {
//                                transaction.displayInfo();
//                                System.out.println();  // Add blank line between transactions
//                            });
//                    break;

                case 5:
                    // Display a transaction by ID
                    System.out.print("Enter the Transaction ID to display: ");
                    String displayTransactionID = scanner.nextLine();
                    salesTransactionCRUD.displayTransactionByID(displayTransactionID);
                    break;

                case 6:
                    // Exit
                    exit = true;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println("---------------------------------------------------");
        }

        scanner.close();
    }
}