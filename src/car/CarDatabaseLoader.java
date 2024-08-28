package car;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarDatabaseLoader {

    public List<Car> loadCarDatabase(String carDatabase) {
        List<Car> cars = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(carDatabase))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] carData = line.split(",");
                Car car = getCar(carData);
                cars.add(car);
            }
        } catch (IOException e) {
            System.err.println("Error loading car database: " + e.getMessage());
            e.printStackTrace();
        }

        return cars;
    }

    private static Car getCar(String[] carData) {
        if (carData.length < 9) {
            throw new IllegalArgumentException("Invalid car data: " + String.join(",", carData));
        }
        String carID = carData[0];
        String make = carData[1];
        String model = carData[2];
        int year = Integer.parseInt(carData[3]);
        int mileage = Integer.parseInt(carData[4]);
        String color = carData[5];
        boolean status = Boolean.parseBoolean(carData[6]);
        double price = Double.parseDouble(carData[7]);
        String notes = carData[8];

        return new Car(carID, make, model, year, mileage, color, status, price, notes);
    }
}
