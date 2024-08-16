package user;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private String serviceID;
    private String serviceDate;
    private String clientID;
    private String mechanicID;
    private String serviceType;
    private List<AutoPart> replacedParts;
    private double serviceCost;
    private String notes;

    public Service(String serviceID, String serviceDate, String clientID, String mechanicID, String serviceType, double serviceCost, String notes) {
        this.serviceID = serviceID;
        this.serviceDate = serviceDate;
        this.clientID = clientID;
        this.mechanicID = mechanicID;
        this.serviceType = serviceType;
        this.replacedParts = new ArrayList<>();
        this.serviceCost = serviceCost;
        this.notes = notes;
    }

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
}
