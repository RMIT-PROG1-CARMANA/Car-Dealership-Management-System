package operations;


import filehandling.AutoPartFileHandler;
import filehandling.ServiceFileHandler;
import interfaces.ServiceInterfaces;
import logsystem.*;
import part.*;
import service.Service;
import utils.InputValidation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static user.Authenticator.loggedUser;


public class ServiceService implements ServiceInterfaces {
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

    @Override
    public void addService() {
        String serviceID = InputValidation.validateServiceID("Enter Service ID: ", serviceList);

        Date serviceDate = InputValidation.validateDate("Enter Service Date (dd/MM/yyyy): ");

        String clientID = InputValidation.validateExistingUserID("Enter Client ID: ");

        String mechanicID = InputValidation.validateExistingUserID("Enter Mechanic ID: ");

        String serviceType = InputValidation.validateString("Enter Service Type: ");

        double serviceCost = InputValidation.validateDouble("Enter Service Cost: ");

        String notes = InputValidation.validateString("Enter Notes: ");

        Service newService = new Service(serviceID, serviceDate, clientID, mechanicID,serviceType, serviceCost, notes);


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

    @Override
    public void getServiceByID() {
        String serviceID = InputValidation.validateExistingServiceID("Enter Service ID: ", serviceList);

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

    @Override
    public void updateService() {
        String serviceID = InputValidation.validateExistingServiceID("Enter Service ID to Update: ", serviceList);

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

        String clientID = InputValidation.validateExistingUserID("Enter New Client ID or press Enter to keep current: ");
        if (!clientID.trim().isEmpty()) {
            service.setClientID(clientID);
        }

        String mechanicID = InputValidation.validateExistingUserID("Enter New Mechanic ID or press Enter to keep current: ");
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

    @Override
    public void deleteService() {
        String serviceID = InputValidation.validateExistingServiceID("Enter Service ID to Delete: ", serviceList);

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

    @Override
    public void addPartToService() {
        // Reload parts to ensure the latest data
        autoPartsList = new ArrayList<>(ServiceFileHandler.loadParts().values());

        AutoPartFileHandler.loadPartsData();

        String serviceID = InputValidation.validateExistingServiceID("Enter Service ID: ", serviceList);
        String partID = InputValidation.validateExistingPartID("Enter Part ID: ");
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
        addReplacedPart(service, part);
        System.out.println("Part " + partID + " added to service " + serviceID);
        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Adding part: " + partID + " to service " + serviceID
        );
        // Save the updated service list to the file
        ServiceFileHandler.saveServices(serviceList);
        // Save the updated parts list to the file
        ServiceFileHandler.saveParts(autoPartsList);
    }
    @Override
    public void removePartFromService() {
        // Reload parts to ensure the latest data
        autoPartsList = new ArrayList<>(ServiceFileHandler.loadParts().values());

        AutoPartFileHandler.loadPartsData();

        String serviceID = InputValidation.validateExistingServiceID("Enter Service ID: ", serviceList);
        String partID = InputValidation.validateExistingPartID("Enter Part ID: ");

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
        boolean removed = removeReplacedPartByID(service, partID);
        if (removed) {
            String logID = ActivityLog.generateLogID();
            ActivityLogService.logActivity(
                    logID,
                    new Date(),
                    loggedUser.getUsername(),
                    loggedUser.getUserID(),
                    "Remove part: " + partID + " from service " + serviceID
            );
            System.out.println("Part " + partID + " removed from service " + serviceID);
        } else {
            System.out.println("Part " + partID + " not found in service " + serviceID);
        }

        // Save the updated service list to the file
        ServiceFileHandler.saveServices(serviceList);
        // Save the updated parts list to the file
        ServiceFileHandler.saveParts(autoPartsList);
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

    @Override
    public void displayAllServices() {
        if (serviceList.isEmpty()) {
            System.out.println("No services available.");
        } else {
            String logID = ActivityLog.generateLogID();
            ActivityLogService.logActivity(
                    logID,
                    new Date(),
                    loggedUser.getUsername(),
                    loggedUser.getUserID(),
                    "View all services"
            );

            // Define table headers and column widths
            String headerFormat = "%-15s %-15s %-15s %-15s %-20s %-15s %-30s%n";
            String rowFormat = "%-15s %-15s %-15s %-15s %-20s %-15.2f %-30s%n";

            // Print table header
            System.out.printf(headerFormat, "Service ID", "Service Date", "Client ID", "Mechanic ID", "Service Type", "Service Cost", "Replaced Parts");

            // Print table rows
            for (Service service : serviceList) {
                // Format replaced parts with prices for the row
                StringBuilder replacedPartsInfo = new StringBuilder();
                List<AutoPart> replacedParts = service.getReplacedParts();
                if (replacedParts != null && !replacedParts.isEmpty()) {
                    for (AutoPart part : replacedParts) {
                        replacedPartsInfo.append(part.getPartID()).append(" (")
                                .append(part.getPartName()).append(" - $")
                                .append(String.format("%.2f", part.getPrice())).append(")").append(" | ");

                    }
                } else {
                    replacedPartsInfo.append("None");
                }

                // Print service details
                System.out.printf(rowFormat,
                        service.getServiceID(),
                        DATE_FORMAT.format(service.getServiceDate()),
                        service.getClientID(),
                        service.getMechanicID(),
                        service.getServiceType(),
                        service.getServiceCost(),
                        replacedPartsInfo.toString()
                );
            }
        }
    }
    public void addReplacedPart(Service service, AutoPart part) {
        if (service.getReplacedParts() == null) {
            service.setReplacedParts(new ArrayList<>());
        }
        service.getReplacedParts().add(part);

    }

    public boolean removeReplacedPartByID(Service service, String partID) {
        List<AutoPart> replacedParts = service.getReplacedParts();
        if (replacedParts != null) {
            for (AutoPart part : replacedParts) {
                if (part.getPartID().equals(partID)) {
                    replacedParts.remove(part);
                    return true;
                }
            }
        }
        System.out.println("Part " + partID + " not found in service " + service.getServiceID());
        return false;
    }

    @Override
    public void displayClientServiceHistory() {
        // Get the client ID from the logged-in client
        String clientID = loggedUser.getUserID();

        // Filter services for the logged-in client
        List<Service> clientServices = serviceList.stream()
                .filter(service -> service.getClientID().equals(clientID))
                .collect(Collectors.toList());

        // If no services found for the client, show a message
        if (clientServices.isEmpty()) {
            System.out.println("You have no service history.");
        } else {
            String logID = ActivityLog.generateLogID();
            ActivityLogService.logActivity(
                    logID,
                    new Date(),
                    loggedUser.getUsername(),
                    loggedUser.getUserID(),
                    "Viewed personal service history"
            );

            // Define table headers and column widths
            String headerFormat = "%-15s %-15s %-15s %-15s %-20s %-15s %-30s%n";
            String rowFormat = "%-15s %-15s %-15s %-15s %-20s %-15.2f %-30s%n";

            // Print table header
            System.out.printf(headerFormat, "Service ID", "Service Date", "Client ID", "Mechanic ID", "Service Type", "Service Cost", "Replaced Parts");

            // Print each service for the logged-in client
            for (Service service : clientServices) {
                // Format replaced parts with prices for the row
                StringBuilder replacedPartsInfo = new StringBuilder();
                List<AutoPart> replacedParts = service.getReplacedParts();
                if (replacedParts != null && !replacedParts.isEmpty()) {
                    for (AutoPart part : replacedParts) {
                        replacedPartsInfo.append(part.getPartID()).append(" (")
                                .append(part.getPartName()).append(" - $")
                                .append(String.format("%.2f", part.getPrice())).append(")").append(" | ");
                    }
                } else {
                    replacedPartsInfo.append("None");
                }

                // Print service details
                System.out.printf(rowFormat,
                        service.getServiceID(),
                        DATE_FORMAT.format(service.getServiceDate()),
                        service.getClientID(),
                        service.getMechanicID(),
                        service.getServiceType(),
                        service.getServiceCost(),
                        replacedPartsInfo.toString()
                );
            }
        }
    }
    public String getReplacedPartsInfo(List<AutoPart> replacedParts) {
        if (replacedParts == null) {
            return "0";
        }

        String replacedPartsInfo = replacedParts.isEmpty()
                ? "No replaced parts."
                : replacedParts.stream()
                .map(part -> String.format("Part ID: %s, Part Name: %s, Price: $%.2f",
                        part.getPartID(), part.getPartName(), part.getPrice()))
                .reduce((part1, part2) -> part1 + "\n               " + part2)
                .orElse("No replaced parts.");

        return replacedPartsInfo;
    }
}
