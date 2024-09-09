package part;

import java.io.*;
import java.util.List;

public class AutoPartFileHandler {

    // Update the file path to reflect the new location
    private static final String FILE_PATH = "src/DataBase/parts.ser";


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
                AutoPartMenu.addPart(part);
            }
            System.out.println("Parts deserialized from file successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error deserializing parts: " + e.getMessage());
        }
    }
}
