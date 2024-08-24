package user;

import java.io.File;
import java.util.*;

public class Mechanic extends Employee {

    public Mechanic(String userID, String fullName, Date dateOfBirth, String address, long phoneNumber, String email, boolean status, String password) {
        super(userID, fullName, dateOfBirth, address, phoneNumber, email, status, password, Position.MECHANIC);
    }

//    public void processService(service.Service service) {
//        // Implementation for processing a service
//    }

    @Override
    public double calculateRevenue(String timePeriod) {
        // Implementation for calculating revenue for this mechanic
        return 0.0; // Placeholder
    }

//    @Override
//    public List<service.Service> listServices(String timePeriod) {
//        // Implementation for listing services done in the given period
//        return null; // Placeholder
//    }

    @Override
    public void listItems(String itemType, String period) {
        // Mechanic-specific logic for listing items
    }
}
