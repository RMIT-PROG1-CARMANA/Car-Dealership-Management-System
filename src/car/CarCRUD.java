package car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CarCRUD {
    private final CarDatabaseLoader cdl = new CarDatabaseLoader();
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

        cdl.loadCarDatabase(carDB);

    }

    // Creation
    public void addCar(Car car) {
        cdl.cars.add(car);
        cdl.cars.sort(Comparator.comparing(Car::getCarID));
        cdl.overwriteDatabase(cdl.cars);
    }

    // Read
    public<T> List<Car> getCarsOrderedBy(OrderType type, boolean ascending) {
        ArrayList<Car> sortedCars = new ArrayList<>(cdl.cars);
        switch (type) {
            case CarID -> {
                sortedCars.sort(Comparator.comparing(Car::getCarID, ascending ? Comparator.naturalOrder() : Comparator.reverseOrder()));
            }
            case Make -> {
                sortedCars.sort(Comparator.comparing(Car::getMake, ascending ? Comparator.naturalOrder() : Comparator.reverseOrder()));
            }
            case Model -> {
                sortedCars.sort(Comparator.comparing(Car::getModel, ascending ? Comparator.naturalOrder() : Comparator.reverseOrder()));
            }
            case Year -> {
                sortedCars.sort(Comparator.comparing(Car::getYear, ascending ? Comparator.naturalOrder() : Comparator.reverseOrder()));
            }
            case Mileage -> {
                sortedCars.sort(Comparator.comparing(Car::getMileage, ascending ? Comparator.naturalOrder() : Comparator.reverseOrder()));
            }
            case Color -> {
                sortedCars.sort(Comparator.comparing(Car::getColor, ascending ? Comparator.naturalOrder() : Comparator.reverseOrder()));
            }
            case Status -> {
                sortedCars.sort(Comparator.comparing(Car::getStatus, ascending ? Comparator.naturalOrder() : Comparator.reverseOrder()));
            }
            case Price -> {
                sortedCars.sort(Comparator.comparing(Car::getPrice, ascending ? Comparator.naturalOrder() : Comparator.reverseOrder()));
            }
            case Notes -> {
                sortedCars.sort(Comparator.comparing(Car::getNotes, ascending ? Comparator.naturalOrder() : Comparator.reverseOrder()));
            }
            default -> {
                System.out.println("Unexpected value: " + type);
                return Collections.emptyList();
            }
        }
        return sortedCars.stream().toList();
    }

    // Update
    public void updateCar(String carID, Car newCarInfo) {
        // May change to binary search
        for (Car c : cdl.cars) {
            if (c.getCarID().equals(carID)) {
                c.setMake(newCarInfo.getMake());
                c.setModel(newCarInfo.getModel());
                c.setYear(newCarInfo.getYear());
                c.setMileage(newCarInfo.getMileage());
                c.setColor(newCarInfo.getColor());
                c.setStatus(newCarInfo.getStatus());
                c.setPrice(newCarInfo.getPrice());
                c.setNotes(newCarInfo.getNotes());
                c.setDeleted(newCarInfo.isDeleted());
//				c.setService(newCarInfo.getService());
                cdl.overwriteDatabase(cdl.cars);
                return;
            }
        }
        System.out.println("Unable to find car with ID" + carID);
    }

    // Delete
    public void deleteCar(String carID) {
        // May change to binary search
        for (Car c : cdl.cars) {
            if (c.getCarID().equals(carID)) {
                // Soft delete
                c.setDeleted(true);
                // Update DB
                cdl.overwriteDatabase(cdl.cars);
                return;
            }
        }
        System.out.println("Unable to find car with ID" + carID);
    }
}
