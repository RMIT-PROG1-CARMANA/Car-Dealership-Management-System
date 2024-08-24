package user;

import java.util.*;

public class Salesperson extends Employee {

    public Salesperson(String userID, String fullName, Date dateOfBirth, String address, long phoneNumber, String email, boolean status, String password) {
        super(userID, fullName, dateOfBirth, address, phoneNumber, email, status, password, Position.SALESPERSON);
    }

//    public void processTransaction(sales.SalesTransaction transaction) {
//        // Implementation for processing a transaction
//    }

    @Override
    public double calculateRevenue(String period) {
        // Salesperson-specific revenue calculation logic
        return 0.0; // Placeholder
    }

    @Override
    public void listItems(String itemType, String period) {
        // Salesperson-specific logic for listing items
    }

//    @Override
//    public List<vehicle.Car> listCarsSold(String timePeriod) {
//        // Implementation for listing cars sold in the given period
//        return null; // Placeholder
//    }
}


