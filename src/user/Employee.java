package user;

import java.util.*;

public abstract class Employee extends User {
    private Position position;

    public enum Position {
        SALESPERSON, MECHANIC
    }

    public Employee(String userID, String fullName, Date dateOfBirth, String address, long phoneNumber, String email, boolean status, String password,String username, Position position) {
        super(userID, fullName, dateOfBirth, address, phoneNumber, email, UserType.EMPLOYEE, status, password, username); // Add username if necessary
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    // Abstract methods to be implemented by subclasses
    public abstract double calculateRevenue(String period);
    public abstract void listItems(String itemType, String period);
}
