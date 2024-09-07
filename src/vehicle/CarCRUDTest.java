package vehicle;

import java.util.List;
import java.util.Scanner;

public class CarCRUDTest {
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
            System.out.println("7. Display car by ID");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    // Display all cars sorted by CarID
                    List<Car> carsByID = carCRUD.getCarsOrderedByID(CarCRUD.OrderType.CarID, true);
                    for (Car car : carsByID) {
                        if (!car.isDeleted()) {
                            car.displayInfo();
                            System.out.println();  // Add blank line between cars
                        }
                    }
                    break;

                case 2:
                    // Add a new car
                    System.out.println("Enter details of the new car:");
                    System.out.print("Car ID: ");
                    String carID = scanner.nextLine();
                    System.out.print("Make: ");
                    String make = scanner.nextLine();
                    System.out.print("Model: ");
                    String model = scanner.nextLine();
                    System.out.print("Year: ");
                    int year = scanner.nextInt();
                    System.out.print("Mileage: ");
                    int mileage = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character
                    System.out.print("Color: ");
                    String color = scanner.nextLine();
                    System.out.print("Status (true for available, false for sold): ");
                    boolean status = scanner.nextBoolean();
                    System.out.print("Price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();  // Consume the newline character
                    System.out.print("Notes: ");
                    String notes = scanner.nextLine();

                    Car newCar = new Car(carID, make, model, year, mileage, color, status, price, notes);
                    carCRUD.addCar(newCar);
                    System.out.println("New car added successfully.");
                    break;

                case 3:
                    // Update an existing car
                    System.out.print("Enter the Car ID to update: ");
                    String updateCarID = scanner.nextLine();
                    System.out.println("Enter new details for the car:");
                    System.out.print("Make: ");
                    String updateMake = scanner.nextLine();
                    System.out.print("Model: ");
                    String updateModel = scanner.nextLine();
                    System.out.print("Year: ");
                    int updateYear = scanner.nextInt();
                    System.out.print("Mileage: ");
                    int updateMileage = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character
                    System.out.print("Color: ");
                    String updateColor = scanner.nextLine();
                    System.out.print("Status (true for available, false for sold): ");
                    boolean updateStatus = scanner.nextBoolean();
                    System.out.print("Price: ");
                    double updatePrice = scanner.nextDouble();
                    scanner.nextLine();  // Consume the newline character
                    System.out.print("Notes: ");
                    String updateNotes = scanner.nextLine();

                    Car updateCar = new Car(updateCarID, updateMake, updateModel, updateYear, updateMileage, updateColor, updateStatus, updatePrice, updateNotes);
                    carCRUD.updateCar(updateCarID, updateCar);
                    System.out.println("Car updated successfully.");
                    break;

                case 4:
                    // Soft delete a car
                    System.out.print("Enter the Car ID to delete: ");
                    String deleteCarID = scanner.nextLine();
                    carCRUD.softDeleteCarByID(deleteCarID);  // Call the soft delete method
                    break;

                case 5:
                    // Display all cars sorted by Price
                    List<Car> carsByPrice = carCRUD.getCarsOrderedByID(CarCRUD.OrderType.Price, true);
                    for (Car car : carsByPrice) {
                        car.displayInfo();
                        System.out.println();  // Add blank line between cars
                    }
                    break;

                case 6:
                    // Display available cars
                    System.out.println("Available cars:");
                    carCRUD.getCarsOrderedByID(CarCRUD.OrderType.CarID, true)
                            .stream()
                            .filter(car -> !car.isDeleted() && car.getStatus())
                            .forEach(car -> {
                                car.displayInfo();
                                System.out.println();  // Add blank line between cars
                            });
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
