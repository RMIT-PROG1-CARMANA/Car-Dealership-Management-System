package car;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
public class CarDatabaseLoader {
    public final ArrayList<Car> cars = new ArrayList<>();
    private String carDatabase = "";

    public void loadCarDatabase(String carDatabase) {

        try (BufferedReader br = new BufferedReader(new FileReader(carDatabase))) {
            this.carDatabase = carDatabase;
            String line;
            while ((line = br.readLine()) != null) {
                String[] carData = line.split(",");
                Car car = getCar(carData);
                cars.add(car);
            }
        } catch (IOException e) {
            System.err.println("Loading: Error loading car database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Car getCar(String[] carData) {
        if (carData.length < 8) {
            throw new IllegalArgumentException("Invalid car data: " + String.join(",", carData));
        }
        String carID = carData[0];
        boolean isDeleted = Boolean.parseBoolean(carData[1]);
        String make = carData[2];
        String model = carData[3];
        int year = Integer.parseInt(carData[4]);
        int mileage = Integer.parseInt(carData[5]);
        String color = carData[6];
        boolean status = Boolean.parseBoolean(carData[7]);
        double price = Double.parseDouble(carData[8]);
        int i = 9;
        StringBuilder notes = new StringBuilder();
        while (i < carData.length) {
            notes.append(carData[i]);
            if (i++ < carData.length - 1) {
                notes.append(",");
            }
        }

        return new Car(carID, make, model, year, mileage, color, status, price, notes.toString(), isDeleted);
    }

    public void overwriteDatabase(List<Car> cars) {
        try (PrintWriter pr = new PrintWriter(carDatabase)) {
            for (Car c : cars) {
                pr.println(String.format("%s,%s,%s,%s,%d,%d,%s,%s,%.2f,%s",c.getCarID(), c.isDeleted(), c.getMake(), c.getModel(), c.getYear(), c.getMileage(), c.getColor(), c.getStatus(), c.getPrice(), c.getNotes()));
            }
            pr.flush();
        } catch (IOException e) {
            System.err.println("Overwriting: Error loading car database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
