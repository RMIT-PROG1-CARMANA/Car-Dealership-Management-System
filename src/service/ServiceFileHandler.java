package service;

import part.AutoPart;
import service.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceFileHandler {

    private static final String PARTS_FILE = "src/DataBase/parts.txt";
    private static final String SERVICES_FILE = "src/DataBase/services.txt";

    public static List<AutoPart> loadPartsFromFile() {
        List<AutoPart> parts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PARTS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Parse line to create AutoPart object
                AutoPart part = parseAutoPart(line);
                parts.add(part);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parts;
    }

    public static List<Service> loadServicesFromFile() {
        List<Service> services = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(SERVICES_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Parse line to create Service object
                Service service = parseService(line);
                services.add(service);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return services;
    }

//    public static void savePartsToFile(List<AutoPart> parts) {
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PARTS_FILE))) {
//            for (AutoPart part : parts) {
//                bw.write(part.toFileString());
//                bw.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void saveServicesToFile(List<Service> services) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(SERVICES_FILE))) {
            for (Service service : services) {
                bw.write(service.toFileFormat());  // Correct method call
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static AutoPart parseAutoPart(String line) {
        // Implement parsing logic here
        return new AutoPart(); // Example
    }

    private static Service parseService(String line) {
        // Implement parsing logic here
        return new Service(); // Example
    }
}
