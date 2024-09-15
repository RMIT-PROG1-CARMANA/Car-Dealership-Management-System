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


    @Override
    public void addCar() {
        String carID = InputValidation.validateCarID("Car ID (format: c-XXXX where XXXX is a number): ");
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
    public void updateCar() {
        // Prompt user for Car ID to update
        String updateCarID = InputValidation.validateExistingCarID("Enter the Car ID to update: ");

        // Retrieve existing car data
        Car existingCar = carCRUD.findCarByID(updateCarID); // This should be the correct call

        if (existingCar == null) {
            System.out.println("Car with ID " + updateCarID + " not found.");
            return;
        }

        // Prompt user for updates, showing existing values
        String updateMake = InputValidation.validateString("Make (" + existingCar.getMake() + ", press Enter to skip): ");
        String updateModel = InputValidation.validateString("Model (" + existingCar.getModel() + ", press Enter to skip): ");

        // Handle integer and double types with default values for skipping
        String updateYearInput = InputValidation.validateString("Year (" + existingCar.getYear() + ", press Enter to skip): ");
        int updateYear = updateYearInput.isEmpty() ? existingCar.getYear() : Integer.parseInt(updateYearInput);

        String updateMileageInput = InputValidation.validateString("Mileage (" + existingCar.getMileage() + ", press Enter to skip): ");
        int updateMileage = updateMileageInput.isEmpty() ? existingCar.getMileage() : Integer.parseInt(updateMileageInput);

        String updateColor = InputValidation.validateString("Color (" + existingCar.getColor() + ", press Enter to skip): ");

        String updateStatusInput = InputValidation.validateString("Status (true for available, false for sold) (" + existingCar.getStatus() + ", press Enter to skip): ");
        boolean updateStatus = updateStatusInput.isEmpty() ? existingCar.getStatus() : Boolean.parseBoolean(updateStatusInput);

        String updatePriceInput = InputValidation.validateString("Price (" + existingCar.getPrice() + ", press Enter to skip): ");
        double updatePrice = updatePriceInput.isEmpty() ? existingCar.getPrice() : Double.parseDouble(updatePriceInput);

        String updateNotes = InputValidation.validateString("Notes (" + existingCar.getNotes() + ", press Enter to skip): ");

        // Create a new Car object with updated information
        Car updatedCar = new Car(
                updateCarID,
                updateMake.isEmpty() ? existingCar.getMake() : updateMake,
                updateModel.isEmpty() ? existingCar.getModel() : updateModel,
                updateYear,
                updateMileage,
                updateColor.isEmpty() ? existingCar.getColor() : updateColor,
                updateStatus,
                updatePrice,
                updateNotes.isEmpty() ? existingCar.getNotes() : updateNotes
        );

        // Update the car information in the system
        carCRUD.updateCar(updateCarID, updatedCar);

        // Log the update
        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Updated car information: " + updateCarID
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

    @Override
    public void displayCarByPrice(){
        System.out.format("%-15s %-15s %-15s %-10s %-10s %-15s %-15s %-12s %-25s %s%n",
                "Car ID", "Make", "Model", "Year", "Mileage", "Color", "Status", "Price", "Notes", "Deleted");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
        // Print each car's details in a formatted way with adjusted gaps
        carCRUD.getCarsOrderedByID(CarCRUD.OrderType.Price, true)
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

            String logID = ActivityLog.generateLogID();
            ActivityLogService.logActivity(
                    logID,
                    new Date(),
                    loggedUser.getUsername(),
                    loggedUser.getUserID(),
                    "Display car by price"
            );

            System.out.println();  // Add blank line between cars
        }

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
                .filter(car -> !car.isDeleted())
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

    @Override
    public void displayAllAvailableCar() {
        // Print table headers with increased gaps
        System.out.format("%-15s %-15s %-15s %-10s %-10s %-15s %-15s %-12s %-25s %s%n",
                "Car ID", "Make", "Model", "Year", "Mileage", "Color", "Price", "Notes", "Deleted");

        // Print a separator line
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");

        // Print each car's details in a formatted way with adjusted gaps
        carCRUD.getCarsOrderedByID(CarCRUD.OrderType.CarID, true)
                .stream()
                .filter(car -> !car.isDeleted() && car.getStatus()) // Filter for available cars only
                .forEach(car -> {
                    System.out.format("%-15s %-15s %-15s %-10d %-10d %-15s %-12.2f %-25s %b%n",
                            car.getCarID(),
                            car.getMake(),
                            car.getModel(),
                            car.getYear(),
                            car.getMileage(),
                            car.getColor(),
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
                "Display available cars"
        );
    }

    @Override
    public void searchCarByID(){
        String carID = InputValidation.validateExistingCarID("Enter the Car ID to search: ");
        carCRUD.searchCarByID(carID);
        // Log the activity
        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Displayed car details for ID: " + carID
        );
    }

}
