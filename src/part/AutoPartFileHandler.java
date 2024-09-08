package part;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AutoPartFileHandler {

    // Update the file path to reflect the new location
    private static final String FILE_PATH = "src/DataBase/parts.txt";

    public static void loadPartsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    AutoPart part = new AutoPart(
                            parts[0],  // partID
                            parts[1],  // partName
                            parts[2],  // manufacturer
                            parts[3],  // partNumber
                            parts[4],  // condition
                            parts[5],  // warranty
                            Double.parseDouble(parts[6]),  // cost
                            parts[7]   // notes
                    );
                    AutoPart.addPart(part);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    public static void savePartsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {  // Set to 'false' to overwrite
            for (AutoPart part : AutoPart.getAllParts()) {
                writer.write(String.format("%s,%s,%s,%s,%s,%s,%f,%s%n",
                        part.getPartID(),
                        part.getPartName(),
                        part.getManufacturer(),
                        part.getPartNumber(),
                        part.getCondition(),
                        part.getWarranty(),
                        part.getCost(),
                        part.getNotes()));
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
