package operations;


import FileHandling.ServiceFileHandler;
import logsystem.*;
import part.*;
import service.Service;
import utils.InputValidation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static user.Authenticator.loggedUser;


public class ServiceService {
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


    public static void addService() {
        String serviceID = InputValidation.validateServiceID("Enter Service ID: ", serviceList);

        Date serviceDate = InputValidation.validateDate("Enter Service Date (dd/MM/yyyy): ");

        String clientID = InputValidation.validateUserID("Enter Client ID: ");

        String mechanicID = InputValidation.validateString("Enter Mechanic ID: ");

        String serviceType = InputValidation.validateString("Enter Service Type: ");

        double serviceCost = InputValidation.validateDouble("Enter Service Cost: ");

        String notes = InputValidation.validateString("Enter Notes: ");

        Service newService = new Service();
        newService.setServiceID(serviceID);
        newService.setServiceDate(serviceDate);
        newService.setClientID(clientID);
        newService.setMechanicID(mechanicID);
        newService.setServiceType(serviceType);
        newService.setServiceCost(serviceCost);
        newService.setNotes(notes);

        serviceList.add(newService);
        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Adding new service: " + serviceID
        );
        System.out.println("Service added successfully.");

        // Save the updated service list to the file
        ServiceFileHandler.saveServices(serviceList);
    }

    public static void getServiceByID() {
        String serviceID = InputValidation.validateString("Enter Service ID: ");

        Service service = findServiceByID(serviceID);
        if (service != null) {
            String logID = ActivityLog.generateLogID();
            ActivityLogService.logActivity(
                    logID,
                    new Date(),
                    loggedUser.getUsername(),
                    loggedUser.getUserID(),
                    "Get service by ID: " + serviceID
            );
            System.out.println(service);
        } else {
            System.out.println("Service not found with ID: " + serviceID);
        }
    }

    public static void updateService() {
        String serviceID = InputValidation.validateString("Enter Service ID to Update: ");

        Service service = findServiceByID(serviceID);
        if (service == null) {
            System.out.println("Service not found with ID: " + serviceID);
            return;
        }

        String dateInput = InputValidation.validateString("Enter New Service Date (dd/MM/yyyy) or press Enter to keep current: ");
        if (!dateInput.trim().isEmpty()) {
            try {
                Date serviceDate = DATE_FORMAT.parse(dateInput);
                service.setServiceDate(serviceDate);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Keeping current date.");
            }
        }

        String clientID = InputValidation.validateString("Enter New Client ID or press Enter to keep current: ");
        if (!clientID.trim().isEmpty()) {
            service.setClientID(clientID);
        }

        String mechanicID = InputValidation.validateString("Enter New Mechanic ID or press Enter to keep current: ");
        if (!mechanicID.trim().isEmpty()) {
            service.setMechanicID(mechanicID);
        }

        String serviceType = InputValidation.validateString("Enter New Service Type or press Enter to keep current: ");
        if (!serviceType.trim().isEmpty()) {
            service.setServiceType(serviceType);
        }

        String serviceCostInput = InputValidation.validateString("Enter New Service Cost or press Enter to keep current: ");
        if (!serviceCostInput.trim().isEmpty()) {
            try {
                service.setServiceCost(Double.parseDouble(serviceCostInput));
            } catch (NumberFormatException e) {
                System.out.println("Invalid cost input. Keeping current cost.");
            }
        }

        String notes = InputValidation.validateString("Enter New Notes or press Enter to keep current: ");
        if (!notes.trim().isEmpty()) {
            service.setNotes(notes);
        }

        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Update the service: " + serviceID
        );
        System.out.println("Service updated successfully.");

        // Save the updated service list to the file
        ServiceFileHandler.saveServices(serviceList);
    }

    public static void deleteService() {
        String serviceID = InputValidation.validateString("Enter Service ID to Delete: ");

        Service service = findServiceByID(serviceID);
        if (service != null) {
            serviceList.remove(service);
            System.out.println("Service deleted successfully.");
            String logID = ActivityLog.generateLogID();
            ActivityLogService.logActivity(
                    logID,
                    new Date(),
                    loggedUser.getUsername(),
                    loggedUser.getUserID(),
                    "Delete the service : " + serviceID
            );

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
        service.addPartService(part);
        System.out.println("Part " + partID + " added to service " + serviceID);
        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Adding part: " + partID +" to service " + serviceID
        );
        // Save the updated service list to the file
        ServiceFileHandler.saveServices(serviceList);
    }

    public static void removePartFromService(String serviceID) {
        String partID = InputValidation.validateString("Enter Part ID to Remove: ");

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
        boolean removed = service.removePartService(partID);
        if (removed) {
            String logID = ActivityLog.generateLogID();
            ActivityLogService.logActivity(
                    logID,
                    new Date(),
                    loggedUser.getUsername(),
                    loggedUser.getUserID(),
                    "Remove part: " + partID +" from service " + serviceID
            );
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
            String logID = ActivityLog.generateLogID();
            ActivityLogService.logActivity(
                    logID,
                    new Date(),
                    loggedUser.getUsername(),
                    loggedUser.getUserID(),
                    "View all service"
            );
            for (Service service : serviceList) {
                System.out.println(service);
            }
        }
    }

    private static Service findServiceByID(String serviceID) {
        for (Service service : serviceList) {
            if (service.getServiceID().equals(serviceID)) {
                String logID = ActivityLog.generateLogID();
                ActivityLogService.logActivity(
                        logID,
                        new Date(),
                        loggedUser.getUsername(),
                        loggedUser.getUserID(),
                        "Find service: " + serviceID
                );
                return service;
            }
        }
        return null;
    }

    private static AutoPart findPartByID(String partID) {
        for (AutoPart part : autoPartsList) {
            if (part.getPartID().equals(partID)) {
                String logID = ActivityLog.generateLogID();
                ActivityLogService.logActivity(
                        logID,
                        new Date(),
                        loggedUser.getUsername(),
                        loggedUser.getUserID(),
                        "Find part: " + partID
                );
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
                System.out.println("Part Name: " + part.getPartName());
            }
        }
    }
}