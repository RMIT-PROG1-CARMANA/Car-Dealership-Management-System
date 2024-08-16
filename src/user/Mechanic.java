package user;

import java.io.File;
import java.util.*;

public class Mechanic extends Employee {
    public Mechanic(String userID, String fullName, Date dateOfBirth, String address, String phoneNumber, String email, boolean status, String password) {
        super(userID, fullName, dateOfBirth, address, phoneNumber, email, status, password, Position.MECHANIC);
    }

    public void processService(Service service) {
        // Implementation for processing a service
    }

    @Override
    public double calculateRevenue(String timePeriod) {
        // Implementation for calculating revenue for this mechanic
        return 0.0; // Placeholder
    }

    @Override
    public List<Service> listServices(String timePeriod) {
        // Implementation for listing services done in the given period
        return null; // Placeholder
    }
}
