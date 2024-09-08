package service;

import part.AutoPart;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Load AutoParts
        AutoPart.loadPartsFromFile("src/DataBase/parts.txt");

        // Load services
        List<Service> services = ServiceFileHandler.loadServices();

        // Print all services and their replaced parts
        for (Service service : services) {
            System.out.println("Service ID: " + service.getServiceID());
            System.out.println("Replaced Parts: ");
            for (AutoPart part : service.getReplacedParts()) {
                System.out.println(" - " + part.getPartID() + ": " + part.getPartName());
            }
        }

        // Modify services and save
        // Example modification
        if (!services.isEmpty()) {
            Service service = services.get(0);

            // Ensure the part exists before adding
            AutoPart newPart = AutoPart.getPartByID("p-002");
            if (newPart != null) {
                service.getReplacedParts().add(newPart);
            } else {
                System.out.println("Part p-002 not found.");
            }

            // Save updated services
            ServiceFileHandler.saveServices(services);
        }
    }
}
