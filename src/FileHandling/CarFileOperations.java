package FileHandling;

import car.Car;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CarFileOperations {

    private static final String CAR_DATABASE_PATH = "src/DataBase/CarDatabase.txt";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    // Method to save a Car object to the file
    public static void saveCar(Car car) {
        try {
            FileHandler.writeToFile(CAR_DATABASE_PATH, carToString(car), true);
            System.out.println("Car saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving car: " + e.getMessage());
        }
    }

    // Method to fetch a Car object by carID from the file
    public static Car fetchCar(String carID) {
        try (BufferedReader reader = FileHandler.readFromFile(CAR_DATABASE_PATH)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Car car = stringToCar(line);
                if (car != null && car.getCarID().equals(carID)) {
                    return car;
                }
            }
        } catch (IOException e) {
            System.err.println("Error fetching car: " + e.getMessage());
        }
        return null; // If the car is not found
    }

    // Method to remove a Car object by carID from the file
    public static boolean removeCarById(String carID) {
        File inputFile = new File(CAR_DATABASE_PATH);
        File tempFile = new File("src/DataBase/tempCarDatabase.txt");

        boolean removed = false;

        try (BufferedReader reader = FileHandler.readFromFile(CAR_DATABASE_PATH);
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                Car car = stringToCar(line);
                if (car != null && car.getCarID().equals(carID)) {
                    removed = true;
                    continue; // Skip writing this car to the temp file
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error removing car: " + e.getMessage());
            return false;
        }

        // Replace original file with the temp file
        if (!FileHandler.deleteFile(inputFile)) {
            System.err.println("Error deleting the original file.");
            return false;
        }
        if (!FileHandler.renameFile(tempFile, inputFile)) {
            System.err.println("Error renaming the temp file.");
            return false;
        }

        if (removed) {
            System.out.println("Car removed successfully.");
        } else {
            System.out.println("Car not found.");
        }

        return removed;
    }

    // Method to get all Cars from the file and return them as an ArrayList
    public static ArrayList<Car> getAllCars() {
        ArrayList<Car> cars = new ArrayList<>();

        try (BufferedReader reader = FileHandler.readFromFile(CAR_DATABASE_PATH)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Car car = stringToCar(line);
                if (car != null) {
                    cars.add(car);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading cars: " + e.getMessage());
        }

        return cars;
    }

    // Helper method to convert a Car object to a String for saving
    private static String carToString(Car car) {
        return String.join(";",
                car.getCarID(),
                car.getMake(),
                car.getModel(),
                Integer.toString(car.getYear()),
                Integer.toString(car.getMileage()),
                car.getColor(),
                Boolean.toString(car.getStatus()),
                Double.toString(car.getPrice()),
                car.getNotes()
        );
    }

    // Helper method to convert a String from the file back to a Car object
    private static Car stringToCar(String carString) {
        String[] fields = carString.split(";");
        if (fields.length != 9) {
            return null; // Invalid car record
        }
        Car car = new Car();
        car.setCarID(fields[0]);
        car.setMake(fields[1]);
        car.setModel(fields[2]);
        car.setYear(Integer.parseInt(fields[3]));
        car.setMileage(Integer.parseInt(fields[4]));
        car.setColor(fields[5]);
        car.setStatus(Boolean.parseBoolean(fields[6]));
        car.setPrice(Double.parseDouble(fields[7]));
        car.setNotes(fields[8]);
        return car;
    }
}
