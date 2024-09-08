package service;

import part.AutoPart;
import part.AutoPartFileHandler;
import util.InputValidation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ServiceMenu {
    private static Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static List<Service> serviceList;
    private static List<AutoPart> autoPartsList;

    public static void main(String[] args) {
        // Load services and parts from files
        serviceList = ServiceFileHandler.loadServices();
        loadAutoParts(); // Load auto parts

        if (serviceList == null) {
            serviceList = new ArrayList<>();
        }
        if (autoPartsList == null) {
            autoPartsList = new ArrayList<>();
        }

        while (true) {
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

            int choice = getValidInteger("Choose a valid option (1-8): ");
            switch (choice) {
                case 1:
                    addService();
                    break;
                case 2:
                    getServiceByID();
                    break;
                case 3:
                    updateService();
                    break;
                case 4:
                    deleteService();
                    break;
                case 5:
                    // You need to get the serviceID and partID before calling the method
                    System.out.print("Enter Service ID: ");
                    String serviceID = scanner.nextLine();  // Assuming you're using a Scanner for input

                    System.out.print("Enter Part ID: ");
                    String partID = scanner.nextLine();

                    addPartToService(serviceID, partID);
                    break;
                case 6:
                    removePartFromService();
                    break;
                case 7:
                    listAllServices();
                    break;
                case 8:
                    try {
                        // Save services
                        ServiceFileHandler.saveServices(serviceList);

                        // If AutoPartFileHandler is not modifying the file, call this
                        AutoPartFileHandler.savePartsToFile(); // Ensure this method doesn't overwrite

                        System.out.println("Exiting...");
                        return;
                    } catch (Exception e) {
                        System.out.println("Unexpected error occurred: " + e.getMessage());
                    }
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
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
        System.out.print("Enter Service ID: ");
        String serviceID = scanner.nextLine();
        while (!InputValidation.isValidServiceID(serviceID)) {
            System.out.println("Invalid Service ID. It should start with 's-'. Please try again.");
            serviceID = scanner.nextLine();
        }

        Date serviceDate = getValidDate("Enter Service Date (dd/MM/yyyy): ");

        System.out.print("Enter Client ID: ");
        String clientID = scanner.nextLine();
        while (!InputValidation.isValidClientID(clientID)) {
            System.out.println("Invalid Client ID. It should start with 'c-'. Please try again.");
            clientID = scanner.nextLine();
        }

        System.out.print("Enter Mechanic ID: ");
        String mechanicID = scanner.nextLine();
        while (!InputValidation.isValidMechanicID(mechanicID)) {
            System.out.println("Invalid Mechanic ID. It should start with 'm-'. Please try again.");
            mechanicID = scanner.nextLine();
        }

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

    private static void getServiceByID() {
        System.out.print("Enter Service ID: ");
        String serviceID = scanner.nextLine();
        while (!InputValidation.isValidServiceID(serviceID)) {
            System.out.println("Invalid Service ID. It should start with 's-'. Please try again.");
            serviceID = scanner.nextLine();
        }

        Service service = findServiceByID(serviceID);
        if (service != null) {
            System.out.println(service.toString());
        } else {
            System.out.println("Service not found with ID: " + serviceID);
        }
    }

    private static void updateService() {
        System.out.print("Enter Service ID to Update: ");
        String serviceID = scanner.nextLine();
        while (!InputValidation.isValidServiceID(serviceID)) {
            System.out.println("Invalid Service ID. It should start with 's-'. Please try again.");
            serviceID = scanner.nextLine();
        }

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
        if (!clientID.trim().isEmpty() && InputValidation.isValidClientID(clientID)) {
            service.setClientID(clientID);
        }

        System.out.print("Enter New Mechanic ID or press Enter to keep current: ");
        String mechanicID = scanner.nextLine();
        if (!mechanicID.trim().isEmpty() && InputValidation.isValidMechanicID(mechanicID)) {
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
            if (InputValidation.isValidDouble(serviceCostInput)) {
                service.setServiceCost(Double.parseDouble(serviceCostInput));
            } else {
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

    private static void deleteService() {
        System.out.print("Enter Service ID to Delete: ");
        String serviceID = scanner.nextLine();
        while (!InputValidation.isValidServiceID(serviceID)) {
            System.out.println("Invalid Service ID. It should start with 's-'. Please try again.");
            serviceID = scanner.nextLine();
        }

        Service service = findServiceByID(serviceID);
        if (service != null) {
            serviceList.remove(service);
            System.out.println("Service deleted successfully.");
        } else {
            System.out.println("Service not found with ID: " + serviceID);
        }
    }

    private static void addPartToService(String serviceID, String partID) {
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




    private static void removePartFromService() {
        System.out.print("Enter Service ID to Remove Part: ");
        String serviceID = scanner.nextLine();
        while (!InputValidation.isValidServiceID(serviceID)) {
            System.out.println("Invalid Service ID. It should start with 's-'. Please try again.");
            serviceID = scanner.nextLine();
        }

        Service service = findServiceByID(serviceID);
        if (service == null) {
            System.out.println("Service not found with ID: " + serviceID);
            return;
        }

        System.out.print("Enter Part ID to Remove: ");
        String partID = scanner.nextLine();

        if (service.removePart(partID)) {
            System.out.println("Part removed from service successfully.");
        } else {
            System.out.println("Part not found in the service with ID: " + partID);
        }
    }

    private static void listAllServices() {
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
        if (autoPartsList == null) {
            System.out.println("Auto parts list is not initialized.");
            return null;
        }

        for (AutoPart part : autoPartsList) {
            if (part.getPartID().equals(partID)) {
                return part;
            }
        }
        return null; // No matching part found
    }


    private static void loadAutoParts() {
        autoPartsList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/DataBase/parts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partsData = line.split(",");
                if (partsData.length == 8) {
                    AutoPart part = new AutoPart(
                            partsData[0], partsData[1], partsData[2], partsData[3],
                            partsData[4], partsData[5], Double.parseDouble(partsData[6]), partsData[7]
                    );
                    autoPartsList.add(part);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading parts: " + e.getMessage());
        }
    }

}
