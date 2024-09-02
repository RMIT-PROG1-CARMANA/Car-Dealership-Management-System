package FileHandling;

import part.AutoPart;
import service.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceFileOperations {
    private static final String SERVICE_DATABASE_PATH = "src/Database/ServiceDatabase.txt";
    private static final String TEMP_DATABASE_PATH = "src/Database/tempServiceDatabase.txt";

    // Save a Service object to the file
    public static void saveService(Service service) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SERVICE_DATABASE_PATH, true))) {
            writer.write(serviceToString(service));
            writer.newLine();
            System.out.println("Service saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving service: " + e.getMessage());
        }
    }

    // Fetch a Service object by serviceID from the file
    public static Service fetchService(String serviceID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(SERVICE_DATABASE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Service service = stringToService(line);
                if (service != null && service.getServiceID().equals(serviceID)) {
                    return service;
                }
            }
        } catch (IOException e) {
            System.err.println("Error fetching service: " + e.getMessage());
        }
        return null; // If the service is not found
    }

    // Remove a Service object by serviceID from the file
    public static boolean removeServiceById(String serviceID) {
        File inputFile = new File(SERVICE_DATABASE_PATH);
        File tempFile = new File(TEMP_DATABASE_PATH);

        boolean removed = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                Service service = stringToService(line);
                if (service != null && service.getServiceID().equals(serviceID)) {
                    removed = true;
                    continue; // Skip writing this service to the temp file
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error removing service: " + e.getMessage());
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
            System.out.println("Service removed successfully.");
        } else {
            System.out.println("Service not found.");
        }

        return removed;
    }

    // Get all Services from the file and return them as an ArrayList
    public static ArrayList<Service> getAllServices() {
        ArrayList<Service> services = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(SERVICE_DATABASE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Service service = stringToService(line);
                if (service != null) {
                    services.add(service);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading services: " + e.getMessage());
        }

        return services;
    }

    // Helper method to convert a Service object to a String for saving
    private static String serviceToString(Service service) {
        StringBuilder sb = new StringBuilder();
        sb.append(service.getServiceID()).append(";")
                .append(service.getServiceDate()).append(";")
                .append(service.getClientID()).append(";")
                .append(service.getMechanicID()).append(";")
                .append(service.getServiceType()).append(";")
                .append(service.getServiceCost()).append(";")
                .append(service.getNotes()).append(";");

        // Convert AutoParts to String
        List<AutoPart> parts = service.getReplacedParts();
        sb.append(parts.size()).append(";");
        for (AutoPart part : parts) {
            sb.append(part.getPartID()).append(",")
                    .append(part.getPartName()).append(",")
                    .append(part.getCost()).append(";");
        }

        return sb.toString();
    }

    // Helper method to convert a String from the file back to a Service object
    private static Service stringToService(String serviceString) {
        String[] fields = serviceString.split(";");
        if (fields.length < 8) {
            return null; // Invalid service record
        }

        Service service = new Service();
        service.setServiceID(fields[0]);
        service.setServiceDate(fields[1]);
        service.setClientID(fields[2]);
        service.setMechanicID(fields[3]);
        service.setServiceType(fields[4]);
        service.setServiceCost(Double.parseDouble(fields[5]));
        service.setNotes(fields[6]);

        // Convert AutoParts from String
        int partCount = Integer.parseInt(fields[7]);
        List<AutoPart> parts = new ArrayList<>();
        for (int i = 8; i < 8 + partCount; i++) {
            String[] partData = fields[i].split(",");
            if (partData.length == 3) {
                AutoPart part = new AutoPart(partData[0], partData[1], "", "", "", "", Double.parseDouble(partData[2]), "");
                parts.add(part);
            }
        }
        service.setReplacedParts(parts);

        return service;
    }
}