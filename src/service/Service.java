package service;

import java.util.ArrayList;
import java.util.List;
import part.AutoPart;

public class Service {
    private String serviceID;
    private String serviceDate;
    private String clientID;
    private String mechanicID;
    private String serviceType;
    private List<AutoPart> replacedParts;
    private double serviceCost;
    private String notes;

    private static List<Service> serviceList = new ArrayList<>();

    // Default Constructor
    public Service() {
        this.serviceID = "s-default";
        this.serviceDate = "01/01/2024";
        this.clientID = "c-default";
        this.mechanicID = "m-default";
        this.serviceType = "General Maintenance";
        this.replacedParts = new ArrayList<>();
        this.serviceCost = 0.0;
        this.notes = "No additional notes.";
    }

    // Parameterized Constructor
    public Service(String serviceID, String serviceDate, String clientID, String mechanicID, String serviceType,List<AutoPart> replacedParts, double serviceCost, String notes) {
        this.serviceID = serviceID;
        this.serviceDate = serviceDate;
        this.clientID = clientID;
        this.mechanicID = mechanicID;
        this.serviceType = serviceType;
        this.replacedParts = replacedParts;
        this.serviceCost = serviceCost;
        this.notes = notes;
    }

    // Getters and Setters
    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getMechanicID() {
        return mechanicID;
    }

    public void setMechanicID(String mechanicID) {
        this.mechanicID = mechanicID;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public List<AutoPart> getReplacedParts() {
        return replacedParts;
    }

    public void setReplacedParts(List<AutoPart> replacedParts) {
        this.replacedParts = replacedParts;
    }

    public double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(double serviceCost) {
        this.serviceCost = serviceCost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceID='" + serviceID + '\'' +
                ", serviceDate='" + serviceDate + '\'' +
                ", clientID='" + clientID + '\'' +
                ", mechanicID='" + mechanicID + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", replacedParts=" + replacedParts +
                ", serviceCost=" + serviceCost +
                ", notes='" + notes + '\'' +
                '}';
    }

    // Add a Service to the System
    public static void addService(Service service) {
        serviceList.add(service);
        System.out.println("Service added successfully: " + service.toString());
    }

    // Get a Service by ID
    public static Service getServiceByID(String serviceID) {
        for (Service service : serviceList) {
            if (service.getServiceID().equals(serviceID)) {
                return service;
            }
        }
        System.out.println("Service not found with ID: " + serviceID);
        return null;
    }

    // Update a Service
    public void updateService(String serviceDate, String clientID, String mechanicID, String serviceType, double serviceCost, String notes) {
        if (serviceDate != null) this.serviceDate = serviceDate;
        if (clientID != null) this.clientID = clientID;
        if (mechanicID != null) this.mechanicID = mechanicID;
        if (serviceType != null) this.serviceType = serviceType;
        if (serviceCost != 0) this.serviceCost = serviceCost;
        if (notes != null) this.notes = notes;
        System.out.println("Service updated successfully: " + this.toString());
    }

    // Delete a Service by ID
    public static void deleteService(String serviceID) {
        Service service = getServiceByID(serviceID);
        if (service != null) {
            serviceList.remove(service);
            System.out.println("Service deleted successfully with ID: " + serviceID);
        }
    }

    // Add an AutoPart to the Service
    public void addPartToService(AutoPart part) {
        this.replacedParts.add(part);
        System.out.println("Part added to service: " + part.toString());
    }

    // Remove an AutoPart from the Service
    public void removePartFromService(String partID) {
        AutoPart partToRemove = null;
        for (AutoPart part : replacedParts) {
            if (part.getPartID().equals(partID)) {
                partToRemove = part;
                break;
            }
        }
        if (partToRemove != null) {
            replacedParts.remove(partToRemove);
            System.out.println("Part removed from service with ID: " + partID);
        } else {
            System.out.println("Part not found with ID: " + partID);
        }
    }

    // List All Services
    public static void listAllServices() {
        if (serviceList.isEmpty()) {
            System.out.println("No services available.");
        } else {
            for (Service service : serviceList) {
                System.out.println(service.toString());
                System.out.println("---------------------------------");
            }
        }
    }
}
