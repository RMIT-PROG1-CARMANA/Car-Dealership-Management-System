package operations;

import java.util.*;
import interfaces.AutoPartInterfaces;
import logsystem.ActivityLog;
import part.*;
import utils.*;

import static user.Authenticator.loggedUser;

public class AutoPartService implements AutoPartInterfaces {
    // Method to add part with validation
    @Override
    public void addPart() {
        // Collect input and validate
        String partID = InputValidation.validatePartID("Enter Part ID (format: p-XXXX): ");
        String partName = InputValidation.validateString("Enter Part Name: ");
        String manufacturer = InputValidation.validateString("Enter Manufacturer: ");
        String partNumber = InputValidation.validateString("Enter Part Number: ");
        String condition = InputValidation.validateString("Enter Condition (e.g., new, refurbished): ");
        String warranty = InputValidation.validateString("Enter Warranty (e.g., 1 year, 6 months): ");
        double price = InputValidation.validateDouble("Enter Cost: ");
        String notes = InputValidation.validateString("Enter Notes: "); // Notes can be empty

        // Create the part
        AutoPart part = new AutoPart(partID, partName, manufacturer, partNumber, condition, warranty, price, notes);

        // Add the part to the list
        AutoPart.partsList.add(part);

        // Log the activity
        String logID = ActivityLog.generateLogID();
        ActivityLogService.logActivity(
                logID,
                new Date(),
                loggedUser.getUsername(),
                loggedUser.getUserID(),
                "Add new part: " + partID
        );

        // Confirmation message
        System.out.println("Part added successfully.");
    }


    // Method to view part details with validation
    @Override
    public void viewPartDetails() {
        String partID = InputValidation.validateExistingPartID("Enter Part ID to View Details: ");
        AutoPart part = getPartByID(partID);
        if (part != null) {
            String logID = ActivityLog.generateLogID();
            ActivityLogService.logActivity(
                    logID,
                    new Date(),
                    loggedUser.getUsername(),
                    loggedUser.getUserID(),
                    "View Part details " + partID
            );
            System.out.println(part.toString());
        } else {
            System.out.println("No part found with the given ID.");
        }
    }

    // Method to update part with validation
    @Override
    public void updatePart() {
        String partID = InputValidation.validateExistingPartID("Enter Part ID to Update: ");

        AutoPart part = getPartByID(partID);
        if (part != null) {
            String partName = InputValidation.validateString("Enter New Part Name (or press Enter to skip): ");
            if (!partName.isEmpty()) {
                part.setPartName(partName);
            }

            String manufacturer = InputValidation.validateString("Enter New Manufacturer (or press Enter to skip): ");
            if (!manufacturer.isEmpty()) {
                part.setManufacturer(manufacturer);
            }

            String partNumber = InputValidation.validateString("Enter New Part Number (or press Enter to skip): ");
            if (!partNumber.isEmpty()) {
                part.setPartNumber(partNumber);
            }

            String condition = InputValidation.validateString("Enter New Condition (or press Enter to skip): ");
            if (!condition.isEmpty()) {
                part.setCondition(condition);
            }

            String warranty = InputValidation.validateString("Enter New Warranty (or press Enter to skip): ");
            if (!warranty.isEmpty()) {
                part.setWarranty(warranty);
            }

            // Validate the cost (double) and allow skipping by entering 0
            double price = InputValidation.validateDouble("Enter New Cost (or enter 0 to skip): ");
            if (price > 0) {
                part.setPrice(price);
            }

            // Validate notes, allowing the user to skip by pressing Enter
            String notes = InputValidation.validateString("Enter New Notes (or press Enter to skip): ");
            if (!notes.isEmpty()) {
                part.setNotes(notes);
            }

            String logID = ActivityLog.generateLogID();
            ActivityLogService.logActivity(
                    logID,
                    new Date(),
                    loggedUser.getUsername(),
                    loggedUser.getUserID(),
                    "Update part information: " + partID
            );
            System.out.println("Part updated successfully.");
        } else {
            System.out.println("No part found with the given ID.");
        }
    }

    // Method to delete part
    @Override
    public void deletePart() {
        String partID = InputValidation.validateExistingPartID("Enter Part ID to Delete: ");
        if (deletePart(partID)) {
            String logID = ActivityLog.generateLogID();
            ActivityLogService.logActivity(
                    logID,
                    new Date(),
                    loggedUser.getUsername(),
                    loggedUser.getUserID(),
                    "Delete part: " + partID
            );
            System.out.println("Part deleted successfully.");
        } else {
            System.out.println("No part found with the given ID.");
        }
    }

    @Override
    public  AutoPart getPartByID(String partID) {
        return AutoPart.partsList.stream().filter(part -> part.getPartID().equals(partID)).findFirst().orElse(null);
    }
    @Override
    public boolean deletePart(String partID) {
        return AutoPart.partsList.removeIf(part -> part.getPartID().equals(partID));
    }
    @Override
    public void listAllParts() {
        if (AutoPart.partsList.isEmpty()) {
            System.out.println("No parts available.");
        } else {
            AutoPart.partsList.forEach(System.out::println);
        }
    }

    public static AutoPart findAutoPartByID(String partId) {
        for (AutoPart part : AutoPart.partsList) {
            if (Objects.equals(part.getPartID(), partId)) {
                String logID = ActivityLog.generateLogID();
                ActivityLogService.logActivity(
                        logID,
                        new Date(),
                        loggedUser.getUsername(),
                        loggedUser.getUserID(),
                        "Search part by ID: " + partId
                );
                return part;  // Return the part if found
            }
        }
        return null;  // Return null if the part is not found
    }

}