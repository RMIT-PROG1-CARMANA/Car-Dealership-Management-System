package FileHandling;

import part.AutoPart;

import java.io.*;
import java.util.ArrayList;

public class PartFileOperations {
    private static final String PART_DATABASE_PATH = "src/Database/PartDatabase.txt";
    private static final String TEMP_DATABASE_PATH = "src/Database/tempPartDatabase.txt";

    // Save an AutoPart object to the file
    public static void savePart(AutoPart part) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PART_DATABASE_PATH, true))) {
            writer.write(partToString(part));
            writer.newLine();
            System.out.println("Auto part saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving auto part: " + e.getMessage());
        }
    }

    // Fetch an AutoPart object by partID from the file
    public static AutoPart fetchPart(String partID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(PART_DATABASE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                AutoPart part = stringToPart(line);
                if (part != null && part.getPartID().equals(partID)) {
                    return part;
                }
            }
        } catch (IOException e) {
            System.err.println("Error fetching auto part: " + e.getMessage());
        }
        return null; // If the part is not found
    }

    // Remove an AutoPart object by partID from the file
    public static boolean removePartById(String partID) {
        File inputFile = new File(PART_DATABASE_PATH);
        File tempFile = new File(TEMP_DATABASE_PATH);

        boolean removed = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                AutoPart part = stringToPart(line);
                if (part != null && part.getPartID().equals(partID)) {
                    removed = true;
                    continue; // Skip writing this part to the temp file
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error removing auto part: " + e.getMessage());
            return false;
        }

        // Replace original file with the temp file
        if (!inputFile.delete()) {
            System.err.println("Error deleting the original file.");
            return false;
        }
        if (!tempFile.renameTo(inputFile)) {
            System.err.println("Error renaming the temp file.");
            return false;
        }

        if (removed) {
            System.out.println("Auto part removed successfully.");
        } else {
            System.out.println("Auto part not found.");
        }

        return removed;
    }

    // Get all AutoParts from the file and return them as an ArrayList
    public static ArrayList<AutoPart> getAllParts() {
        ArrayList<AutoPart> parts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(PART_DATABASE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                AutoPart part = stringToPart(line);
                if (part != null) {
                    parts.add(part);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading auto parts: " + e.getMessage());
        }

        return parts;
    }

    // Helper method to convert an AutoPart object to a String for saving
    private static String partToString(AutoPart part) {
        return part.getPartID() + ";" +
                part.getPartName() + ";" +
                part.getManufacturer() + ";" +
                part.getPartNumber() + ";" +
                part.getCondition() + ";" +
                part.getWarranty() + ";" +
                part.getCost() + ";" +
                part.getNotes();
    }

    // Helper method to convert a String from the file back to an AutoPart object
    private static AutoPart stringToPart(String partString) {
        String[] fields = partString.split(";");
        if (fields.length < 8) {
            return null; // Invalid part record
        }

        return new AutoPart(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], Double.parseDouble(fields[6]), fields[7]);
    }
}
