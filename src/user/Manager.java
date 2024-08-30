package user;

import java.io.File;
import java.security.Provider;
import java.util.*;


public class Manager extends User {
    private List<User> employees;
    public Manager(String userID, String fullName, Date dateOfBirth, String address, Long phoneNumber,String username, String email, boolean status, String password) {
        super(userID, fullName, dateOfBirth, address, phoneNumber, email, UserType.MANAGER, status,username, password);
        this.employees = new ArrayList<>();
    }

    public void addEmployee(User employee) {
        employees.add(employee);
    }

    public void removeEmployee(String userID) {
        employees.removeIf(employee -> employee.getUserID().equals(userID));
    }

    //get the employee list
    public List<User> getEmployees() {
        return employees;
    }
    // Example statistics methods
    public int calculateCarsSold(Date month) {
        // Implementation would filter cars sold in the specified month
        return 0; // Placeholder
    }

    public double calculateRevenue(String timePeriod) {
        // Implementation would calculate revenue in the given time period
        return 0.0; // Placeholder
    }
}
