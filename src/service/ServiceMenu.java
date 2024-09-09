package service;

import part.AutoPart;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ServiceMenu {
    static Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static List<Service> serviceList = new ArrayList<>();
    private static List<AutoPart> autoPartsList = new ArrayList<>();

    static {
        // Load services and parts from files
        serviceList = ServiceFileHandler.loadServices();
        // Ensure the loaded parts are a List<AutoPart>
        Map<String, AutoPart> partsMap = ServiceFileHandler.loadParts();
        autoPartsList = new ArrayList<>(partsMap.values());
    }

    private static int getValidInteger(String prompt) {
        int value = -1;
        while (true) {
            try {
                System.out.print(prompt);
                value = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Clear the buffer
            }
        }
    }

    private static double getValidDouble(String prompt) {
        double value = -1;
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                value = Double.parseDouble(input);
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static Date getValidDate(String prompt) {
        Date date = null;
        while (date == null) {
            try {
                System.out.print(prompt);
                date = DATE_FORMAT.parse(scanner.nextLine());
                return date;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter a date in the format dd/MM/yyyy.");
            }
        }
        return date;
    }

    public static void addService() {
        System.out.print("Enter Service ID: ");
        String serviceID = scanner.nextLine();

        Date serviceDate = getValidDate("Enter Service Date (dd/MM/yyyy): ");

        System.out.print("Enter Client ID: ");
        String clientID = scanner.nextLine();

        System.out.print("Enter Mechanic ID: ");
        String mechanicID = scanner.nextLine();

        System.out.print("Enter Service Type: ");
        String serviceType = scanner.nextLine();

        double serviceCost = getValidDouble("Enter Service Cost: ");

        System.out.print("Enter Notes: ");
        String notes = scanner.nextLine();

        Service newService = new Service();
        newService.setServiceID(serviceID);
        newService.setServiceDate(serviceDate);
        newService.setClientID(clientID);
        newService.setMechanicID(mechanicID);
        newService.setServiceType(serviceType);
        newService.setServiceCost(serviceCost);
        newService.setNotes(notes);

        serviceList.add(newService);
        System.out.println("Service added successfully.");

        // Save the updated service list to the file
        ServiceFileHandler.saveServices(serviceList);
    }

    public static void getServiceByID() {
        System.out.print("Enter Service ID: ");
        String serviceID = scanner.nextLine();

        Service service = findServiceByID(serviceID);
        if (service != null) {
            System.out.println(service);
        } else {
            System.out.println("Service not found with ID: " + serviceID);
        }
    }

    public static void updateService() {
        System.out.print("Enter Service ID to Update: ");
        String serviceID = scanner.nextLine();

        Service service = findServiceByID(serviceID);
        if (service == null) {
            System.out.println("Service not found with ID: " + serviceID);
            return;
        }

        System.out.print("Enter New Service Date (dd/MM/yyyy) or press Enter to keep current: ");
        String dateInput = scanner.nextLine();
        if (!dateInput.trim().isEmpty()) {
            try {
                Date serviceDate = DATE_FORMAT.parse(dateInput);
                service.setServiceDate(serviceDate);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Keeping current date.");
            }
        }

        System.out.print("Enter New Client ID or press Enter to keep current: ");
        String clientID = scanner.nextLine();
        if (!clientID.trim().isEmpty()) {
            service.setClientID(clientID);
        }

        System.out.print("Enter New Mechanic ID or press Enter to keep current: ");
        String mechanicID = scanner.nextLine();
        if (!mechanicID.trim().isEmpty()) {
            service.setMechanicID(mechanicID);
        }

        System.out.print("Enter New Service Type or press Enter to keep current: ");
        String serviceType = scanner.nextLine();
        if (!serviceType.trim().isEmpty()) {
            service.setServiceType(serviceType);
        }

        System.out.print("Enter New Service Cost or press Enter to keep current: ");
        String serviceCostInput = scanner.nextLine();
        if (!serviceCostInput.trim().isEmpty()) {
            try {
                service.setServiceCost(Double.parseDouble(serviceCostInput));
            } catch (NumberFormatException e) {
                System.out.println("Invalid cost input. Keeping current cost.");
            }
        }

        System.out.print("Enter New Notes or press Enter to keep current: ");
        String notes = scanner.nextLine();
        if (!notes.trim().isEmpty()) {
            service.setNotes(notes);
        }

        System.out.println("Service updated successfully.");

        // Save the updated service list to the file
        ServiceFileHandler.saveServices(serviceList);
    }

    public static void deleteService() {
        System.out.print("Enter Service ID to Delete: ");
        String serviceID = scanner.nextLine();

        Service service = findServiceByID(serviceID);
        if (service != null) {
            serviceList.remove(service);
            System.out.println("Service deleted successfully.");

            // Save the updated service list to the file
            ServiceFileHandler.saveServices(serviceList);
        } else {
            System.out.println("Service not found with ID: " + serviceID);
        }
    }

    public static void addPartToService(String serviceID, String partID) {
        Service service = findServiceByID(serviceID);
        if (service == null) {
            System.out.println("Service not found.");
            return;
        }

        AutoPart part = findPartByID(partID);
        if (part == null) {
            System.out.println("Part not found.");
            return;
        }

        // Add part to the service
        service.addPart(part);
        System.out.println("Part " + partID + " added to service " + serviceID);

        // Save the updated service list to the file
        ServiceFileHandler.saveServices(serviceList);
    }

    public static void removePartFromService(String serviceID) {
        System.out.print("Enter Part ID to Remove: ");
        String partID = scanner.nextLine();

        Service service = findServiceByID(serviceID);
        if (service == null) {
            System.out.println("Service not found.");
            return;
        }

        AutoPart part = findPartByID(partID);
        if (part == null) {
            System.out.println("Part not found.");
            return;
        }

        // Remove part from the service
        boolean removed = service.removePart(partID);
        if (removed) {
            System.out.println("Part " + partID + " removed from service " + serviceID);
        } else {
            System.out.println("Part " + partID + " not found in service " + serviceID);
        }

        // Save the updated service list to the file
        ServiceFileHandler.saveServices(serviceList);
    }

    public static void listAllServices() {
        if (serviceList.isEmpty()) {
            System.out.println("No services available.");
        } else {
            for (Service service : serviceList) {
                System.out.println(service);
            }
        }
    }

    private static Service findServiceByID(String serviceID) {
        for (Service service : serviceList) {
            if (service.getServiceID().equals(serviceID)) {
                return service;
            }
        }
        return null;
    }

    private static AutoPart findPartByID(String partID) {
        for (AutoPart part : autoPartsList) {
            if (part.getPartID().equals(partID)) {
                return part;
            }
        }
        return null;
    }

    public static void listAllReplacedParts(String serviceID) {
        Service service = findServiceByID(serviceID);
        if (service == null) {
            System.out.println("Service not found with ID: " + serviceID);
            return;
        }

        List<AutoPart> replacedParts = service.getReplacedParts();
        if (replacedParts.isEmpty()) {
            System.out.println("No parts replaced in this service.");
        } else {
            System.out.println("Replaced Parts for Service ID " + serviceID + ":");
            for (AutoPart part : replacedParts) {
                System.out.println(part);
            }
        }
    }

}
