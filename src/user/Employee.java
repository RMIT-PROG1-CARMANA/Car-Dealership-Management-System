package user;

import java.io.Serializable;
import java.util.*;

public abstract class Employee extends User implements Serializable {
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

}