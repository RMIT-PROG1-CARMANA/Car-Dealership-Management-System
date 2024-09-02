package user;

import java.io.File;
import java.util.*;

public class Mechanic extends Employee {

    public Mechanic(String userID, String fullName, Date dateOfBirth, String address, long phoneNumber, String email, boolean status, String password,String username) {
        super(userID, fullName, dateOfBirth, address, phoneNumber, email, status, password, username,Position.MECHANIC);
    }

//    public void processService(service.Service service) {
//        // Implementation for processing a service
//    }

}
