package crudHandlers;

import FileHandling.CarDataHandler;
import vehicle.Car;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class CarCRUDMethodHandler {
    private final CarDataHandler cdl;

    // Constructor
    public CarCRUDMethodHandler(CarDataHandler cdl) {
        this.cdl = cdl;
    }

    // Creation
    public void addCar(Car car) {
        cdl.getCars().add(car);
        cdl.getCars().sort(Comparator.comparing(Car::getCarID));
        cdl.overwriteDatabase(cdl.getCars());
    }

    // Read
    public List<Car> getCarsOrderedByID(CarCRUD.OrderType type, boolean ascending) {
        ArrayList<Car> sortedCars = new ArrayList<>(cdl.getCars().stream().filter(c -> !c.isDeleted()).toList());
        switch (type) {
            case CarCRUD.OrderType.CarID -> {
                sortedCars.sort(Comparator.comparing(Car::getCarID, ascending ? Comparator.naturalOrder() : Comparator.reverseOrder()));
            }
            case CarCRUD.OrderType.Make -> {
                sortedCars.sort(Comparator.comparing(Car::getMake, ascending ? Comparator.naturalOrder() : Comparator.reverseOrder()));
            }
            case CarCRUD.OrderType.Model -> {
                sortedCars.sort(Comparator.comparing(Car::getModel, ascending ? Comparator.naturalOrder() : Comparator.reverseOrder()));
            }
            case CarCRUD.OrderType.Year -> {
                sortedCars.sort(Comparator.comparing(Car::getYear, ascending ? Comparator.naturalOrder() : Comparator.reverseOrder()));
            }
            case CarCRUD.OrderType.Mileage -> {
                sortedCars.sort(Comparator.comparing(Car::getMileage, ascending ? Comparator.naturalOrder() : Comparator.reverseOrder()));
            }
            case CarCRUD.OrderType.Color -> {
                sortedCars.sort(Comparator.comparing(Car::getColor, ascending ? Comparator.naturalOrder() : Comparator.reverseOrder()));
            }
            case CarCRUD.OrderType.Status -> {
                sortedCars.sort(Comparator.comparing(Car::getStatus, ascending ? Comparator.naturalOrder() : Comparator.reverseOrder()));
            }
            case CarCRUD.OrderType.Price -> {
                sortedCars.sort(Comparator.comparing(Car::getPrice, ascending ? Comparator.naturalOrder() : Comparator.reverseOrder()));
            }
            case CarCRUD.OrderType.Notes -> {
                sortedCars.sort(Comparator.comparing(Car::getNotes, ascending ? Comparator.naturalOrder() : Comparator.reverseOrder()));
            }
            default -> {
                System.out.println("Unexpected value: " + type);
                return List.of();
            }
        }
        return sortedCars.stream().toList();
    }

    // Update
    public void updateCar(String carID, Car newCarInfo) {
        for (Car c : cdl.getCars()) {
            if (c.getCarID().equals(carID) && !c.isDeleted()) {
                c.setMake(newCarInfo.getMake());
                c.setModel(newCarInfo.getModel());
                c.setYear(newCarInfo.getYear());
                c.setMileage(newCarInfo.getMileage());
                c.setColor(newCarInfo.getColor());
                c.setStatus(newCarInfo.getStatus());
                c.setPrice(newCarInfo.getPrice());
                c.setNotes(newCarInfo.getNotes());
                c.setDeleted(newCarInfo.isDeleted());
                cdl.overwriteDatabase(cdl.getCars());
                return;
            }
        }
        System.out.println("Unable to find car with ID " + carID);
    }

    // Delete
    public void softDeleteCarByID(String carID) {
        boolean carFound = false;
        for (Car c : cdl.getCars()) {
            if (Objects.equals(c.getCarID(), carID) && !c.isDeleted()) {
                c.setDeleted(true);  // Mark the car as deleted
                carFound = true;
                break;
            }
        }
        if (carFound) {
            System.out.println("Car deleted successfully.");
        } else {
            System.out.println("Unable to find car with that ID.");
        }
    }

    // Display car by ID
    public void displayCarByID(String carID) {
        boolean carFound = false;  // Flag to track if the car is found

        for (Car c : cdl.getCars()) {
            if (Objects.equals(c.getCarID(), carID) && !c.isDeleted()) {
                c.displayInfo();
                carFound = true;
                break;  // Exit the loop once the car is found and displayed
            }
        }

        if (!carFound) {
            System.out.println("Car not found or has been deleted.");
        }
    }
}