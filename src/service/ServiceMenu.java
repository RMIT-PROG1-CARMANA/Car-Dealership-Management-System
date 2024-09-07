package service;

import part.AutoPart;
import util.InputValidation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ServiceMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        // Load data from files
        List<AutoPart> loadedParts = ServiceFileHandler.loadPartsFromFile();
        AutoPart.setPartList(loadedParts);

        List<Service> loadedServices = ServiceFileHandler.loadServicesFromFile();
        Service.setServiceList(loadedServices);

        // Main loop
        while (true) {
            displayMenu();
            int choice = getValidInteger("Choose a valid option (1-8): ");
            handleMenuChoice(choice);
        }
    }

    private static void displayMenu() {
        System.out.println("\n--- Service Management System ---");
        System.out.println("1. Add Service");
        System.out.println("2. Get Service by ID");
        System.out.println("3. Update Service");
        System.out.println("4. Delete Service");
        System.out.println("5. Add Part to Service");
        System.out.println("6. Remove Part from Service");
        System.out.println("7. List All Services");
        System.out.println("8. Exit");
        System.out.print("Select an option: ");
    }

    private static void handleMenuChoice(int choice) {
        switch (choice) {
            case 1 -> addService();
            case 2 -> getServiceByID();
            case 3 -> updateService();
            case 4 -> deleteService();
            case 5 -> addPartToService();
            case 6 -> removePartFromService();
            case 7 -> listAllServices();
            case 8 -> exitProgram();
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    private static void exitProgram() {
        // Save data to files before exiting
        ServiceFileHandler.saveServicesToFile(Service.getAllServices());
//        ServiceFileHandler.savePartsToFile(AutoPart.getAllParts());
        System.out.println("Data saved. Exiting...");
        System.exit(0);
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
                if (InputValidation.isValidDouble(input)) {
                    value = Double.parseDouble(input);
                    return value;
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
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

    private static void addService() {
        String serviceID = getValidStringInput("Enter Service ID (starting with 's-'): ", InputValidation::isValidServiceID);
        Date serviceDate = getValidDate("Enter Service Date (dd/MM/yyyy): ");
        String clientID = getValidStringInput("Enter Client ID (starting with 'c-'): ", InputValidation::isValidClientID);
        String mechanicID = getValidStringInput("Enter Mechanic ID (starting with 'm-'): ", InputValidation::isValidMechanicID);
        System.out.print("Enter Service Type: ");
        String serviceType = scanner.nextLine();
        double serviceCost = getValidDouble("Enter Service Cost: ");
        System.out.print("Enter Notes: ");
        String notes = scanner.nextLine();

        Service newService = new Service(serviceID, serviceDate, clientID, mechanicID, serviceType, serviceCost, notes);
        Service.addService(newService);
        System.out.println("Service added.");
    }

    private static String getValidStringInput(String prompt, java.util.function.Predicate<String> validation) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (validation.test(input)) {
                return input;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void getServiceByID() {
        String serviceID = getValidStringInput("Enter Service ID (starting with 's-'): ", InputValidation::isValidServiceID);
        Service service = Service.getServiceByID(serviceID);
        if (service != null) {
            System.out.println(service);
        } else {
            System.out.println("Service not found with ID: " + serviceID);
        }
    }

    private static void updateService() {
        String serviceID = getValidStringInput("Enter Service ID to Update (starting with 's-'): ", InputValidation::isValidServiceID);
        Service service = Service.getServiceByID(serviceID);
        if (service == null) {
            System.out.println("Service not found with ID: " + serviceID);
            return;
        }

        Date serviceDate = getValidDateOrDefault("Enter New Service Date (dd/MM/yyyy) or press Enter to keep existing: ", service.getServiceDate());
        String clientID = getValidStringInput("Enter New Client ID (starting with 'c-') or press Enter to keep existing: ", InputValidation::isValidClientID);
        String mechanicID = getValidStringInput("Enter New Mechanic ID (starting with 'm-') or press Enter to keep existing: ", InputValidation::isValidMechanicID);
        System.out.print("Enter New Service Type or press Enter to keep existing: ");
        String serviceType = scanner.nextLine().trim();
        if (serviceType.isEmpty()) {
            serviceType = service.getServiceType();
        }
        double serviceCost = getValidDoubleOrDefault("Enter New Service Cost or press Enter to keep existing: ", service.getServiceCost());
        System.out.print("Enter New Notes or press Enter to keep existing: ");
        String notes = scanner.nextLine().trim();
        if (notes.isEmpty()) {
            notes = service.getNotes();
        }

        service.setServiceDate(serviceDate);
        service.setClientID(clientID);
        service.setMechanicID(mechanicID);
        service.setServiceType(serviceType);
        service.setServiceCost(serviceCost);
        service.setNotes(notes);
        System.out.println("Service updated.");
    }

    private static Date getValidDateOrDefault(String prompt, Date defaultDate) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return defaultDate;
        }
        return getValidDate(prompt);
    }

    private static double getValidDoubleOrDefault(String prompt, double defaultValue) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return defaultValue;
        }
        return getValidDouble(prompt);
    }

    private static void deleteService() {
        String serviceID = getValidStringInput("Enter Service ID to Delete (starting with 's-'): ", InputValidation::isValidServiceID);
        Service.deleteService(serviceID);
        System.out.println("Service deleted with ID: " + serviceID);
    }

    private static void addPartToService() {
        String serviceID = getValidStringInput("Enter Service ID (starting with 's-'): ", InputValidation::isValidServiceID);
        Service service = Service.getServiceByID(serviceID);
        if (service == null) {
            System.out.println("Service not found with ID: " + serviceID);
            return;
        }

        String partID = getValidStringInput("Enter Part ID to add: ", InputValidation::isValidPartID);
        AutoPart part = AutoPart.getPartByID(partID);
        if (part == null) {
            System.out.println("Part not found with ID: " + partID);
            return;
        }

        service.getReplacedParts().add(part);
        System.out.println("Part added to service: " + partID);
    }

    private static void removePartFromService() {
        String serviceID = getValidStringInput("Enter Service ID (starting with 's-'): ", InputValidation::isValidServiceID);
        Service service = Service.getServiceByID(serviceID);
        if (service == null) {
            System.out.println("Service not found with ID: " + serviceID);
            return;
        }

        String partID = getValidStringInput("Enter Part ID to remove: ", InputValidation::isValidPartID);
        AutoPart part = AutoPart.getPartByID(partID);
        if (part == null) {
            System.out.println("Part not found with ID: " + partID);
            return;
        }

        if (service.getReplacedParts().remove(part)) {
            System.out.println("Part removed from service: " + partID);
        } else {
            System.out.println("Part was not found in service: " + partID);
        }
    }

    private static void listAllServices() {
        Service.getAllServices().forEach(service -> System.out.println(service));
    }
}
