package FileHandling;

import user.Employee;
import user.Mechanic;
import user.Salesperson;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EmployeeFileOperations {

    private static final String USER_DATABASE_PATH = "src/DataBase/UserDatabase.txt";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    // Method to save an Employee object to the file
    public static void saveEmployee(Employee employee) {
        try {
            FileHandler.writeToFile(USER_DATABASE_PATH, employeeToString(employee), true);
            System.out.println("Employee saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving employee: " + e.getMessage());
        }
    }

    // Method to fetch an Employee object by userID from the file
    public static Employee fetchEmployee(String userID) {
        try (BufferedReader reader = FileHandler.readFromFile(USER_DATABASE_PATH)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Employee employee = stringToEmployee(line);
                if (employee != null && employee.getUserID().equals(userID)) {
                    return employee;
                }
            }
        } catch (IOException e) {
            System.err.println("Error fetching employee: " + e.getMessage());
        }
        return null; // If not found
    }

    // Method to remove an Employee by userID from the file
    public static boolean removeEmployeeById(String userID) {
        File inputFile = new File(USER_DATABASE_PATH);
        File tempFile = new File("src/DataBase/tempEmployeeDatabase.txt");

        boolean removed = false;

        try (BufferedReader reader = FileHandler.readFromFile(USER_DATABASE_PATH);
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                Employee employee = stringToEmployee(line);
                if (employee != null && employee.getUserID().equals(userID)) {
                    removed = true;
                    continue; // Skip writing this employee to the temp file
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error removing employee: " + e.getMessage());
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
            System.out.println("Employee removed successfully.");
        } else {
            System.out.println("Employee not found.");
        }

        return removed;
    }

    // Method to get all Employees from the file and return them as an ArrayList
    public static ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();

        try (BufferedReader reader = FileHandler.readFromFile(USER_DATABASE_PATH)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Employee employee = stringToEmployee(line);
                if (employee != null) {
                    employees.add(employee);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading employees: " + e.getMessage());
        }

        return employees;
    }

    // Helper method to convert an Employee object to a String for saving
    private static String employeeToString(Employee employee) {
        return String.join(";",
                employee.getUserID(),
                employee.getFullName(),
                employee.getDateOfBirth() != null ? DATE_FORMAT.format(employee.getDateOfBirth()) : "",
                employee.getAddress(),
                String.valueOf(employee.getPhoneNumber()), // Convert phoneNumber to String
                employee.getEmail(),
                employee.getPosition().name(),
                Boolean.toString(employee.isStatus()),
                employee.getPassword(),
                employee.getUsername()
        );
    }

    // Helper method to convert a String from the file back to an Employee object
    private static Employee stringToEmployee(String userString) {
        String[] fields = userString.split(";");
        if (fields.length < 10) {
            return null; // Invalid employee record
        }

        String userID = fields[0];
        String fullName = fields[1];
        Date dateOfBirth = null;
        try {
            dateOfBirth = fields[2].isEmpty() ? null : DATE_FORMAT.parse(fields[2]);
        } catch (ParseException e) {
            e.printStackTrace(); // Handle parsing exception
        }
        String address = fields[3];
        long phoneNumber = 0;
        try {
            phoneNumber = Long.parseLong(fields[4]); // Convert String to long
        } catch (NumberFormatException e) {
            e.printStackTrace(); // Handle parsing exception
        }
        String email = fields[5];
        boolean status = Boolean.parseBoolean(fields[6]);
        String password = fields[7];
        String username = fields[8];
        Employee.Position position = Employee.Position.valueOf(fields[9]);

        Employee employee = null;

        if (position == Employee.Position.MECHANIC) {
            employee = new Mechanic(userID, fullName, dateOfBirth, address, phoneNumber, email, status, password, username);
        } else if (position == Employee.Position.SALESPERSON) {
            employee = new Salesperson(userID, fullName, dateOfBirth, address, phoneNumber, email, status, password, username);
        }

        return employee;
    }
}
