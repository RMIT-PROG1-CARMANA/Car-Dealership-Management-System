package user;

import java.io.File;
import java.util.*;

public abstract class Employee extends User {
    private Position position;

    public enum Position {
        SALESPERSON, MECHANIC
    }

    public Employee(String userID, String fullName, Date dateOfBirth, String address, String phoneNumber, String email, boolean status, String password, Position position) {
        super(userID, fullName, dateOfBirth, address, phoneNumber, email, UserType.EMPLOYEE, status, password);
        this.position = position;
    }

    public abstract double calculateRevenue(String period);

    public abstract void listItems(String itemType, String period);
}

