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

    public void addPart(AutoPart part) {
        if (this.replacedParts == null) {
            this.replacedParts = new ArrayList<>();
        }
        this.replacedParts.add(part);
    }

    public boolean removePart(String partID) {
        if (this.replacedParts != null) {
            for (AutoPart part : this.replacedParts) {
                if (part.getPartID().equals(partID)) {
                    this.replacedParts.remove(part);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceID='" + serviceID + '\'' +
                ", serviceDate=" + serviceDate +
                ", clientID='" + clientID + '\'' +
                ", mechanicID='" + mechanicID + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", replacedParts=" + replacedParts +
                ", serviceCost=" + serviceCost +
                ", notes='" + notes + '\'' +
                '}';
    }
}
