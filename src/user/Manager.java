package user;

import java.io.File;
import java.util.*;


public class Manager extends User {
    private List<Car> cars;
    private List<Part> parts;
    private List<Service> services;
    private List<Transaction> transactions;
    private List<User> employees;

    public Manager() {
        this.cars = null;
        this.parts = null;
        this.services = null;
        this.transactions = null;
        this.employees = null;
    }

    public Manager(String userID, String fullName, Date dateOfBirth, String address, String phoneNumber, String email, boolean status, String password) {
        super(userID, fullName, dateOfBirth, address, phoneNumber, email, UserType.MANAGER, status, password);
        this.cars = new ArrayList<>();
        this.parts = new ArrayList<>();
        this.services = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void removeCar(String carID) {
        cars.removeIf(car -> car.getCarID().equals(carID));
    }

    public void addPart(Part part) {
        parts.add(part);
    }

    public void removePart(String partID) {
        parts.removeIf(part -> part.getPartID().equals(partID));
    }

    public void addService(Service service) {
        services.add(service);
    }

    public void removeService(String serviceID) {
        services.removeIf(service -> service.getServiceID().equals(serviceID));
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void removeTransaction(String transactionID) {
        transactions.removeIf(transaction -> transaction.getTransactionID().equals(transactionID));
    }

    public void addEmployee(User employee) {
        employees.add(employee);
    }

    public void removeEmployee(String userID) {
        employees.removeIf(employee -> employee.getUserID().equals(userID));
    }

    // Example statistics methods
    public int calculateCarsSold(Date month) {
        // Implementation would filter cars sold in the specified month
        return 0; // Placeholder
    }

    public double calculateRevenue(String timePeriod) {
        // Implementation would calculate revenue in the given time period
        return 0.0; // Placeholder
    }
}
