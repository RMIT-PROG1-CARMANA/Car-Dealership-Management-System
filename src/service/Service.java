package service;

import part.AutoPart;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Service implements Serializable {
    private static final long serialVersionUID = 1L;

    private String serviceID;
    private Date serviceDate;
    private String clientID;
    private String mechanicID;
    private String serviceType;
    private List<AutoPart> replacedParts = new ArrayList<>();
    private double serviceCost;
    private String notes;

    // Getters and Setters
    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
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
        // If replacedParts is not null and contains parts, format each part's ID, Name, and Price
        String replacedPartsInfo = (replacedParts != null && !replacedParts.isEmpty())
                ? replacedParts.stream()
                .map(part -> String.format("Part ID: %s, Part Name: %s, Price: $%.2f",
                        part.getPartID(), part.getPartName(), part.getPrice()))
                .reduce((part1, part2) -> part1 + "\n               " + part2)
                .orElse("No replaced parts.")
                : "No replaced parts.";

        return String.format("Service Details:%n" +
                        "Service ID    : %s%n" +
                        "Service Date  : %s%n" +
                        "Client ID     : %s%n" +
                        "Mechanic ID   : %s%n" +
                        "Service Type  : %s%n" +
                        "Service Cost  : $%.2f%n" +
                        "Notes         : %s%n" +
                        "Replaced Parts: %s%n",
                serviceID,
                serviceDate,
                clientID,
                mechanicID,
                serviceType,
                serviceCost,
                notes,
                replacedPartsInfo
        );
    }
}
