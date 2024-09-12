package part;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AutoPart implements Serializable {
    private static final long serialVersionUID = 1L;  // Adding serialVersionUID for serialization compatibility

    private String partID;
    private String partName;
    private String manufacturer;
    private String partNumber;
    private String condition;
    private String warranty;
    private double price;
    private String notes;

    static public List<AutoPart> partsList = new ArrayList<>();

    // Default Constructor
    public AutoPart() {
        this.partID = "p-default";
        this.partName = "Default Part Name";
        this.manufacturer = "Default Manufacturer";
        this.partNumber = "0000";
        this.condition = "new";
        this.warranty = "1 year";
        this.price = 0.0;
        this.notes = "No additional notes.";
    }

    // Parameterized Constructor
    public AutoPart(String partID, String partName, String manufacturer, String partNumber, String condition, String warranty, double price, String notes) {
        this.partID = partID;
        this.partName = partName;
        this.manufacturer = manufacturer;
        this.partNumber = partNumber;
        this.condition = condition;
        this.warranty = warranty;
        this.price = price;
        this.notes = notes;
    }

    // Getter and Setter methods
    public static List<AutoPart> getAllParts() {
        return partsList;
    }

    public static void setPartsList(List<AutoPart> newPartsList) {
        partsList = newPartsList;
    }

    public String getPartID() {
        return partID;
    }

    public void setPartID(String partID) {
        this.partID = partID;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
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

    @Override
    public String toString() {
        return String.format(
                "Part ID: %s\n" +
                        "Part Name: %s\n" +
                        "Manufacturer: %s\n" +
                        "Part Number: %s\n" +
                        "Condition: %s\n" +
                        "Warranty: %s\n" +
                        "Price: $%.2f\n" +
                        "Notes: %s",
                partID, partName, manufacturer, partNumber, condition, warranty, price, notes
        );
    }
}
