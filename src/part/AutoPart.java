package AutoPart;

import java.util.ArrayList;
import java.util.List;

public class AutoPart {
    private String partID;
    private String partName;
    private String manufacturer;
    private String partNumber;
    private String condition;
    private String warranty;
    private double cost;
    private String notes;

    private static List<AutoPart> partsList = new ArrayList<>();

    // Default Constructor
    public AutoPart() {
        this.partID = "p-default";
        this.partName = "Default Part Name";
        this.manufacturer = "Default Manufacturer";
        this.partNumber = "0000";
        this.condition = "new";
        this.warranty = "1 year";
        this.cost = 0.0;
        this.notes = "No additional notes.";
    }

    // Parameterized Constructor
    public AutoPart(String partID, String partName, String manufacturer, String partNumber, String condition, String warranty, double cost, String notes) {
        this.partID = partID;
        this.partName = partName;
        this.manufacturer = manufacturer;
        this.partNumber = partNumber;
        this.condition = condition;
        this.warranty = warranty;
        this.cost = cost;
        this.notes = notes;
    }

    // Getters and Setters
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "part.AutoPart{" +
                "partID='" + partID + '\'' +
                ", partName='" + partName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", partNumber='" + partNumber + '\'' +
                ", condition='" + condition + '\'' +
                ", warranty='" + warranty + '\'' +
                ", cost=" + cost +
                ", notes='" + notes + '\'' +
                '}';
    }

    // CRUD Operations

    // Create
    public static void addPart(AutoPart part) {
        partsList.add(part);
        System.out.println("Auto part added successfully: " + part.toString());
    }

    // Read
    public static AutoPart getPartByID(String partID) {
        for (AutoPart part : partsList) {
            if (part.getPartID().equals(partID)) {
                return part;
            }
        }
        System.out.println("Auto part not found with ID: " + partID);
        return null;
    }

    // Update
    public static void updatePart(String partID, String partName, String manufacturer, String partNumber, String condition, String warranty, double cost, String notes) {
        AutoPart part = getPartByID(partID);
        if (part != null) {
            if (partName != null) part.setPartName(partName);
            if (manufacturer != null) part.setManufacturer(manufacturer);
            if (partNumber != null) part.setPartNumber(partNumber);
            if (condition != null) part.setCondition(condition);
            if (warranty != null) part.setWarranty(warranty);
            if (cost != 0) part.setCost(cost);
            if (notes != null) part.setNotes(notes);
            System.out.println("Auto part updated successfully: " + part.toString());
        }
    }

    // Delete
    public static void deletePart(String partID) {
        AutoPart part = getPartByID(partID);
        if (part != null) {
            partsList.remove(part);
            System.out.println("Auto part deleted successfully with ID: " + partID);
        }
    }

    // List all parts
    public static void listAllParts() {
        if (partsList.isEmpty()) {
            System.out.println("No parts available.");
        } else {
            for (AutoPart part : partsList) {
                System.out.println(part.toString());
                System.out.println("---------------------------------");
            }
        }
    }
}
