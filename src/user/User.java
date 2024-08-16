package user;

import java.io.File;
import java.util.*;


public class User {
    private String userID;
    private String fullName;
    private Date dateOfBirth;
    private String address;
    private String phoneNumber;
    private String email;
    private UserType userType;
    private boolean status;
    private String password;

    public enum UserType {
        MANAGER, EMPLOYEE, CLIENT
    }

    public User(){
        this.userID = "00000";
        this.fullName = "";
        this.dateOfBirth = null;
        this.address = "";
        this.phoneNumber = "";
        this.email = "";
        this.userType = UserType.MANAGER;
        this.status = false;
        this.password = "";
    }

    public User(String userID, String fullName, Date dateOfBirth, String address, String phoneNumber, String email, UserType userType, boolean status, String password) {
        this.userID = userID;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userType = userType;
        this.status = status;
        this.password = password;
    }

    public boolean login(String username, String password) {
        return this.email.equals(username) && this.password.equals(password);
    }

    public void viewInformation() {
        System.out.println("User ID: " + userID);
        System.out.println("Full Name: " + fullName);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Address: " + address);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("User Type: " + userType);
        System.out.println("Status: " + status);
    }

    public void modifyInformation(Map<String, String> newInfo) {
        if (newInfo.containsKey("address")) this.address = newInfo.get("address");
        if (newInfo.containsKey("phoneNumber")) this.phoneNumber = newInfo.get("phoneNumber");
        if (newInfo.containsKey("email")) this.email = newInfo.get("email");
        if (newInfo.containsKey("password")) this.password = newInfo.get("password");
    }
}
