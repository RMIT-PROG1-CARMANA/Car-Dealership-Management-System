import FileHandling.ServiceFileOperations;
import user.User;
import car.Car;
import service.*;
import part.AutoPart;
import FileHandling.*;
import transaction.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create 5 User objects
//        User user1 = new User("1", "John Doe", new Date(), "123 Main St", "555-1234", "john@example.com", User.UserType.CLIENT, true, "password1", "johndoe");
//        User user2 = new User("2", "Jane Smith", new Date(), "456 Elm St", "555-5678", "jane@example.com", User.UserType.CLIENT, true, "password2", "janesmith");
//        User user3 = new User("3", "Alice Johnson", new Date(), "789 Oak St", "555-9101", "alice@example.com", User.UserType.CLIENT, true, "password3", "alicejohnson");
//        User user4 = new User("4", "Bob Brown", new Date(), "321 Pine St", "555-1213", "bob@example.com", User.UserType.CLIENT, true, "password4", "bobbrown");
//        User user5 = new User("5", "Charlie Davis", new Date(), "654 Cedar St", "555-1415", "charlie@example.com", User.UserType.CLIENT, true, "password5", "charliedavis");
//
//        // Save the users to the file
//        UserFileOperations.saveUser(user1);
//        UserFileOperations.saveUser(user2);
//        UserFileOperations.saveUser(user3);
//        UserFileOperations.saveUser(user4);
//        UserFileOperations.saveUser(user5);
//
//        // Delete the third user (Alice Johnson)
//        UserFileOperations.removeUserById("3");
//
//        // Fetch the first user (John Doe)
//        User fetchedUser1 = UserFileOperations.fetchUser("1");
//        if (fetchedUser1 != null) {
//            fetchedUser1.viewInformation();
//        } else {
//            System.out.println("User 1 not found.");
//        }
//
//        // Try to fetch the third user (Alice Johnson) - should return null since it was deleted
//        User fetchedUser5 = UserFileOperations.fetchUser("5");
//        if (fetchedUser5 != null) {
//            fetchedUser5.viewInformation();
//        } else {
//            System.out.println("User 3 not found.");
//        }
//
//        ArrayList<User> allUsers = UserFileOperations.getAllUsers();
//        for (User user : allUsers) {
//            System.out.println("ID: " + user.getUserID() + ", Name: " + user.getFullName() + ", Role: " + user.getUserType());
//        }

//        Car car1 = new Car("c-001", "Toyota", "Camry", 2021, 15000, "Red", true, 25000.00, "Well-maintained");
//        Car car2 = new Car("c-002", "Honda", "Civic", 2020, 20000, "Blue", true, 22000.00, "One owner");
//        Car car3 = new Car("c-003", "Ford", "Mustang", 2022, 5000, "Black", true, 30000.00, "Like new");
//
//        // Save cars to the file
//        CarFileOperations.saveCar(car1);
//        CarFileOperations.saveCar(car2);
//        CarFileOperations.saveCar(car3);
//
//        // Fetch and display a car
//        Car fetchedCar = CarFileOperations.fetchCar("c-002");
//        if (fetchedCar != null) {
//            System.out.println("Fetched Car:");
//            fetchedCar.displayInfo();
//        } else {
//            System.out.println("Car not found.");
//        }
//
//        // Get all cars and display them
//        ArrayList<Car> allCars = CarFileOperations.getAllCars();
//        System.out.println("\nAll Cars in the Database:");
//        for (Car car : allCars) {
//            System.out.println(car);
//        }
//
//        // Remove a car
//        boolean removed = CarFileOperations.removeCarById("c-001");
//        System.out.println("\nCar removal status: " + (removed ? "Success" : "Failed"));
//
//        // Display all cars again to verify removal
//        allCars = CarFileOperations.getAllCars();
//        System.out.println("\nAll Cars in the Database after removal:");
//        for (Car car : allCars) {
//            System.out.println(car);
//        }

        AutoPart part1 = new AutoPart("p001", "Brake Pad", "ABC Corp", "BP123", "new", "2 years", 50.0, "Front brake pads");
        AutoPart part2 = new AutoPart("p002", "Oil Filter", "XYZ Inc", "OF456", "new", "1 year", 15.0, "Engine oil filter");

        // Add AutoPart instances to the AutoPart list
        AutoPart.addPart(part1);
        AutoPart.addPart(part2);

        // Retrieve the AutoPart list
        List<AutoPart> partsList = new ArrayList<>();
        partsList.add(part1);
        partsList.add(part2);

        // Create a Service instance with partsList
        Service service1 = new Service("s001", "2024-08-29", "client001", "mechanic001", "Oil Change", partsList, 100.0, "Standard oil change service.");

        // Add Service instance to the Service list
        Service.addService(service1);

        ServiceFileOperations.saveService(service1);

        // Display all AutoParts
        System.out.println("List of AutoParts:");
        AutoPart.listAllParts();

        // Display all Services
        System.out.println("List of Services:");
        Service.listAllServices();

        // Update an AutoPart
        AutoPart.updatePart("p001", "Brake Pad", "ABC Corp", "BP123", "new", "2 years", 55.0, "Updated brake pads");

        // Update a Service
        service1.updateService("2024-08-30", "client002", "mechanic002", "Tire Rotation", 120.0, "Updated service details.");

        // Display updated AutoParts
        System.out.println("Updated List of AutoParts:");
        AutoPart.listAllParts();

        // Display updated Services
        System.out.println("Updated List of Services:");
        Service.listAllServices();

        // Delete an AutoPart
        AutoPart.deletePart("p002");

        // Delete a Service
        Service.deleteService("s001");

        // Display AutoParts and Services after deletion
        System.out.println("List of AutoParts after deletion:");
        AutoPart.listAllParts();

        System.out.println("List of Services after deletion:");
        Service.listAllServices();

        // Create some hardcoded transactions
//        List<Object> purchasedItems1 = new ArrayList<>();
//        purchasedItems1.add("Item1");
//        purchasedItems1.add("Item2");
//
//        SalesTransaction transaction1 = new SalesTransaction(
//                "t-001", "2024-08-29", purchasedItems1, // transactionID, transactionDate, purchasedItems
//                "cl-001", "s-001", // clientID, salespersonID
//                10.0, 150.0, // discount, totalAmount
//                "First transaction" // notes
//        );
//
//        List<Object> purchasedItems2 = new ArrayList<>();
//        purchasedItems2.add("Item3");
//
//        SalesTransaction transaction2 = new SalesTransaction(
//                "t-002", "2024-08-30", purchasedItems2, // transactionID, transactionDate, purchasedItems
//                "cl-002", "s-002", // clientID, salespersonID
//                5.0, 75.0, // discount, totalAmount
//                "Second transaction" // notes
//        );
//
//        // Save transactions to file
//        TransactionFileOperations.saveTransaction(transaction1);
//        TransactionFileOperations.saveTransaction(transaction2);
//
//        // Fetch and print transactions by ID
//        SalesTransaction fetchedTransaction1 = TransactionFileOperations.fetchTransaction("t-001");
//        SalesTransaction fetchedTransaction2 = TransactionFileOperations.fetchTransaction("t-002");
//
//        System.out.println("Fetched Transaction 1:");
//        if (fetchedTransaction1 != null) {
//            System.out.println(fetchedTransaction1);
//        } else {
//            System.out.println("Transaction 1 not found.");
//        }
//
//        System.out.println("Fetched Transaction 2:");
//        if (fetchedTransaction2 != null) {
//            System.out.println(fetchedTransaction2);
//        } else {
//            System.out.println("Transaction 2 not found.");
//        }
//
//        // Remove a transaction by ID
//        boolean removed = TransactionFileOperations.removeTransactionById("t-001");
//        if (removed) {
//            System.out.println("Transaction t-001 removed successfully.");
//        } else {
//            System.out.println("Transaction t-001 not found.");
//        }
//
//        // Fetch and print all transactions
//        System.out.println("All Transactions:");
//        List<SalesTransaction> allTransactions = TransactionFileOperations.getAllTransactions();
//        for (SalesTransaction transaction : allTransactions) {
//            System.out.println(transaction);
//        }

    }
}