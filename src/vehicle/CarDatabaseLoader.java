package vehicle;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;


public class CarDatabaseLoader {
    private ArrayList<Car> cars = new ArrayList<>();
    private String carDatabaseFile = "src/vehicle/CarDatabase.ser";

    // Constructor to set the default file path (optional)
    public CarDatabaseLoader() {
        this.carDatabaseFile = carDatabaseFile;
    }

    // Load the car database from the specified file
    public void loadCarDatabase(String testDatabaseFile) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(carDatabaseFile))) {
            cars = (ArrayList<Car>) ois.readObject();  // Deserialize the list of cars
            if (cars.isEmpty()) {
                System.out.println("The car database is empty.");
            } else {
                System.out.println("Car database loaded successfully from " + carDatabaseFile);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + carDatabaseFile);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading car database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Get the list of cars
    public ArrayList<Car> getCars() {
        return cars;
    }

    // Save the car database to the specified file
    public void saveCarDatabase(String testDatabaseFile) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(carDatabaseFile))) {
            oos.writeObject(cars);  // Serialize the list of cars
            oos.flush();
            System.out.println("Car database saved successfully to " + carDatabaseFile);
        } catch (IOException e) {
            System.err.println("Error saving car database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Set a new list of cars
    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    // Overwrite the car database with a new list of cars
    public void overwriteDatabase(ArrayList<Car> newCars) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(carDatabaseFile))) {
            oos.writeObject(newCars);  // Serialize the new list of cars
            oos.flush();
            System.out.println("Car database overwritten successfully in " + carDatabaseFile);
        } catch (IOException e) {
            System.err.println("Error overwriting car database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
