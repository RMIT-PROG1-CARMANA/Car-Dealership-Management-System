package part;

import java.io.*;
import java.util.List;

public class AutoPartFileHandler {

    // Update the file path to reflect the new location
    private static final String FILE_PATH = "src/DataBase/parts.ser";

    // Load parts from a file
    public static void loadPartsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    AutoPart part = new AutoPart(
                            parts[0], // partID
                            parts[1], // partName
                            parts[2], // manufacturer
                            parts[3], // partNumber
                            parts[4], // condition
                            parts[5], // warranty
                            Double.parseDouble(parts[6]), // cost
                            parts[7]  // notes
                    );
                    AutoPart.addPart(part);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    // Save parts to a file
    public static void savePartsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) { // Overwrites the file
            List<AutoPart> partsList = AutoPart.getAllParts();
            for (AutoPart part : partsList) {
                writer.write(String.format("%s,%s,%s,%s,%s,%s,%.2f,%s",
                        part.getPartID(),
                        part.getPartName(),
                        part.getManufacturer(),
                        part.getPartNumber(),
                        part.getCondition(),
                        part.getWarranty(),
                        part.getCost(),
                        part.getNotes()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Serialize parts to a binary file
    public static void serializeParts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(AutoPart.getAllParts());
            System.out.println("Parts serialized to file successfully.");
        } catch (IOException e) {
            System.out.println("Error serializing parts: " + e.getMessage());
        }
    }

    // Deserialize parts from a binary file
    @SuppressWarnings("unchecked")
    public static void deserializeParts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            List<AutoPart> deserializedParts = (List<AutoPart>) ois.readObject();
            for (AutoPart part : deserializedParts) {
                AutoPart.addPart(part);
            }
            System.out.println("Parts deserialized from file successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error deserializing parts: " + e.getMessage());
        }
    }
}
