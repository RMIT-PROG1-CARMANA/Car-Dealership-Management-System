package crudhandlers;

import filehandling.CarDataHandler;
import vehicle.Car;

import java.util.List;

public class CarCRUD {
    private final CarCRUDMethodHandler methodHandler;

    public enum OrderType {
        CarID,
        Make,
        Model,
        Year,
        Mileage,
        Color,
        Status,
        Price,
        Notes,
    }

    // Constructor
    public CarCRUD(String carDB) {
        CarDataHandler cdl = new CarDataHandler();
        cdl.loadCarDatabase(carDB);
        methodHandler = new CarCRUDMethodHandler(cdl);
    }

    // Creation
    public void addCar(Car car) {
        methodHandler.addCar(car);
    }

    // Read
    public List<Car> getCarsOrderedByID(OrderType type, boolean ascending) {
        return methodHandler.getCarsOrderedByID(type, ascending);
    }

    // Update
    public void updateCar(String carID, Car newCarInfo) {
        methodHandler.updateCar(carID, newCarInfo);
    }

    // Delete
    public void softDeleteCarByID(String carID) {
        methodHandler.softDeleteCarByID(carID);
    }

    public void searchCarByID(String carID){
        methodHandler.displayCarByID(carID);
    }
    // Find Car by ID
    public Car findCarByID(String carID) {
        return methodHandler.findCarByID(carID);
    }

}