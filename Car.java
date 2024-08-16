import java.util.ArrayList;
import java.util.List;

public class Car {
    private String carID; // format c-number
    private String make;
    private String model;
    private int year;
    private int mileage;
    private String color;
    private boolean status; // true for available, false for sold
    private double price;
    private String notes;
    private List<Service> serviceHistory;

    public Car(String carID, String make, String model, int year, int mileage, String color, boolean status, double price, String notes) {
        this.carID = carID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.color = color;
        this.status = status;
        this.price = price;
        this.notes = notes;
        this.serviceHistory = new ArrayList<>();
    }

    // Getters and Setters
    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Service> getServiceHistory() {
        return serviceHistory;
    }

    public void addService(Service service) {
        this.serviceHistory.add(service);
    }

    // Additional Methods
    public void updateStatus(boolean newStatus) {
        this.status = newStatus;
    }

    public void updateNotes(String newNotes) {
        this.notes = newNotes;
    }

    public void displayInfo() {
        System.out.println("Car ID: " + carID);
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Mileage: " + mileage);
        System.out.println("Color: " + color);
        System.out.println("Status: " + (status ? "Available" : "Sold"));
        System.out.println("Price: " + price);
        System.out.println("Notes: " + notes);
        System.out.println("Service History: " + serviceHistory.size() + " services");
    }
}
