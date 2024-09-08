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

    // Getter and Setter methods
    public static List<AutoPart> getAllParts() {
        return new ArrayList<>(partsList);
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
        return "AutoPart{" +
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

    // Static methods for managing parts
    public static void addPart(AutoPart part) {
        partsList.add(part);
    }

    public static AutoPart getPartByID(String partID) {
        for (AutoPart part : partsList) {
            if (part.getPartID().equals(partID)) {
                return part;
            }
        }
        return null;
    }

    public static void deletePart(String partID) {
        partsList.removeIf(part -> part.getPartID().equals(partID));
    }

    public static void listAllParts() {
        if (partsList.isEmpty()) {
            System.out.println("No parts available.");
        } else {
            for (AutoPart part : partsList) {
                System.out.println(part);
            }
        }
    }

    // Serialization method to save parts to a file
    public static void savePartsToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(partsList);
            System.out.println("Parts saved successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving parts to file: " + e.getMessage());
        }
    }

    // Deserialization method to load parts from a file
    public static void loadPartsFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            partsList = (List<AutoPart>) ois.readObject();
            System.out.println("Parts loaded successfully from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading parts from file: " + e.getMessage());
        }
    }
}
