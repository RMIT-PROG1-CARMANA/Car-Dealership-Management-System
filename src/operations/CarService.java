package operations;

import vehicle.Car;
import crudHandlers.CarCRUD;

import java.util.List;
import java.util.Scanner;

public class CarService {
    static CarCRUD carCRUD = new CarCRUD("");
    static Scanner scanner = new Scanner(System.in);

    public static void displayCarByID(){
        List<Car> carsByID = carCRUD.getCarsOrderedByID(CarCRUD.OrderType.CarID, true);
        for (Car car : carsByID) {
            if (!car.isDeleted()) {
                car.displayInfo();
                System.out.println();  // Add blank line between cars
            }
        }
    }
    public static void createCar() {
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
    }

    public static void updateCar(){
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
    }
    public static void deleteCar(){
        System.out.print("Enter the Car ID to delete: ");
        String deleteCarID = scanner.nextLine();
        carCRUD.softDeleteCarByID(deleteCarID);  // Call the soft delete method
    }
    public static void displayCarByPrice(){
        List<Car> carsByPrice = carCRUD.getCarsOrderedByID(CarCRUD.OrderType.Price, true);
        for (Car car : carsByPrice) {
            car.displayInfo();
            System.out.println();  // Add blank line between cars
        }
    }
    public static void displayAllCar(){
        System.out.println("Available cars:");
        carCRUD.getCarsOrderedByID(CarCRUD.OrderType.CarID, true)
                .stream()
                .filter(car -> !car.isDeleted() && car.getStatus())
                .forEach(car -> {
                    car.displayInfo();
                    System.out.println();  // Add blank line between cars
                });
    }
}
