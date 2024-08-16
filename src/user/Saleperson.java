package user;

import java.io.File;
import java.util.*;

public class Salesperson extends Employee {
    public Salesperson(String userID, String fullName, Date dateOfBirth, String address, String phoneNumber, String email, boolean status, String password) {
        super(userID, fullName, dateOfBirth, address, phoneNumber, email, status, password, Position.SALESPERSON);
    }

    public void processTransaction(Transaction transaction) {
        // Implementation for processing a transaction
    }

    @Override
    public double calculateRevenue(String timePeriod) {
        // Implementation for calculating revenue for this salesperson
        return 0.0; // Placeholder
    }

    @Override
    public List<Car> listCarsSold(String timePeriod) {
        // Implementation for listing cars sold in the given period
        return null; // Placeholder
    }
}
