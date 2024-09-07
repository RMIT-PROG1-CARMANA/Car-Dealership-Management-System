package service;

import part.AutoPart;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Service {
    private String serviceID;
    private Date serviceDate;
    private String clientID;
    private String mechanicID;
    private String serviceType;
    private List<AutoPart> replacedParts;
    private double serviceCost;
    private String notes;

    private static List<Service> serviceList = new ArrayList<>();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Constructors, Getters, and Setters

    public Service() {
        this.serviceID = "s-default";
        this.serviceDate = new Date();
        this.clientID = "c-default";
        this.mechanicID = "m-default";
        this.serviceType = "General Maintenance";
        this.replacedParts = new ArrayList<>();
        this.serviceCost = 0.0;
        this.notes = "No additional notes.";
    }

    public Service(String serviceID, Date serviceDate, String clientID, String mechanicID, String serviceType, double serviceCost, String notes) {
        this.serviceID = serviceID;
        this.serviceDate = serviceDate;
        this.clientID = clientID;
        this.mechanicID = mechanicID;
        this.serviceType = serviceType;
        this.replacedParts = new ArrayList<>();
        this.serviceCost = serviceCost;
        this.notes = notes;
    }

    public String getServiceID() { return serviceID; }
    public void setServiceID(String serviceID) { this.serviceID = serviceID; }
    public Date getServiceDate() { return serviceDate; }
    public void setServiceDate(Date serviceDate) { this.serviceDate = serviceDate; }
    public String getClientID() { return clientID; }
    public void setClientID(String clientID) { this.clientID = clientID; }
    public String getMechanicID() { return mechanicID; }
    public void setMechanicID(String mechanicID) { this.mechanicID = mechanicID; }
    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }
    public List<AutoPart> getReplacedParts() { return replacedParts; }
    public void setReplacedParts(List<AutoPart> replacedParts) { this.replacedParts = replacedParts; }
    public double getServiceCost() { return serviceCost; }
    public void setServiceCost(double serviceCost) { this.serviceCost = serviceCost; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String toFileFormat() {
        String parts = replacedParts.stream()
                .map(AutoPart::getPartID)
                .collect(Collectors.joining(","));
        return String.join(",",
                serviceID,
                dateFormat.format(serviceDate),
                clientID,
                mechanicID,
                serviceType,
                String.valueOf(serviceCost),
                notes,
                parts
        );
    }

    public static void saveServicesToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Service service : serviceList) {
                writer.write(service.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void loadServicesFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(","); // Assuming CSV format
                if (details.length >= 7) { // Adjust according to actual number of fields
                    Service service = new Service(
                            details[0], // serviceID
                            dateFormat.parse(details[1]), // serviceDate
                            details[2], // clientID
                            details[3], // mechanicID
                            details[4], // serviceType
                            Double.parseDouble(details[5]), // serviceCost
                            details[6]  // notes
                    );
                    // Load replaced parts
                    if (details.length > 7) {
                        String[] parts = details[7].split(",");
                        for (String partID : parts) {
                            AutoPart part = AutoPart.getPartByID(partID);
                            if (part != null) {
                                service.replacedParts.add(part);
                            }
                        }
                    }
                    serviceList.add(service);
                }
            }
        } catch (IOException | ParseException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    public static List<Service> getAllServices() { return new ArrayList<>(serviceList); }
    public static void addService(Service service) { serviceList.add(service); }
    public static Service getServiceByID(String serviceID) {
        return serviceList.stream()
                .filter(service -> service.getServiceID().equals(serviceID))
                .findFirst()
                .orElse(null);
    }
    public static void deleteService(String serviceID) {
        serviceList.removeIf(service -> service.getServiceID().equals(serviceID));
    }
}
