package user;

import java.util.*;

public class Salesperson extends Employee {

    public Salesperson(String userID, String fullName, Date dateOfBirth, String address, long phoneNumber, String email, boolean status, String password,String username) {
        super(userID, fullName, dateOfBirth, address, phoneNumber, email, status, password, username,Position.SALESPERSON);
    }

}


