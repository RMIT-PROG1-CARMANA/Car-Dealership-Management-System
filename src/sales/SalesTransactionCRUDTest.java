package sales;

import FileHandling.CarDataHandler;
import FileHandling.UserDataHandler;
import crudHandlers.CarCRUDMethodHandler;
import crudHandlers.SalesTransactionCRUD;

import java.util.List;
import java.util.Scanner;

import operations.PartService;
import user.Client;
import vehicle.Car;
import part.AutoPart;
import java.util.ArrayList;

import user.Membership;

public class SalesTransactionCRUDTest {

    static UserDataHandler userDataHandler = new UserDataHandler();
    private static CarCRUDMethodHandler methodHandler;

    private static double totalAmountCalculation(List<PurchasedItem> purchaseItems, Membership membership) {
        double sum = purchaseItems.stream()
                .mapToDouble(PurchasedItem::getItemPrice)
                .sum();

        // Apply membership discount
        double discount = membership.getDiscount();
        double totalAmount = sum * (1 - discount);  // Apply discount

        return totalAmount;
    }

    public static void main(String[] args) {

        // Initialize CarDataHandler and CarCRUDMethodHandler
        CarDataHandler carDataHandler = new CarDataHandler();
        carDataHandler.loadCarDatabase("src/DataBase/SalesTransactionDatabase.txt"); // Adjust the file path as needed
        methodHandler = new CarCRUDMethodHandler(carDataHandler);

        SalesTransactionCRUD salesTransactionCRUD = new SalesTransactionCRUD();
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

                    // Collect PurchasedItem details
                    List<PurchasedItem> purchaseItems = new ArrayList<>();
                    boolean addMoreItems = true;

                    while (addMoreItems) {
                        System.out.print("Enter car ID (or leave blank if none): ");
                        String carID = scanner.nextLine();

                        Car car = null;
                        if (!carID.isEmpty()) {
                            // Retrieve the car from the database
                            car = methodHandler.findCarByID(carID);
                            if (car == null) {
                                System.out.println("Car with ID " + carID + " not found.");
                            }
                        }

                        // Since AutoPart is optional, we'll ask for part details similarly
                        System.out.print("Enter part ID (or leave blank if none): ");
                        String partID = scanner.nextLine();

                        AutoPart part = null;
                        if (!partID.isEmpty()) {
                            part = PartService.findAutoPartByID(partID);
                            if (part == null) {
                                System.out.println("Part with ID " + partID + " not found.");
                            }
                        }

                        // Create a PurchasedItem object and add it to the list
                        PurchasedItem purchasedItem = new PurchasedItem(car, part);
                        purchaseItems.add(purchasedItem);

                        // Ask the user if they want to add more items
                        System.out.print("Do you want to add another item? (yes/no): ");
                        addMoreItems = scanner.nextLine().equalsIgnoreCase("yes");
                    }

                    // Calculate total amount based on client's membership
                    Client client = userDataHandler.findClientByID(clientID);
                    Membership membership = client.getMembership();  // Retrieve the Membership object
                    double totalAmount = totalAmountCalculation(purchaseItems, membership);

                    System.out.print("Notes: ");
                    String notes = scanner.nextLine();

                    SalesTransaction newTransaction = new SalesTransaction(transactionID, transactionDate, clientID, salespersonID, purchaseItems, membership.getDiscount(), totalAmount, notes);
                    salesTransactionCRUD.addTransaction(newTransaction);
                    System.out.println("New transaction added successfully.");
                    break;

                case 3:
                    // Update an existing transaction (Implement the update logic here)
                    // For now, this case does nothing and requires implementation.
                    System.out.println("Updating a transaction functionality is not implemented yet.");
                    break;

                case 4:
                    // Soft delete a transaction
                    System.out.print("Enter the Transaction ID to delete: ");
                    String deleteTransactionID = scanner.nextLine();
                    salesTransactionCRUD.deleteTransaction(deleteTransactionID);  // Call the soft delete method
                    System.out.println("Transaction deleted successfully.");
                    break;

                case 5:
                    // Display all transactions sorted by Total Amount
                    List<SalesTransaction> transactionsByAmount = salesTransactionCRUD.getTransactionsOrderedByID(SalesTransactionCRUD.OrderType.totalAmount, true);
                    for (SalesTransaction transaction : transactionsByAmount) {
                        transaction.displayTransactionDetails();
                        System.out.println();  // Add blank line between transactions
                    }
                    break;

                case 6:
                    // Display transactions for a specific client ID
                    System.out.print("Enter the Client ID to display transactions: ");
                    String displayClientID = scanner.nextLine();
                    salesTransactionCRUD.getTransactionsByClientID(displayClientID)
                            .forEach(transaction -> {
                                transaction.displayTransactionDetails();
                                System.out.println();  // Add blank line between transactions
                            });
                    break;

                case 7:
                    // Display a transaction by ID
                    System.out.print("Enter the Transaction ID to display: ");
                    String displayTransactionID = scanner.nextLine();
                    salesTransactionCRUD.displayTransactionByID(displayTransactionID);
                    break;

                case 8:
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
