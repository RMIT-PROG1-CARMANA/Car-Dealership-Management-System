package operations;

import interfaces.CarInterfaces;
import logsystem.ActivityLog;
import vehicle.Car;
import crudhandlers.CarCRUD;
import utils.InputValidation;

import java.util.*;

import static user.Authenticator.loggedUser;

public class CarService implements CarInterfaces {
    static CarCRUD carCRUD = new CarCRUD("");

//    @Override
//    public void displayCarByID(){
//        List<Car> carsByID = carCRUD.getCarsOrderedByID(CarCRUD.OrderType.CarID, true);
//        for (Car car : carsByID) {
//            if (!car.isDeleted()) {
//                car.displayInfo();
//                System.out.println();  // Add blank line between cars
//            }
//        }
//    }

    @Override
    public void createCar() {
        String carID = InputValidation.validateCarID("Car ID (format: C-XXXX where XXXX is a number): ");
        String make = InputValidation.validateString("Make: ");
        String model = InputValidation.validateString("Model: ");
        int year = InputValidation.validateInt("Year: ");
        int mileage = InputValidation.validateInt("Mileage: ");
        String color = InputValidation.validateString("Color: ");
        boolean status = InputValidation.validateBoolean("Status (true for available, false for sold): ");
        double price = InputValidation.validateDouble("Price: ");
        String notes = InputValidation.validateString("Notes: ");

        Car newCar = new Car(carID, make, model, year, mileage, color, status, price, notes);
        carCRUD.addCar(newCar);

        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Create a car : " + carID
        );

        System.out.println("New car added successfully.");
    }

    @Override
    public void updateCar(){
        String updateCarID = InputValidation.validateExistingCarID("Enter the Car ID to update: ");
        String updateMake = InputValidation.validateString("Make: ");
        String updateModel = InputValidation.validateString("Model: ");
        int updateYear = InputValidation.validateInt("Year: ");
        int updateMileage = InputValidation.validateInt("Mileage: ");
        String updateColor = InputValidation.validateString("Color: ");
        boolean updateStatus = InputValidation.validateBoolean("Status (true for available, false for sold): ");
        double updatePrice = InputValidation.validateDouble("Price: ");
        String updateNotes = InputValidation.validateString("Notes: ");

        Car updateCar = new Car(updateCarID, updateMake, updateModel, updateYear, updateMileage, updateColor, updateStatus, updatePrice, updateNotes);
        carCRUD.updateCar(updateCarID, updateCar);

        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Update car information: " + updateCarID
        );

        System.out.println("Car updated successfully.");
    }

    @Override
    public void deleteCar(){

        String deleteCarID = InputValidation.validateExistingCarID("Enter the Car ID to delete: ");

        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Soft delete car: " + deleteCarID
        );

        carCRUD.softDeleteCarByID(deleteCarID);
    }

//    @Override
//    public void displayCarByPrice(){
//        List<Car> carsByPrice = carCRUD.getCarsOrderedByID(CarCRUD.OrderType.Price, true);
//        for (Car car : carsByPrice) {
//            car.displayInfo();
//
//            String logID = ActivityLog.generateLogID();
//            ActivityLogService.logActivity(
//                    logID,
//                    new Date(),
//                    loggedUser.getUsername(),
//                    loggedUser.getUserID(),
//                    "Display car by price"
//            );
//
//            System.out.println();  // Add blank line between cars
//        }
//    }

    @Override
    public void displayAllCar() {
        // Print table headers with increased gaps
        System.out.format("%-15s %-15s %-15s %-10s %-10s %-15s %-15s %-12s %-25s %s%n",
                "Car ID", "Make", "Model", "Year", "Mileage", "Color", "Status", "Price", "Notes", "Deleted");

        // Print a separator line
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");

        // Print each car's details in a formatted way with adjusted gaps
        carCRUD.getCarsOrderedByID(CarCRUD.OrderType.CarID, true)
                .stream()
                .filter(car -> !car.isDeleted() && car.getStatus())
                .forEach(car -> {
                    System.out.format("%-15s %-15s %-15s %-10d %-10d %-15s %-15s %-12.2f %-25s %b%n",
                            car.getCarID(),
                            car.getMake(),
                            car.getModel(),
                            car.getYear(),
                            car.getMileage(),
                            car.getColor(),
                            car.getStatus() ? "Available" : "Sold",
                            car.getPrice(),
                            car.getNotes(),
                            car.isDeleted());
                });

        // Log activity
        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Display all car "
        );
    }

}
