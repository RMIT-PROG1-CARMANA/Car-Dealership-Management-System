package vehicle;

import crudHandlers.CarCRUD;
import operations.CarService;

import java.util.Scanner;

public class CarCRUDTest {
    private static final CarService carService = new CarService();
    public static void main(String[] args) {
        CarCRUD carCRUD = new CarCRUD("");
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("CarCRUD Operations:");
            System.out.println("1. Display all cars sorted by CarID");
            System.out.println("2. Add a new car");
            System.out.println("3. Update an existing car");
            System.out.println("4. Delete a car (soft delete)");
            System.out.println("5. Display all cars sorted by Price");
            System.out.println("6. Display available cars");
            System.out.println("7. Search car by ID");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    // Display all cars sorted by CarID
                    carService.displayCarByID();
                    break;

                case 2:
                    // Add a new car
                    carService.createCar();
                    break;

                case 3:
                    // Update an existing car
                    carService.updateCar();
                    break;

                case 4:
                    // Soft delete a car
                    carService.deleteCar();
                    break;

                case 5:
                    // Display all cars sorted by Price
                    carService.displayCarByPrice();
                    break;

                case 6:
                    // Display available cars
                    carService.displayAllCar();
                    break;

                case 7:
                    // Display a car by ID
                    System.out.print("Enter the Car ID to display: ");
                    String displayCarID = scanner.nextLine();
                    carCRUD.displayCarByID(displayCarID);
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
