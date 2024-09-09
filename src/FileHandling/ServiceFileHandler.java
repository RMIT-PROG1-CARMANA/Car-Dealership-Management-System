package FileHandling;

import part.AutoPart;
import service.Service;

import java.io.*;
import java.util.*;

public class ServiceFileHandler {
    private static final String SERVICE_FILE_PATH = "src/DataBase/services.txt";
    private static final String PARTS_FILE_PATH = "src/DataBase/parts.txt";

    // Load parts from file and return as a Map
    public static Map<String, AutoPart> loadParts() {
        Map<String, AutoPart> partsMap = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PARTS_FILE_PATH))) {
            Object object = ois.readObject();
            if (object instanceof List<?>) {
                List<AutoPart> partsList = (List<AutoPart>) object;
                for (AutoPart part : partsList) {
                    partsMap.put(part.getPartID(), part);
                }
            } else {
                System.out.println("Unexpected object type in parts file. Expected List<AutoPart>, got " + object.getClass().getName());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading parts from file: " + e.getMessage());
        }
        return partsMap;
    }

    // Load services from file and return as a List
    public static List<Service> loadServices() {
        List<Service> services = new ArrayList<>();
        Map<String, AutoPart> partsMap = loadParts();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SERVICE_FILE_PATH))) {
            Object object = ois.readObject();
            if (object instanceof List<?>) {
                services = (List<Service>) object;
            } else {
                System.out.println("Unexpected object type in services file. Expected List<Service>, got " + object.getClass().getName());
            }

            // Ensure replaced parts are updated
            for (Service service : services) {
                List<AutoPart> replacedParts = new ArrayList<>();
                for (AutoPart part : service.getReplacedParts()) {
                    AutoPart loadedPart = partsMap.get(part.getPartID());
                    if (loadedPart != null) {
                        replacedParts.add(loadedPart);
                    } else {
                        System.out.println("Part not found: " + part.getPartID());
                    }
                }
                service.setReplacedParts(replacedParts);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading services from file: " + e.getMessage());
        }

        return services;
    }

    // Save services to file
    public static void saveServices(List<Service> services) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SERVICE_FILE_PATH))) {
            oos.writeObject(services);
            System.out.println("Services saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving services: " + e.getMessage());
        }
    }

    // Save parts to file
    public static void saveParts(List<AutoPart> parts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PARTS_FILE_PATH))) {
            oos.writeObject(parts);
            System.out.println("Parts saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving parts: " + e.getMessage());
        }
    }
}
