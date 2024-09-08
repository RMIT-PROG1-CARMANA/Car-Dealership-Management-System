package service;

import part.AutoPart;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ServiceFileHandler {
    private static final String SERVICE_FILE_PATH = "src/DataBase/services.txt";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static List<Service> loadServices() {
        List<Service> services = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(SERVICE_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 7) {
                    System.out.println("Skipping malformed line: " + line);
                    continue; // Skip malformed lines
                }

                try {
                    String serviceID = parts[0];
                    Date serviceDate = DATE_FORMAT.parse(parts[1]);
                    String clientID = parts[2];
                    String mechanicID = parts[3];
                    String serviceType = parts[4];
                    double serviceCost = Double.parseDouble(parts[5]);
                    String notes = parts[6];

                    // Load replaced parts
                    List<AutoPart> replacedParts = new ArrayList<>();
                    for (int i = 7; i < parts.length; i++) {
                        AutoPart part = AutoPart.getPartByID(parts[i]);
                        if (part != null) {
                            replacedParts.add(part);
                        } else {
                            System.out.println("Part not found or not loaded properly: " + parts[i]);
                        }
                    }

                    Service service = new Service();
                    service.setServiceID(serviceID);
                    service.setServiceDate(serviceDate);
                    service.setClientID(clientID);
                    service.setMechanicID(mechanicID);
                    service.setServiceType(serviceType);
                    service.setServiceCost(serviceCost);
                    service.setNotes(notes);
                    service.setReplacedParts(replacedParts);

                    services.add(service);

                    // Debug: Print loaded service details
                    System.out.println("Loaded Service: " + serviceID + " with parts: " + replacedParts);

                } catch (ParseException | NumberFormatException e) {
                    System.out.println("Error parsing line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return services;
    }

    public static void saveServices(List<Service> services) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(SERVICE_FILE_PATH))) {
            for (Service service : services) {
                StringBuilder sb = new StringBuilder();
                sb.append(service.getServiceID()).append(",");
                sb.append(DATE_FORMAT.format(service.getServiceDate())).append(",");
                sb.append(service.getClientID()).append(",");
                sb.append(service.getMechanicID()).append(",");
                sb.append(service.getServiceType()).append(",");
                sb.append(service.getServiceCost()).append(",");
                sb.append(service.getNotes());

                List<AutoPart> replacedParts = service.getReplacedParts();
                if (replacedParts != null) {
                    for (AutoPart part : replacedParts) {
                        sb.append(",").append(part.getPartID());
                    }
                }

                // Debug: Print saved service details
                System.out.println("Saving Service: " + sb.toString());

                bw.write(sb.toString());
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
