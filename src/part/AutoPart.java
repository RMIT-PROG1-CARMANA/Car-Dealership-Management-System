package part;

import java.io.*;
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
                System.out.println(part.toString());
            }
        }
    }

    // Method to read parts from a file
    public static void loadPartsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(","); // Assuming CSV format
                if (details.length == 8) {
                    AutoPart part = new AutoPart(
                            details[0], // partID
                            details[1], // partName
                            details[2], // manufacturer
                            details[3], // partNumber
                            details[4], // condition
                            details[5], // warranty
                            Double.parseDouble(details[6]), // cost
                            details[7]  // notes
                    );
                    addPart(part);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    // Method to save parts to a file
    public static void savePartsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (AutoPart part : partsList) {
                writer.write(String.join(",",
                        part.getPartID(),
                        part.getPartName(),
                        part.getManufacturer(),
                        part.getPartNumber(),
                        part.getCondition(),
                        part.getWarranty(),
                        String.valueOf(part.getCost()),
                        part.getNotes()
                ));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}