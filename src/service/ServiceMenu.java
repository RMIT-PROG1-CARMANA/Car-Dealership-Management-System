package service;

import part.AutoPart;
import part.AutoPartFileHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ServiceMenu {
    private static Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static List<Service> serviceList = new ArrayList<>();
    private static List<AutoPart> autoPartsList = new ArrayList<>();

    static {
        // Load services and parts from files
        try {
            serviceList = ServiceFileHandler.loadServices();
            if (serviceList == null) {
                serviceList = new ArrayList<>();
            }
            loadAutoParts(); // Load auto parts
        } catch (Exception e) {
            System.out.println("Error initializing ServiceMenu: " + e.getMessage());
            serviceList = new ArrayList<>();
            autoPartsList = new ArrayList<>();
        }
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
        // Uncomment validation check if needed
        // while (!InputValidation.isValidServiceID(serviceID)) {
        //     System.out.println("Invalid Service ID. It should start with 's-'. Please try again.");
        //     serviceID = scanner.nextLine();
        // }

        Date serviceDate = getValidDate("Enter Service Date (dd/MM/yyyy): ");

        System.out.print("Enter Client ID: ");
        String clientID = scanner.nextLine();
        // Uncomment validation check if needed
        // while (!InputValidation.isValidClientID(clientID)) {
        //     System.out.println("Invalid Client ID. It should start with 'c-'. Please try again.");
        //     clientID = scanner.nextLine();
        // }

        System.out.print("Enter Mechanic ID: ");
        String mechanicID = scanner.nextLine();
        // Uncomment validation check if needed
        // while (!InputValidation.isValidMechanicID(mechanicID)) {
        //     System.out.println("Invalid Mechanic ID. It should start with 'm-'. Please try again.");
        //     mechanicID = scanner.nextLine();
        // }

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
    }

    public static void getServiceByID() {
        System.out.print("Enter Service ID: ");
        String serviceID = scanner.nextLine();
        // Uncomment validation check if needed
        // while (!InputValidation.isValidServiceID(serviceID)) {
        //     System.out.println("Invalid Service ID. It should start with 's-'. Please try again.");
        //     serviceID = scanner.nextLine();
        // }

        Service service = findServiceByID(serviceID);
        if (service != null) {
            System.out.println(service.toString());
        } else {
            System.out.println("Service not found with ID: " + serviceID);
        }
    }

    public static void updateService() {
        System.out.print("Enter Service ID to Update: ");
        String serviceID = scanner.nextLine();
        // Uncomment validation check if needed
        // while (!InputValidation.isValidServiceID(serviceID)) {
        //     System.out.println("Invalid Service ID. It should start with 's-'. Please try again.");
        //     serviceID = scanner.nextLine();
        // }

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
        // Uncomment validation check if needed
        // if (!clientID.trim().isEmpty() && InputValidation.isValidClientID(clientID)) {
        service.setClientID(clientID);
        // }

        System.out.print("Enter New Mechanic ID or press Enter to keep current: ");
        String mechanicID = scanner.nextLine();
        // Uncomment validation check if needed
        // if (!mechanicID.trim().isEmpty() && InputValidation.isValidMechanicID(mechanicID)) {
        service.setMechanicID(mechanicID);
        // }

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
    }

    public static void deleteService() {
        System.out.print("Enter Service ID to Delete: ");
        String serviceID = scanner.nextLine();
        // Uncomment validation check if needed
        // while (!InputValidation.isValidServiceID(serviceID)) {
        //     System.out.println("Invalid Service ID. It should start with 's-'. Please try again.");
        //     serviceID = scanner.nextLine();
        // }

        Service service = findServiceByID(serviceID);
        if (service != null) {
            serviceList.remove(service);
            System.out.println("Service deleted successfully.");
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

    public static void removePartFromService() {
        System.out.print("Enter Service ID to Remove Part: ");
        String serviceID = scanner.nextLine();
        // Uncomment validation check if needed
        // while (!InputValidation.isValidServiceID(serviceID)) {
        //     System.out.println("Invalid Service ID. It should start with 's-'. Please try again.");
        //     serviceID = scanner.nextLine();
        // }

        Service service = findServiceByID(serviceID);
        if (service == null) {
            System.out.println("Service not found.");
            return;
        }

        System.out.print("Enter Part ID to Remove: ");
        String partID = scanner.nextLine();
        // Uncomment validation check if needed
        // while (!InputValidation.isValidPartID(partID)) {
        //     System.out.println("Invalid Part ID. It should start with 'p-'. Please try again.");
        //     partID = scanner.nextLine();
        // }

        AutoPart part = findPartByID(partID);
        if (part == null) {
            System.out.println("Part not found.");
            return;
        }

        // Remove part from the service
        service.removePart(String.valueOf(part));
        System.out.println("Part " + partID + " removed from service " + serviceID);

        // Save the updated service list to the file
        ServiceFileHandler.saveServices(serviceList);
    }

    public static void listAllServices() {
        if (serviceList.isEmpty()) {
            System.out.println("No services available.");
        } else {
            for (Service service : serviceList) {
                System.out.println(service.toString());
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

    private static void loadAutoParts() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/DataBase/parts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    AutoPart part = new AutoPart(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], Double.parseDouble(parts[6]), parts[7]);
                    autoPartsList.add(part);
                }
            }
        }
    }
}
