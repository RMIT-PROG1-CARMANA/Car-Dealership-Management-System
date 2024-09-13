package FileHandling;

import part.AutoPart;
import operations.*;

import java.io.*;
import java.util.*;

public class AutoPartFileHandler {

    // Update the file path to reflect the new location
    private static final String FILE_PATH = "src/DataBase/parts.txt";


    // Serialize parts to a binary file
    public static void savePartsData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(AutoPart.getAllParts());
            System.out.println("Parts saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error serializing parts: " + e.getMessage());
        }
    }

    // Deserialize parts from a binary file
    @SuppressWarnings("unchecked")
    public static void loadPartsData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            // Replace the existing parts list with the deserialized one
            List<AutoPart> loadPartsData = (List<AutoPart>) ois.readObject();
            AutoPart.setPartsList(loadPartsData);  // Replace the parts list with deserialized data

            System.out.println("Parts loaded from file successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error deserializing parts: " + e.getMessage());
        }
    }

}
