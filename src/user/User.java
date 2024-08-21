import FileHandling.*;
import Database.*;



import java.util.*;
import java.io.*;

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
    private String username;

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
        this.username = ""; // Added username initialization
    }

    public User(String userID, String fullName, Date dateOfBirth, String address, String phoneNumber, String email, UserType userType, boolean status, String password, String username) {
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

    // Authentication method
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    // Register method
    public void register(String username, String password, String fullName, String email) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.status = true; // Assuming the user becomes active upon registration
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
        if (newInfo.containsKey("phoneNumber")) this.phoneNumber = newInfo.get("phoneNumber");
        if (newInfo.containsKey("email")) this.email = newInfo.get("email");
        if (newInfo.containsKey("password")) this.password = newInfo.get("password");
        if (newInfo.containsKey("username")) this.username = newInfo.get("username");
    }

    public static void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter full name: ");
        String fullName = scanner.nextLine();

        System.out.println("Enter date of birth (yyyy-mm-dd): ");
        Date dateOfBirth = Date.parse(scanner.nextLine());

        System.out.println("Enter address: ");
        String address = scanner.nextLine();

        System.out.println("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.println("Enter email: ");
        String email = scanner.nextLine();

        System.out.println("Enter a username: ");
        String username = scanner.nextLine();

        System.out.println("Enter a password: ");
        String password = scanner.nextLine();

        // Generate userID (e.g., auto-increment or other logic)
        String userID = generateUserID();

        // Default userType to CLIENT and status to false
        UserType userType = UserType.CLIENT;
        boolean status = false;

        User newUser = new User(userID, fullName, dateOfBirth, address, phoneNumber, email, userType, status, password, username);

        try {
            saveUserToFile(newUser);
            System.out.println("Registration successful! Your User ID is " + userID);
        } catch (IOException e) {
            System.out.println("Error saving user data: " + e.getMessage());
        }
    }

    private static String generateUserID() {
        // Logic to generate a unique userID (e.g., based on current timestamp, counter, etc.)
        // Placeholder implementation
        int idCounter = 1; // You should implement a counter or another method for generating unique IDs.
        return String.format("u%05d", idCounter++);
    }

    private static void saveUserToFile(User user) throws IOException {
        String fileName = "Database/UserDatabase/u" + user.getUserID() + ".txt";
        String userData = user.toString();
        EditFile.saveToFile(fileName, userData);
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
