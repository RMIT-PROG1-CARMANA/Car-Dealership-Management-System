import part.AutoPart;
import service.Service;

public class Test {
    public static void main(String[] args) {
//        // Create some part.AutoPart objects
//        AutoPart part1 = new AutoPart("p-001", "Brake Pad", "Brembo", "BP1234", "new", "2 years", 1500.0, "Front brake pad");
//        AutoPart part2 = new AutoPart("p-002", "Oil Filter", "Bosch", "OF5678", "new", "1 year", 300.0, "For diesel engine");
//        AutoPart part3 = new AutoPart("p-003", "Air Filter", "K&N", "AF9101", "new", "1 year", 500.0, "High flow air filter");
//
//        // Test Create: Add parts to the system
//        AutoPart.addPart(part1);
//        AutoPart.addPart(part2);
//        AutoPart.addPart(part3);
//
//        // Test Read: Get a part by ID
//        System.out.println("Testing getPartByID(\"p-002\"):");
//        AutoPart retrievedPart = AutoPart.getPartByID("p-002");
//        if (retrievedPart != null) {
//            System.out.println(retrievedPart.toString());
//        }
//
//        // Test Update: Update a part's details
//        System.out.println("\nTesting updatePart(\"p-003\"):");
//        AutoPart.updatePart("p-003", "Air Filter", "K&N", "AF9101", "refurbished", "6 months", 400.0, "Refurbished air filter");
//        AutoPart updatedPart = AutoPart.getPartByID("p-003");
//        if (updatedPart != null) {
//            System.out.println(updatedPart.toString());
//        }
//
//        // Test Delete: Delete a part by ID
//        System.out.println("\nTesting deletePart(\"p-001\"):");
//        AutoPart.deletePart("p-001");
//
//        // Test List All: List all remaining parts
//        System.out.println("\nListing all parts:");
//        AutoPart.listAllParts();
//    }
//}


        // Create some AutoPart objects
        AutoPart part1 = new AutoPart("p-001", "Brake Pad", "Brembo", "BP1234", "new", "2 years", 1500.0, "Front brake pad");
        AutoPart part2 = new AutoPart("p-002", "Oil Filter", "Bosch", "OF5678", "new", "1 year", 300.0, "For diesel engine");

        // Create a Service object
        Service service1 = new Service("s-001", "01/08/2024", "c-001", "m-001", "Oil Change", 200.0, "Regular oil change");

        // Test Create: Add the service to the system
        Service.addService(service1);

        // Test Add Part: Add parts to the service
        service1.addPartToService(part1);
        service1.addPartToService(part2);

        // Test Read: Get the service by ID
        System.out.println("Testing getServiceByID(\"s-001\"):");
        Service retrievedService = Service.getServiceByID("s-001");
        if (retrievedService != null) {
            System.out.println(retrievedService.toString());
        }

        // Test Update: Update service details
        System.out.println("\nTesting updateService(\"s-001\"):");
        retrievedService.updateService("02/08/2024", "c-002", "m-002", "Brake Replacement", 400.0, "Replaced brake pads");

        // Test Remove Part: Remove a part from the service
        System.out.println("\nTesting removePartFromService(\"p-001\"):");
        retrievedService.removePartFromService("p-001");

        // Test List All: List all services
        System.out.println("\nListing all services:");
        Service.listAllServices();

        // Test Delete: Delete the service by ID
        System.out.println("\nTesting deleteService(\"s-001\"):");
        Service.deleteService("s-001");

        // Verify Deletion
        System.out.println("\nListing all services after deletion:");
        Service.listAllServices();
    }
}