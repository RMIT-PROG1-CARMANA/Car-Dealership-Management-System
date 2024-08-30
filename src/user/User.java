package user;

import FileHandling.*;
import java.util.*;
import java.io.*;

public class User {
    private String userID;
    private String fullName;
    private Date dateOfBirth;
    private String address;
    private long phoneNumber;
    private String email;
    private UserType userType;
    private boolean status;
    private String password;
    private String username;


    public enum UserType {
        MANAGER, EMPLOYEE, CLIENT
    }

    public User(){
        this.userID = "00000";
        this.fullName = "";
        this.dateOfBirth = null;
        this.address = "";
        this.phoneNumber = 0L;
        this.email = "";
        this.userType = UserType.MANAGER;
        this.status = false;
        this.password = "";
        this.username = ""; // Added username initialization
    }
//constructor
    public User(String userID, String fullName, Date dateOfBirth, String address, Long phoneNumber, String email, UserType userType, boolean status, String password, String username) {
        this.userID = userID;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userType = userType;
        this.status = status;
        this.password = password;
        this.username = username;
    }

    public void viewInformation() {
        System.out.println("User ID: " + userID);
        System.out.println("Full Name: " + fullName);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Address: " + address);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("Username: " + username); // Added username display
        System.out.println("User Type: " + userType);
        System.out.println("Status: " + status);
    }

    public void modifyInformation(Map<String, String> newInfo) {
        if (newInfo.containsKey("address")) this.address = newInfo.get("address");
        if (newInfo.containsKey("phoneNumber")) this.phoneNumber = Long.parseLong(newInfo.get("phoneNumber"));
        if (newInfo.containsKey("email")) this.email = newInfo.get("email");
        if (newInfo.containsKey("password")) this.password = newInfo.get("password");
        if (newInfo.containsKey("username")) this.username = newInfo.get("username");
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Number getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "status=" + status +
                ", userType=" + userType +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", fullName='" + fullName + '\'' +
                ", userID='" + userID + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
